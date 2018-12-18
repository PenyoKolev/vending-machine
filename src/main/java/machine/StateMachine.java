package machine;

import beverage.Drinks;
import inventory.Inventory;

public enum StateMachine implements Machine {
    STAND_BY {
	@Override
	public boolean putCoins(VendingMachine machine, int coins) {
	    machine.getInventory().setBalance(machine.getInventory().getBalance() + coins);
	    machine.setState(SELECT_ITEM);
	    return true;
	}
    },
    SELECT_ITEM {
	@Override
	public boolean selectDrink(VendingMachine machine, Drinks drink) {
	    Inventory inventory = machine.getInventory();
	    if (drink == Drinks.SODA) {
		if (inventory.getSlots() <= 0) {
		    System.out.printf("We are sorry! Out of %s\n", drink);
		    returnCoins(machine);
		    machine.setState(SERVICE);
		    return false;
		}
	    }
	    if (inventory.getCoffee() < drink.getCoffee() || inventory.getMilk() < drink.getMilk()) {
		System.out.printf("We are sorry! Out of %s.\n", drink);
		returnCoins(machine);
		machine.setState(SERVICE);
		return false;
	    }
	    machine.setState(MAKE_ITEM);
	    actualDrink = drink;
	    return true;
	}
	// TODO check if enough inventory ? MAKE_ITEM : SERVICE(or SELECT_ITEM)

	@Override
	public boolean returnCoins(VendingMachine machine) {
	    // TODO implement returnCoins
	    System.out.printf("%d coins returned!\n", machine.getInventory().getBalance());
	    machine.getInventory().setBalance(0);
	    machine.setState(STAND_BY);
	    return true;
	}
    },
    MAKE_ITEM {
	@Override
	public boolean makeDrink(VendingMachine machine) {
	    if (machine.getInventory().getBalance() < actualDrink.getPrice()) {
		System.out.printf("The price of %s is %d.\nPlease add %d.\n", actualDrink, actualDrink.getPrice(),
			actualDrink.getPrice() - machine.getInventory().getBalance());
		machine.setState(STAND_BY);
		return false;
	    }
	    actualDrink.create();
	    machine.setState(TAKE_ITEM);
	    return true;
	}
    },
    TAKE_ITEM {
	@Override
	public boolean takeDrink(VendingMachine machine) {
	    
	    System.out.printf("Your %s is ready!\n", actualDrink);
	    returnCoins(machine);
	    machine.setState(STAND_BY);
	    actualDrink = null;
	    return true;
	}

	@Override
	public boolean returnCoins(VendingMachine machine) {
	    System.out.printf("%d coins returned!\n", machine.getInventory().getBalance());
	    machine.getInventory().setBalance(0);
	    machine.setState(STAND_BY);
	    return true;
	}
	// TODO return coins ? STAND_BY : SERVICE
    },
    SERVICE {
	@Override
	public boolean service(VendingMachine machine) {
	    machine.service();
	    return true;
	}

	@Override
	public boolean returnCoins(VendingMachine machine) {
	    // TODO Auto-generated method stub
	    return false;
	}

	@Override
	public boolean endService(VendingMachine machine) {
	    machine.setState(STAND_BY);
	    return true;
	}
	// TODO check if service is done ? STAND_BY : SERVICE
    };

    static Drinks actualDrink = null;
    
    @Override
    public boolean putCoins(VendingMachine machine, int coins) {
	return false;
    }

    @Override
    public boolean returnCoins(VendingMachine machine) {
	return false;
    }

    @Override
    public boolean selectDrink(VendingMachine machine, Drinks drink) {
	return false;
    }

    @Override
    public boolean makeDrink(VendingMachine machine) {
	return false;
    }

    @Override
    public boolean takeDrink(VendingMachine machine) {
	return false;
    }

    @Override
    public boolean service(VendingMachine machine) {
	return false;
    }

    @Override
    public boolean endService(VendingMachine machine) {
	return false;
    }

}
