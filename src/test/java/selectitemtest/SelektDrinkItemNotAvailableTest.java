package selectitemtest;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.egtinteractive.vendingMachine.Provider;

import beverage.Drinks;
import machine.VendingMachine;

public class SelektDrinkItemNotAvailableTest {
  
    @DataProvider(name = "vendingMachine")
    public Object[][] getData() {
	return Provider.selectItemNotAvailableMachine();
    }

    @Test(dataProvider = "vendingMachine")
    public void selectDrinkShouldNotChangeStateIfItemNotAvailable(VendingMachine machine) {
	// Act
	machine.selectDrink(Drinks.CAPUCCINO);
	

	// Assert
	assertEquals(machine.getState().toString(), "SELECT_ITEM");
    }
}
