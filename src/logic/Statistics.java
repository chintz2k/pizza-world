package logic;

import java.util.ArrayList;

/**
 *
 * @author André Heinen
 */
public class Statistics {
    
    private final ArrayList<ArrayList<Integer>> soldUnits;
    private final ArrayList<ArrayList<Integer>> sales;

    public Statistics() {
        soldUnits = new ArrayList<>();
        sales = new ArrayList<>();
        for (int i = 0; i < Game.NUMBER_OF_DISHES; i++) {
            soldUnits.add(new ArrayList<>());
            soldUnits.get(i).add(0);
            soldUnits.get(i).add(0);
            sales.add(new ArrayList<>());
            sales.get(i).add(0);
            sales.get(i).add(0);
        }
    }

    public void addColumn() {
        for (int i = 0; i < soldUnits.size(); i++) {
            soldUnits.get(i).add(0);
            sales.get(i).add(0);
        }
    }

    public void addSoldUnits(int dish, int day, int amount) {
        soldUnits.get(dish).set(day, (soldUnits.get(dish).get(day) + amount));
    }
    
    public void addSales(int dish, int day, int amount) {
        sales.get(dish).set(day, (sales.get(dish).get(day) + amount));
    }
    
    public ArrayList<ArrayList<Integer>> getSoldUnits() {
        return soldUnits;
    }

    public int getSoldUnits(int dish, int day) {
        return soldUnits.get(dish).get(day);
    }
    
    public int getSoldUnitsTotal(int day) {
        int total = 0;
        for (int i = 0; i < soldUnits.size(); i++) {
            total += getSoldUnits(i, day);
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

    public int getSales(int dish, int day) {
        return sales.get(dish).get(day);
    }
    
    public int getSalesTotal(int day) {
        int total = 0;
        for (int i = 0; i < sales.size(); i++) {
            total += getSales(i, day);
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