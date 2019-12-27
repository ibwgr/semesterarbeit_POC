package GA_Trial;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

public class Calculations {


    public static double gaPreis = 5265;
    public static double gaPerMonth = Math.round((gaPreis/12) * 100.0) / 100.0;
    public static double bern = 127.00;
    public static double olten = 116.70;
    public static double zurich = 81.90;
    public static double andererZielort = 0.00;


    public Calculations(DataSource ds) {
    }

    public static List showAll(){
        ArrayList<Reise> ergebnis = new SQL_Persistence().getReise();
        return ergebnis;
    }

    public static double totalCost() {
        double totalCost = new SQL_Persistence().getPreise();
        return totalCost;
    }

    public static int gaRelation(){
        double sum = new SQL_Persistence().getPreise();
        int gaRelation = (int)(sum*100/ gaPreis)-100;
        return gaRelation;
    }
}
