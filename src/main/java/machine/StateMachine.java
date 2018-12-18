package machine;

import beverage.Drinks;
import inventory.Inventory;

public enum StateMachine implements Machine {
    STAND_BY {
	@Override
	public int putCoins(VendingMachine machine, int coins) {
	    machine.getInventory().setBalance(machine.getInventory().getBalance() + coins);
	    machine.setState(SELECT_ITEM);
	    return coins;
	}
    },
    SELECT_ITEM {
	@Override
	public Drinks selectDrink(VendingMachine machine, Drinks drink) {
	    Inventory inventory = machine.getInventory();
	    if (drink == Drinks.SODA) {
		if (inventory.getSlots() <= 0) {
		    System.out.printf("We are sorry! Out of %s\n", drink);
		    returnCoins(machine);
		    machine.setState(SERVICE);
		    return drink;
		}
	    }
	    if (inventory.getCoffee() < drink.getCoffee() || inventory.getMilk() < drink.getMilk()) {
		System.out.printf("We are sorry! Out of %s.\n", drink);
		returnCoins(machine);
		machine.setState(SERVICE);
		return drink;
	    }
	    machine.setState(MAKE_ITEM);
	    actualDrink = drink;
	    return drink;
	}
	// TODO check if enough inventory ? MAKE_ITEM : SERVICE(or SELECT_ITEM)
	
	@Override
	public int putCoins(VendingMachine machine, int coins) {
	    machine.getInventory().setBalance(machine.getInventory().getBalance() + coins);
	    machine.setState(SELECT_ITEM);
	    return coins;
	}
	
	@Override
	public int returnCoins(VendingMachine machine) {
	    int coins = machine.getInventory().getBalance();
	    System.out.printf("%d coins returned!\n", coins);
	    machine.getInventory().setBalance(0);
	    machine.setState(STAND_BY);
	    return coins;
	}
    },
    MAKE_ITEM {
	@Override
	public Drinks makeDrink(VendingMachine machine) {
	    if (machine.getInventory().getBalance() < actualDrink.getPrice()) {
		System.out.printf("The price of %s is %d.\nPlease add %d.\n", actualDrink, actualDrink.getPrice(),
			actualDrink.getPrice() - machine.getInventory().getBalance());
		machine.setState(MAKE_ITEM);
		return actualDrink;
	    }
	    actualDrink.create();
	    machine.setState(TAKE_ITEM);
	    return actualDrink;
	}
	
	@Override
	public int putCoins(VendingMachine machine, int coins) {
	    machine.getInventory().setBalance(machine.getInventory().getBalance() + coins);
	    machine.setState(MAKE_ITEM);
	    return coins;
	}
	
	@Override
	public int returnCoins(VendingMachine machine) {
	    int coins = machine.getInventory().getBalance();
	    System.out.printf("%d coins returned!\n", coins);
	    machine.getInventory().setBalance(0);
	    machine.setState(STAND_BY);
	    return coins;
	}
    },
    TAKE_ITEM {
	@Override
	public Drinks takeDrink(VendingMachine machine) {
	    System.out.printf("Your %s is ready!\n", actualDrink);
	    returnCoins(machine);
	    machine.setState(STAND_BY);
	    actualDrink = null;
	    return actualDrink;
	}

	@Override
	public int returnCoins(VendingMachine machine) {
	    int coins = machine.getInventory().getBalance();
	    System.out.printf("%d coins returned!\n", coins);
	    machine.getInventory().setBalance(0);
	    machine.setState(STAND_BY);
	    return coins;
	}
    },
    SERVICE {
	@Override
	public void service(VendingMachine machine) {
	    machine.getInventory().fillUpInventory();
	    machine.setState(SERVICE);
	}

	@Override
	public void endService(VendingMachine machine) {
	    machine.setState(STAND_BY);
	}
    };

    static Drinks actualDrink = null;

    @Override
    public int putCoins(VendingMachine machine, int coins) {
	return coins;
    }

    @Override
    public int returnCoins(VendingMachine machine) {
	return 0;
    }

    @Override
    public Drinks selectDrink(VendingMachine machine, Drinks drink) {
	return null;
    }

    @Override
    public Drinks makeDrink(VendingMachine machine) {
	return null;
    }

    @Override
    public Drinks takeDrink(VendingMachine machine) {
	return null;
    }

    @Override
    public void service(VendingMachine machine) {
    }

    @Override
    public void endService(VendingMachine machine) {
    }

}
