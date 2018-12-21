package machine;

import beverage.Drinks;
import beverage.Products;
import machine.VendingMachine.Ingredients;

public enum StateMachine implements Machine {
  STAND_BY {
    @Override
    public int putCoins(VendingMachine machine, int coins) {
      /*
       * [WARNING] author ivailozd
       *
       * What if negative value is inserted?
       *
       */
      machine.setBalance(machine.getBalance() + coins);
      machine.setState(SELECT_ITEM);
      return coins;
    }

    @Override
    public void service(VendingMachine machine) {
      machine.fillUpInventory();
      machine.setState(SERVICE);
    }
  },
  SELECT_ITEM {
    @Override
    public Drinks selectDrink(VendingMachine machine, Drinks drink) {
      //      if (machine.disponibles().contains(drink)) {
      //        machine.setState(MAKE_ITEM);
      //        actualDrink = drink;
      //      } else if (!machine.disponibles().isEmpty()) {
      //        System.out.printf("We are sorry! Out of %s\n", drink);
      //        System.out.println(machine.disponibles());
      //        machine.setState(SELECT_ITEM);
      //      } else {
      //        System.out.println("Out of Service!");
      //        returnCoins(machine);
      //        machine.setState(SERVICE);
      //      }
      return drink;
    }

    @Override
    public int putCoins(VendingMachine machine, int coins) {
      machine.setBalance(machine.getBalance() + coins);
      machine.setState(SELECT_ITEM);
      /*
       * [WARNING] author ivailozd
       *
       * What is the point of returning the same value?
       *
       */
      return coins;
    }

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
      /*
       * [WARNING] author ivailozd
       *
       * Validations should be made as soon as possible
       *
       */
      if (machine.getBalance() < actualDrink.getPrice()) {
        System.out.printf(
            "The price of %s is %d.\nPlease add %d.\n",
            actualDrink, actualDrink.getPrice(), actualDrink.getPrice() - machine.getBalance());
        machine.setState(MAKE_ITEM);
        return actualDrink;
      }
      //      actualDrink.create(); // should refactor this
      machine.setState(TAKE_ITEM);

      /*
       * [WARNING] author ivailozd
       *
       * If I got the drink here, why would I call takeDrink()?
       *
       */
      return actualDrink;
    }

    /*
     * [WARNING] author ivailozd
     *
     * Why does the machine accept money in MAKE_ITEM state?
     *
     */
    @Override
    public int putCoins(VendingMachine machine, int coins) {
      machine.setBalance(machine.getBalance() + coins);
      machine.setState(MAKE_ITEM);
      return coins;
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
      returnCoins(machine);
      machine.setState(STAND_BY);
      actualDrink = null;
      return actualDrink;
    }

    /*
     * [WARNING] author ivailozd
     *
     * The item has been already sold, the machine can't return the money!
     *
     */
    @Override
    public int returnCoins(VendingMachine machine) {
      int coins = machine.getBalance();
      System.out.printf("%d coins returned!\n", coins);
      machine.setBalance(0);
      machine.setState(STAND_BY);
      return coins;
    }
  },
  SERVICE {
    @Override
    public void service(VendingMachine machine) {
      machine.fillUpInventory();
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

  /*
   * [WARNING] author ivailozd
   *
   * Why are these static fields?
   *
   */
  // remove inventory & machine fields
  private static Drinks actualDrink = null;

  /*
   * [WARNING] author ivailozd
   *
   * Every state should implements its logic for the methods below
   *
   */

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
  public void service(VendingMachine machine) {}

  @Override
  public void endService(VendingMachine machine) {}

  @Override
  public void addProduct(VendingMachine machine, String name, int price, int quantity) {}

  @Override
  public void addProduct(VendingMachine machine, Ingredients ingredients, int quantity) {}
}
