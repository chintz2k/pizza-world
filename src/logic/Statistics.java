package logic;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author André Heinen
 */
public class Statistics implements Serializable {
    
    private final ArrayList<ArrayList<Integer>> soldUnits;
    private final ArrayList<ArrayList<Integer>> sales;

    public Statistics(Game game) {
        soldUnits = new ArrayList<>();
        sales = new ArrayList<>();
        for (int i = 0; i < game.getProducts().getDishes().size(); i++) {
            soldUnits.add(new ArrayList<>());
            soldUnits.get(i).add(0);
            soldUnits.get(i).add(0);
            sales.add(new ArrayList<>());
            sales.get(i).add(0);
            sales.get(i).add(0);
        }
    }

    public void newDay() {
        for (int i = 0; i < soldUnits.size(); i++) {
            soldUnits.get(i).add(0);
            sales.get(i).add(0);
        }
    }

    public ArrayList<ArrayList<Integer>> getSoldUnits() {
        return soldUnits;
    }

    public void incSoldUnits(int day, int dish) {
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
    
    public ArrayList<ArrayList<Integer>> getSales() {
        return sales;
    } 

    public void incSales(int day, int dish, int price) {
        sales.get(dish).set(day, (sales.get(dish).get(day) + price));
    }
    
    public int getSalesYesterday(int dish, int day) {
        return sales.get(dish).get(day - 1);
    }
    
    public int getSalesYesterdayTotal(int day) {
        int total = 0;
        for (int i = 0; i < sales.size(); i++) {
            total += getSalesYesterday(i, day);
        }
        return total;
    }
    
    public int getSalesAllTime(int dish, int day) {
        int units = 0;
        for (int i = 0; i < day; i++) {
            units += getSales().get(dish).get(i);
        }
        return units;
    }
    
    public int getSalesAllTimeTotal(int day) {
        int total = 0;
        for (int i = 0; i < sales.size(); i++) {
            total += getSalesAllTime(i, day);
        }
        return total;
    }
}