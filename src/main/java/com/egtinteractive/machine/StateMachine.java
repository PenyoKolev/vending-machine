package com.egtinteractive.machine;

import com.egtinteractive.beverage.Drinks;
import com.egtinteractive.beverage.Products;
import com.egtinteractive.machine.VendingMachine.Ingredients;

public enum StateMachine {
  STAND_BY {
    @Override
    public int putCoins(VendingMachine machine, int coins) {
      if (coins < 0) {
        System.out.println("Negative coins are not accepted!");
        machine.setState(STAND_BY);
        return coins;
      } else {
        machine.setBalance(machine.getBalance() + coins);
        machine.setState(SELECT_ITEM);
        return coins;
      }
    }

    @Override
    public void service(VendingMachine machine) {
      machine.setState(SELECT_ITEM);
      machine.setState(SERVICE);
    }
  },
  SELECT_ITEM {
    @Override
    public Drinks selectItem(VendingMachine machine, Drinks drink) {
      if (machine.getBalance() < drink.getPrice()) {
        System.out.printf(
            "The price of %s is %d.\nPlease add %d.\n",
            drink, drink.getPrice(), drink.getPrice() - machine.getBalance());
        machine.setState(SELECT_ITEM);
        return drink;
      }

      if (machine.available().contains(drink)) {
        machine.setState(MAKE_ITEM);
        actualDrink = drink;
      } else if (!machine.available().isEmpty()) {
        System.out.printf("We are sorry! Out of %s\n", drink);
        System.out.println(machine.available());
        machine.setState(SELECT_ITEM);
      } else {
        System.out.println("Out of Service!");
        returnCoins(machine);
        machine.setState(SERVICE);
      }

      return drink;
    }

    @Override
    public Products selectItem(VendingMachine machine, String productName) {

      // TODO
      return null;
    }

    @Override
    public int putCoins(VendingMachine machine, int coins) {
      if (coins < 0) {
        System.out.println("Negative coins are not accepted!");
        machine.setState(STAND_BY);
        return coins;
      } else {
        machine.setBalance(machine.getBalance() + coins);
        machine.setState(SELECT_ITEM);
        return coins;
      }
    }
    /*
     * [WARNING] author ivailozd
     *
     * What is the point of returning the same value?
     *
     */
    //      return coins;
    //    }

    @Override
    public int returnCoins(VendingMachine machine) {
      int coins = machine.getBalance();
      System.out.printf("%d coins returned!\n", coins);
      /*
       * [WARNING] author ivailozd
       *
       * Why is this? Where is the profit?
       *
       */
      machine.setBalance(0);
      machine.setState(STAND_BY);
      return coins;
    }
  },
  MAKE_ITEM {
    @Override
    public Drinks makeDrink(VendingMachine machine) {
      machine.updateInventory(actualDrink);
      machine.setBalance(machine.getBalance() - actualDrink.getPrice());
      machine.setState(TAKE_ITEM);

      /*
       * [WARNING] author ivailozd
       *
       * If I got the drink here, why would I call takeDrink()?
       *
       */
      return actualDrink;
    }

    @Override
    public int returnCoins(VendingMachine machine) {
      int coins = machine.getBalance();
      System.out.printf("%d coins returned!\n", coins);
      machine.setBalance(0);
      machine.setState(STAND_BY);
      return coins;
    }
  },
  TAKE_ITEM {
    @Override
    public Drinks takeDrink(VendingMachine machine) {
      System.out.printf("Your %s is ready!\n", actualDrink);
      System.out.printf("%d coins returned!\n", machine.getBalance());
      machine.setBalance(0);
      machine.setState(STAND_BY);
      actualDrink = null;
      return actualDrink;
    }
  },
  SERVICE {
    @Override
    public void service(VendingMachine machine) {
      machine.setState(SERVICE);
    }

    @Override
    public void addProduct(VendingMachine machine, String name, int price, int quantity) {
      Products product = new Products(name, price);
      if (machine.getInventory().getProducts().size() + quantity > VendingMachine.DEFAULT_SIZE) {
        System.out.println("No free space!");
        return;
      }
      for (int i = 0; i < quantity; i++) {
        machine.getInventory().getProducts().add(product);
      }
    }

    @Override
    public void addProduct(VendingMachine machine, Ingredients ingredients, int quantity) {

      if (ingredients == Ingredients.COFFEE) {
        if (machine.getInventory().getCoffee() + quantity > VendingMachine.COFFEE_TANK) {
          System.out.println("Not enough space");
        } else {
          machine.getInventory().setCoffee(quantity);
        }
      }
      if (ingredients == Ingredients.MILK) {
        if (machine.getInventory().getMilk() + quantity > VendingMachine.MILK_TANK) {
          System.out.println("Not enough space");
        } else {
          machine.getInventory().setMilk(quantity);
        }
      }
    }

    @Override
    public void endService(VendingMachine machine) {
      machine.setState(STAND_BY);
    }
  };

  private static Drinks actualDrink = null;

  /*
   * [WARNING] author ivailozd
   *
   * Every state should implements its logic for the methods below
   *
   */

  public int putCoins(VendingMachine machine, int coins) {
    return coins;
  }

  public int returnCoins(VendingMachine machine) {
    return 0;
  }

  public Drinks selectItem(VendingMachine machine, Drinks drink) {
    return null;
  }

  public Products selectItem(VendingMachine machine, String productName) {
    return null;
  }

  public Drinks makeDrink(VendingMachine machine) {
    return null;
  }

  public Drinks takeDrink(VendingMachine machine) {
    return null;
  }

  public void service(VendingMachine machine) {}

  public void endService(VendingMachine machine) {}

  public void addProduct(VendingMachine machine, String name, int price, int quantity) {}

  public void addProduct(VendingMachine machine, Ingredients ingredients, int quantity) {}
}
