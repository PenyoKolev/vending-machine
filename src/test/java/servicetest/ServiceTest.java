package servicetest;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.egtinteractive.vendingMachine.Provider;

import machine.VendingMachine;

public class ServiceTest {
  
    @DataProvider(name = "vendingMachine")
    public Object[][] getData() {
	return Provider.serviceMachine();
    }

    @Test(dataProvider = "vendingMachine")
    public void serviceShouldFillUpInventory(VendingMachine machine) {
	// Act
	machine.service();

	// Assert
	assertEquals(machine.getInventory().getSlots(), 1);
	assertEquals(machine.getInventory().getCoffee(), 2);
	assertEquals(machine.getInventory().getMilk(), 2);
    }
}
