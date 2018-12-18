package machine;

import beverage.Drinks;

public interface Machine {

    public boolean putCoins(VendingMachine machine, int coins);

    public boolean returnCoins(VendingMachine machine);

    public boolean selectDrink(VendingMachine machine, Drinks drink);

    public boolean makeDrink(VendingMachine machine);

    public boolean takeDrink(VendingMachine machine);

    public boolean service(VendingMachine machine);
    
    public boolean endService(VendingMachine machine);

}
