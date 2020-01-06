package GA_Trial;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

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

    // Methode für Monatsabfrage
    public static String showMonth (String month){
        String returnMonth = "";
        switch (month){
            case "Januar":
                returnMonth = "%-01-%";
                break;
            case "Februar":
                returnMonth = "%-02-%";
                break;
            case "März":
                returnMonth = "%-03-%";
                break;
            case "April":
                returnMonth = "%-04-%";
                break;
            case "Mai":
                returnMonth = "%-05-%";
                break;
            case "Juni":
                returnMonth = "%-06-%";
                break;
            case "Juli":
                returnMonth = "%-07-%";
                break;
            case "August":
                returnMonth = "%-08-%";
                break;
            case "September":
                returnMonth = "%-09-%";
                break;
            case "Oktober":
                returnMonth = "%-10-%";
                break;
            case "November":
                returnMonth = "%-11-%";
                break;
            case "Dezember":
                returnMonth = "%-12-%";
                break;
            default:
                break;
        }
        return returnMonth;
    }
}