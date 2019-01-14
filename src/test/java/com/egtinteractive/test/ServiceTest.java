package com.egtinteractive.test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.egtinteractive.machine.VendingMachine;
import com.egtinteractive.machine.VendingMachine.Ingredients;
import com.egtinteractive.provider.Provider;

public class ServiceTest {

  @DataProvider(name = "service")
  public Object[][] machine() {
    return Provider.serviceMachine();
  }

  @Test(dataProvider = "service")
  public void serviceShouldNotChangeState(VendingMachine machine) {
    // Act
    machine.service();

    // Assert
    assertEquals(machine.getStateName(), "SERVICE");
  }

  @Test(dataProvider = "service")
  public void addProductShouldAddToProducts(VendingMachine machine) {
    // Arrange
    String name = "Lemonade";
    int price = 2;
    int quantity = 6;

    // Act
    machine.addProduct(name, price, quantity);
    
    // Assert
    assertEquals(machine.getInventory().getSize(), quantity);
  }

  @Test(dataProvider = "service")
  public void addProductShouldNotAddToProductsIfQuantityBiggerThanDEFAULT_SIZE(
      VendingMachine machine) {
    // Act
    String name = "Lemonade";
    int price = 2;
    int quantity = 20;
    machine.addProduct(name, price, quantity);

    // Assert
    assertEquals(machine.getInventory().getProducts().size(), 0);
  }

  @Test(dataProvider = "service")
  public void addProductShouldAddIngredients(VendingMachine machine) {
    // Arrange
    int quantity = 5;
    // Act
    machine.addProduct(Ingredients.COFFEE, quantity);
    machine.addProduct(Ingredients.MILK, quantity);

    // Assert
    assertEquals(machine.getInventory().getCoffee(), quantity);
    assertEquals(machine.getInventory().getMilk(), quantity);
  }

  @Test(dataProvider = "service")
  public void addProductShouldNotAddIngredientsIfQuantityBiggerThanTANK_SIZE(
      VendingMachine machine) {
    // Arrange
    int quantity = 20;
    // Act
    machine.addProduct(Ingredients.COFFEE, quantity);
    machine.addProduct(Ingredients.MILK, quantity);

    // Assert
    assertEquals(machine.getInventory().getCoffee(), 0);
    assertEquals(machine.getInventory().getMilk(), 0);
  }
  
  @Test(dataProvider = "service")
  public void addProductShouldNotAddIfQuantityEqualOrLessThanOne(VendingMachine machine) {   //TODO
    // Arrange
    String name = "Lemonade";
    int price = 2;
    int quantity = 0;
    // Act
    machine.addProduct(name, price, quantity);

    // Assert
    assertFalse(machine.getInventory().getProducts().containsKey(name));
  }

  @Test(dataProvider = "service")
  public void endServiceShouldChangeStateToSTAND_BY(VendingMachine machine) {
    // Act
    machine.endService();

    // Assert
    assertEquals(machine.getStateName(), "STAND_BY");
  }
  
  @Test(dataProvider = "service", expectedExceptions = IllegalStateException.class)
  public void methodUnsuportedForTheStateShouldDoNothing(VendingMachine machine) {
    // Act
    machine.makeItem();
  }
}
