package pizzaworld.logic;

import java.util.ArrayList;

/**
 *
 * @author André Heinen
 */
public class Statistics {
    
    private final ArrayList<ArrayList<Integer>> soldUnits;

    private final int[][] guests;
    private final double[][] sales;

    public Statistics(Game game) {
        
        soldUnits = new ArrayList<>();
        for (int i = 0; i < game.getProducts().getDishes().size(); i++) {
            soldUnits.add(new ArrayList<>());
            soldUnits.get(i).add(0);
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

    public ArrayList<ArrayList<Integer>> getSoldUnits() {
        return soldUnits;
    }

    public int[][] getGuests() {
        return guests;
    }

    public double getSales(int period) {
        double amount = 0.0;
        for (int i = 0; i < sales.length; i++) {
            amount += sales[i][period];
        }
        return amount;
    }

    public void incSoldUnits(int day, int dish) {
        if (soldUnits.get(0).size() <= day) {
            for (int i = 0; i < soldUnits.size(); i++) {
                soldUnits.get(i).add(0);
            }
        }
        soldUnits.get(dish).set(day, (soldUnits.get(dish).get(day) + 1));
    }
    
    public int getSoldUnitsYesterday(int dish, int day) {
        return soldUnits.get(dish).get(day - 1);
    }
    
    public int getSoldUnitsYesterdayTotal(int day) {
        int total = 0;
        for (int i = 0; i < soldUnits.size(); i++) {
            total += getSoldUnitsYesterday(i, day);
        }
        return total;
    }
    
    public int getSoldUnitsWeek(int dish, int day) {
        int units = 0;
        int startingDay;
        if ((day - 7) < 0) {
            startingDay = 0;
        } else {
            startingDay = day - 7;
        }
        for (int i = startingDay; i < day; i++) {
            units += getSoldUnits().get(dish).get(i);
        }
        return units;
    }
    
    public int getSoldUnitsWeekTotal(int day) {
        int total = 0;
        for (int i = 0; i < soldUnits.size(); i++) {
            total += getSoldUnitsWeek(i, day);
        }
        return total;
    }
    
    public int getSoldUnitsAllTime(int dish, int day) {
        int units = 0;
        for (int i = 0; i < day; i++) {
            units += getSoldUnits().get(dish).get(i);
        }
        return units;
    }
    
    public int getSoldUnitsAllTimeTotal(int day) {
        int total = 0;
        for (int i = 0; i < soldUnits.size(); i++) {
            total += getSoldUnitsAllTime(i, day);
        }
        return total;
    }

    public void incGuests(int status) {
        guests[status][0]++;
        guests[status][1]++;
        guests[status][2]++;
    }

    public void incSales(int dish, double price) {
        sales[dish][0] += price;
        sales[dish][1] += price;
        sales[dish][2] += price;
    }
}