package com.egtinteractive.machine;

import com.egtinteractive.beverage.Drinks;
import com.egtinteractive.beverage.Articles;
import com.egtinteractive.beverage.Products;
import com.egtinteractive.inventory.Inventory;
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
      machine.setState(SERVICE);
    }
  },
  SELECT_ITEM {
    @Override
    public Drinks selectItem(VendingMachine machine, Inventory inventory, Drinks drink) {
      if (machine.getBalance() < drink.getPrice()) {
        System.out.printf(
            "The price of %s is %d.\nPlease add %d.\n",
            drink, drink.getPrice(), drink.getPrice() - machine.getBalance());
        machine.setState(SELECT_ITEM);
        return drink;
      }
      if (machine.available().contains(drink)) {
        machine.setState(MAKE_ITEM);
        machine.setArticle(drink);
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
    public Products selectItem(VendingMachine machine, Inventory inventory, String productName) {
      Products product = inventory.getProductByName(productName);
      if (product == null) {
        System.out.printf("We are sorry! Out of %s\n", productName);
        machine.setState(SELECT_ITEM);
        return product;
      }
      if (machine.getBalance() < product.getPrice()) {
        System.out.printf(
            "The price of %s is %d.\nPlease add %d.\n",
            product.getName(), product.getPrice(), product.getPrice() - machine.getBalance());
        machine.setState(SELECT_ITEM);
        return product;
      }
      machine.setArticle(product);
      machine.setState(MAKE_ITEM);
      return product;
    }

    @Override
    public int putCoins(VendingMachine machine, int coins) {
      if (coins < 0) {
        System.out.println("Negative coins are not accepted!");
        machine.setState(SELECT_ITEM);
        return machine.getBalance();
      } else {
        machine.setBalance(machine.getBalance() + coins);
        machine.setState(SELECT_ITEM);
        return machine.getBalance();
      }
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
  MAKE_ITEM {
    @Override
    public void makeItem(VendingMachine machine) {
      machine.updateInventory(machine.getArticle());
      machine.setBalance(machine.getBalance() - machine.getArticle().getPrice());
      machine.setState(TAKE_ITEM);
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
    public Articles takeItem(VendingMachine machine) {
      System.out.printf("Your %s is ready!\n", machine.getArticle().getName());
      System.out.printf("%d coins returned!\n", machine.getBalance());
      machine.setBalance(0);
      machine.setState(STAND_BY);
      machine.setArticle(null);
      return machine.getArticle();
    }
  },
  SERVICE {
    @Override
    public void service(VendingMachine machine) {
      machine.setState(SERVICE);
    }

    @Override
    public void addProduct(
        VendingMachine machine, String name, Inventory inventory, int price, int quantity) {
      Products product = new Products(name, price);
      if (inventory.getProducts().size() + quantity > inventory.DEFAULT_SIZE) {
        System.out.printf(
            "Only %d articles can be added!\n",
            inventory.DEFAULT_SIZE - inventory.getProducts().size());
        return;
      }
      for (int i = 0; i < quantity; i++) {
        inventory.getProducts().add(product);
      }
    }

    @Override
    public void addProduct(
        VendingMachine machine, Ingredients ingredients, Inventory inventory, int quantity) {
      if (ingredients == Ingredients.COFFEE) {
        if (inventory.getCoffee() + quantity > inventory.COFFEE_TANK) {
          System.out.printf(
              "Only %d coffee can be added!\n", inventory.COFFEE_TANK - inventory.getCoffee());
        } else {
          inventory.setCoffee(quantity);
        }
      }
      if (ingredients == Ingredients.MILK) {
        if (inventory.getMilk() + quantity > inventory.MILK_TANK) {
          System.out.printf(
              "Only %d milk can be added!\n", inventory.MILK_TANK - inventory.getMilk());
        } else {
          inventory.setMilk(quantity);
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
   * Every state should implements its logic for the methods below
   *
   */

  public int putCoins(VendingMachine machine, int coins) {
    System.out.println("Coins are not accepted in current state!" ); 
    return coins;
  }

  public int returnCoins(VendingMachine machine) {
    System.out.println("Machine cannot return coins in current state!" ); 
    return 0;
  }

  public Drinks selectItem(VendingMachine machine, Inventory inventory, Drinks drink) {
    System.out.println("Item cannot be selected in current state!" ); 
    return null;
  }

  public Products selectItem(VendingMachine machine, Inventory inventory, String productName) {
    System.out.println("Item cannot be selected in current state!" ); 
    return null;
  }

  public void makeItem(VendingMachine machine) {
    System.out.println("Machine cannot make item in current state!" ); 
  }

  public Articles takeItem(VendingMachine machine) {
    System.out.println("Item cannot be taken in current state!" ); 
    return null;
  }

  public void service(VendingMachine machine) {
    System.out.println("Service not accepted in current state!" ); 
  }

  public void endService(VendingMachine machine) {
    System.out.println("End service not accepted in current state!" ); 
  }

  public void addProduct(
      VendingMachine machine, String name, Inventory inventory, int price, int quantity) {
    System.out.println("Products cannot be added in current state" ); 
  }

  public void addProduct(
      VendingMachine machine, Ingredients ingredients, Inventory inventory, int quantity) {
    System.out.println("Products cannot be added in current state" ); 
  }
}
