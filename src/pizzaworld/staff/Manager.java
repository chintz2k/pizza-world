package pizzaworld.staff;

/**
 *
 * @author andre
 */
public class Manager {
    
    private int money;
    
    public Manager() {
        money = 1000;
    }
    
    public int getMoney() {
        return money;
    }
    
    public void modMoney(int amount) {
        money += amount;
    }
}
