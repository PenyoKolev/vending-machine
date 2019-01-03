package com.egtinteractive.machine;

import com.egtinteractive.beverage.Drinks;
import com.egtinteractive.beverage.Articles;
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
        article = drink;
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
      Products product = machine.getInventory().getProductByName(productName);
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
      article = product;
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
      machine.updateInventory(article);
      machine.setBalance(machine.getBalance() - article.getPrice());
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
      System.out.printf("Your %s is ready!\n", article.getName());
      System.out.printf("%d coins returned!\n", machine.getBalance());
      machine.setBalance(0);
      machine.setState(STAND_BY);
      article = null;
      return article;
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
        System.out.printf(
            "Only %d articles can be added!\n",
            VendingMachine.DEFAULT_SIZE - machine.getInventory().getProducts().size());
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
          System.out.printf(
              "Only %d coffee can be added!\n",
              VendingMachine.COFFEE_TANK - machine.getInventory().getCoffee());
        } else {
          machine.getInventory().setCoffee(quantity);
        }
      }
      if (ingredients == Ingredients.MILK) {
        if (machine.getInventory().getMilk() + quantity > VendingMachine.MILK_TANK) {
          System.out.printf(
              "Only %d milk can be added!\n",
              VendingMachine.MILK_TANK - machine.getInventory().getMilk());
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

  private static Articles article;

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

  public void makeItem(VendingMachine machine) {}

  public Articles takeItem(VendingMachine machine) {
    return null;
  }

  public void service(VendingMachine machine) {}

  public void endService(VendingMachine machine) {}

  public void addProduct(VendingMachine machine, String name, int price, int quantity) {}

  public void addProduct(VendingMachine machine, Ingredients ingredients, int quantity) {}
}
