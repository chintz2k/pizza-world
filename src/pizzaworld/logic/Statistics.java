package pizzaworld.logic;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author André Heinen
 */
public class Statistics implements Serializable {
    
    /*
        TODO guests[] ist einzig und allein fürs debuggen
    */
    private final ArrayList<ArrayList<Integer>> soldUnits;
    private final ArrayList<ArrayList<Double>> sales;

    public Statistics(Game game) {
        soldUnits = new ArrayList<>();
        sales = new ArrayList<>();
        for (int i = 0; i < game.getProducts().getDishes().size(); i++) {
            soldUnits.add(new ArrayList<>());
            soldUnits.get(i).add(0);
            soldUnits.get(i).add(0);
            sales.add(new ArrayList<>());
            sales.get(i).add(0.0);
            sales.get(i).add(0.0);
        }
    }

    public void newDay() {
        for (int i = 0; i < soldUnits.size(); i++) {
            soldUnits.get(i).add(0);
            sales.get(i).add(0.0);
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
    
    public ArrayList<ArrayList<Double>> getSales() {
        return sales;
    } 

    public void incSales(int day, int dish, double price) {
        sales.get(dish).set(day, (sales.get(dish).get(day) + price));
    }
    
    public double getSalesYesterday(int dish, int day) {
        return sales.get(dish).get(day - 1);
    }
    
    public double getSalesYesterdayTotal(int day) {
        double total = 0.0;
        for (int i = 0; i < sales.size(); i++) {
            total += getSalesYesterday(i, day);
        }
        return total;
    }
    
    public double getSalesWeek(int dish, int day) {
        double units = 0.0;
        int startingDay;
        if ((day - 7) < 0) {
            startingDay = 0;
        } else {
            startingDay = day - 7;
        }
        for (int i = startingDay; i < day; i++) {
            units += getSales().get(dish).get(i);
        }
        return units;
    }
    
    public double getSalesWeekTotal(int day) {
        double total = 0;
        for (int i = 0; i < sales.size(); i++) {
            total += getSalesWeek(i, day);
        }
        return total;
    }
    
    public double getSalesAllTime(int dish, int day) {
        double units = 0;
        for (int i = 0; i < day; i++) {
            units += getSales().get(dish).get(i);
        }
        return units;
    }
    
    public double getSalesAllTimeTotal(int day) {
        double total = 0;
        for (int i = 0; i < sales.size(); i++) {
            total += getSalesAllTime(i, day);
        }
        return total;
    }
}