package takeitemtest;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.egtinteractive.vendingMachine.Provider;

import machine.VendingMachine;

public class TakeDrinkTest {
  
    @DataProvider(name = "vendingMachine")
    public Object[][] getData() {
	return Provider.takeItemMachine();
    }

    @Test(dataProvider = "vendingMachine")
    public void takeDrinkShouldChangeStateToSTAND_BY(VendingMachine machine) {
	// Act
	machine.takeDrink();

	// Assert
	assertEquals(machine.getState().toString(), "STAND_BY");
    }
}
