package pizzaworld.logic;

import java.io.Serializable;

/**
 *
 * @author André Heinen
 */
public class Statistics implements Serializable {

    private final int[][] soldUnits;
    private final int[][] guests;
    private final double[][] sales;

    public Statistics(Game game) {
        
        soldUnits = new int[game.getProducts().getDishes().size()][3];
        for (int i = 0; i < soldUnits.length; i++) {
            for (int j = 0; j < soldUnits[i].length; j++) {
                soldUnits[i][j] = 0;
            }
        }

        guests = new int[2][3];
        for (int i = 0; i < guests.length; i++) {
            for (int j = 0; j < guests[i].length; j++) {
                guests[i][j] = 0;
            }
        }

        sales = new double[game.getProducts().getDishes().size()][3];
        for (int i = 0; i < sales.length; i++) {
            for (int j = 0; j < sales[i].length; j++) {
                sales[i][j] = 0.0;
            }
        }
    }

    /**
     *
     * @param period 0 = today, 1 = month, 2 = alltime
     * @return
     */
    public int getSoldUnits(int period) {
        int units = 0;
        for (int i = 0; i < soldUnits.length; i++) {
            units += soldUnits[i][period];
        }
        return units;
    }

    public int[][] getSoldUnits() {
        return soldUnits;
    }

    /**
     * [0 = served, 1 = NOT served][0 = today, 1 = month, 2 = alltime]
     *
     * @return
     */
    public int[][] getGuests() {
        return guests;
    }

    /**
     * @param period [0 = today, 1 = month, 2 = alltime]
     * @return
     */
    public double getSales(int period) {
        double amount = 0.0;
        for (int i = 0; i < sales.length; i++) {
            amount += sales[i][period];
        }
        return amount;
    }

    /**
     * 
     * @param dish the dishnumber 0-15
     */
    public void incSoldUnits(int dish) {
        soldUnits[dish][0]++;
        soldUnits[dish][1]++;
        soldUnits[dish][2]++;
    }

    /**
     * 
     * @param status 0 = served, 1 = not served
     */
    public void incGuests(int status) {
        guests[status][0]++;
        guests[status][1]++;
        guests[status][2]++;
    }

    /**
     * 
     * @param dish dishnumber 0-15
     * @param price the price of the dish
     */
    public void incSales(int dish, double price) {
        sales[dish][0] += price;
        sales[dish][1] += price;
        sales[dish][2] += price;
    }

    public void resetDailyStatistics() {
        for (int i = 0; i < soldUnits.length; i++) {
            soldUnits[i][0] = 0;
        }
        for (int i = 0; i < guests.length; i++) {
            guests[i][0] = 0;
        }
        for (int i = 0; i < sales.length; i++) {
            sales[i][0] = 0.0;
        }
    }

    public void endMonth() {
        for (int i = 0; i < soldUnits.length; i++) {
            soldUnits[i][0] = 0;
            soldUnits[i][1] = 0;
        }
        for (int i = 0; i < guests.length; i++) {
            guests[i][0] = 0;
            guests[i][1] = 0;
        }
        for (int i = 0; i < sales.length; i++) {
            sales[i][0] = 0.0;
            sales[i][1] = 0.0;
        }
    }

}
