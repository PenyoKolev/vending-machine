package takeitemtest;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.egtinteractive.vendingMachine.Provider;

import machine.VendingMachine;

public class ReturnCoinsTest {
  
    @DataProvider(name = "vendingMachine")
    public Object[][] getData() {
	return Provider.makeItemMachine();
    }

    @Test(dataProvider = "vendingMachine")
    public void returnCoinsShouldChangeStateToSTAND_BY(VendingMachine machine) {
	// Act
	machine.returnCoins();

	// Assert
	assertEquals(machine.getState().toString(), "STAND_BY");
    }
}
