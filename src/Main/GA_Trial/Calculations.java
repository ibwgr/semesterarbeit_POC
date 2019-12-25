package GA_Trial;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Calculations {


    public static final double GAPreis = 5000;

    public Calculations(DataSource ds) {
    }

    public static List showAll(){
        ArrayList<Reise> ergebnis = new SQL_Persistence().getReise();
        return ergebnis;
    }

    public static int totalCost() {
        int totalCost = new SQL_Persistence().getPreise();
        return totalCost;
    }

    public static int gaRelation(){
        int sum = new SQL_Persistence().getPreise();
        int gaRelation = (int)(sum*100/GAPreis)-100;
        return gaRelation;
    }
}
