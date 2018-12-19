package servicetest;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.egtinteractive.vendingMachine.Provider;

import machine.VendingMachine;

public class EndServiceTest {
  
    @DataProvider(name = "vendingMachine")
    public Object[][] getData() {
	return Provider.serviceMachine();
    }

    @Test(dataProvider = "vendingMachine")
    public void endServiceShouldChangeStateToSTAND_BY(VendingMachine machine) {
	// Act
	machine.service();
	machine.endService();
	
	// Assert
	assertEquals(machine.getState().toString(), "STAND_BY");
    }
}
