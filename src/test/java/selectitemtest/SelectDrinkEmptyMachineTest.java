package selectitemtest;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.egtinteractive.vendingMachine.Provider;

import beverage.Drinks;
import machine.VendingMachine;

public class SelectDrinkEmptyMachineTest {
 
    @DataProvider(name = "vendingMachine")
    public Object[][] getData() {
	return Provider.selectItemEmptyMachine();
    }

    @Test(dataProvider = "vendingMachine")
    public void selectDrinkShouldReturnMoneyIfMachineEmpty(VendingMachine machine) {
	// Act
	machine.selectDrink(Drinks.CAPUCCINO);

	// Assert
	assertEquals(machine.getInventory().getBalance(), 0);
    }
    
    @Test(dataProvider = "vendingMachine")
    public void selectDrinkShouldChangeStateToSERVICEifMachineEmpty(VendingMachine machine) {
	// Act
	machine.selectDrink(Drinks.CAPUCCINO);

	// Assert
	assertEquals(machine.getState().toString(), "SERVICE");
    }
}
