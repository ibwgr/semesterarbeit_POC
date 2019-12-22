package GA_Trial;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Calculations {

    public static final double GAPreis = 5000;

    public static List showAll(){
        ArrayList<Reise> ergebnis = new SQL_Persistence().getReise();
        return ergebnis;
    }

    public static int totalCost() {
        int totalCost = new SQL_Persistence().getPreise();
        return totalCost;
    }

    public static double gaRelation(){
        int sum = new SQL_Persistence().getPreise();
        double gaRelation = Math.round((sum/GAPreis*100) * 100.0) / 100.0;
        return gaRelation;
    }
}
