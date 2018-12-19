package selectitemtest;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.egtinteractive.vendingMachine.Provider;

import beverage.Drinks;
import machine.VendingMachine;

public class SelectDrinkTest {

    @DataProvider(name = "vendingMachine")
    public Object[][] getData() {
	return Provider.selectItemMachine();
    }

    @Test(dataProvider = "vendingMachine")
    public void selectDrinkShouldChangeStateToMAKE_ITEM(VendingMachine machine) {
	// Act
	machine.selectDrink(Drinks.CAPUCCINO);

	// Assert
	assertEquals(machine.getState().toString(), "MAKE_ITEM");
    }
}
