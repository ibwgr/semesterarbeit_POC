package GA_Trial;

import java.util.ArrayList;
import java.util.List;

public class Calculations {


    public static double gaPreis = 5265;
    public static double gaPerMonth = Math.round((gaPreis/12) * 100.0) / 100.0;
    public static double bern = 127.00;
    public static double olten = 116.70;
    public static double zurich = 81.90;
    public static double andererZielort = 0.00;


    public static List showAll(){
        ArrayList<Reise> ergebnis = new SQL_Persistence().getTrip();
        return ergebnis;
    }

    public static double totalCost() {
        double totalCost = new SQL_Persistence().getPrices();
        return totalCost;
    }

    public static int gaRelation(){
        double sum = new SQL_Persistence().getPrices();
        int gaRelation = (int)(sum*100/ gaPreis)-100;
        return gaRelation;
    }

    // method-overloading for unitTesting
    public int gaRelation(double x){
        x = new SQL_Persistence().getPrices();
        int gaRelation = (int)(x*100/ gaPreis)-100;
        return gaRelation;
    }
}