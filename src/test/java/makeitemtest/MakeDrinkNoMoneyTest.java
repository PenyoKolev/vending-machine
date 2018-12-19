package makeitemtest;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.egtinteractive.vendingMachine.Provider;

import machine.VendingMachine;

public class MakeDrinkNoMoneyTest {

    @DataProvider(name = "vendingMachine")
    public Object[][] getData() {
	return Provider.makeItemNotEnoughMoneyMachine();
    }

    @Test(dataProvider = "vendingMachine")
    public void makeDrinkShouldNotChangeStateIfNotEnoughCoins(VendingMachine machine) {
	// Act
	machine.makeDrink();

	// Assert
	assertEquals(machine.getState().toString(), "MAKE_ITEM");
    }
}
