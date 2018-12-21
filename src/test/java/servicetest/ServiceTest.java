package servicetest;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.egtinteractive.vendingMachine.Provider;

import inventory.Inventory;
import machine.VendingMachine;

public class ServiceTest {
  
//    @DataProvider(name = "vendingMachine")
//    public Object[][] getData() {
//	return Provider.serviceMachine();
//    }
//
//    @Test(dataProvider = "vendingMachine")
//    public void serviceShouldFillUpInventory(VendingMachine machine) {
//	// Arrange
//	Inventory inventory = Inventory.INSTANCE;
//
//	// Act
//	machine.service();
//
//	// Assert
//	assertEquals(inventory.getSlots(), 1);
//	assertEquals(inventory.getCoffee(), 2);
//	assertEquals(inventory.getMilk(), 2);
//    }
}
