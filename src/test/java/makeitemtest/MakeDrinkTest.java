package makeitemtest;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.egtinteractive.vendingMachine.Provider;

import machine.VendingMachine;

public class MakeDrinkTest {

    @DataProvider(name = "vendingMachine")
    public Object[][] getData() {
	return Provider.makeItemMachine();
    }

    @Test(dataProvider = "vendingMachine")
    public void makeDrinkShouldChangeStateToTAKE_ITEM(VendingMachine machine) {	
	// Act
	machine.makeDrink();

	// Assert
	assertEquals(machine.getState().toString(), "TAKE_ITEM");
	machine = null;
    }
}
