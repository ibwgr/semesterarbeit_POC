package GA_Trial;


public class Calculations {

    public Calculations(Persistence persistence) {
        this.persistence = persistence;
    }


    public static double gaPreis = 5265;
    public static double gaPerMonth = Math.round((gaPreis/12) * 100.0) / 100.0;

    private Persistence persistence;


    public double totalCost() {
        double totalCost = persistence.getPrices();
        return totalCost;
    }

    public int gaRelation(){
        double sum = this.persistence.getPrices();
        int gaRelation = (int)(sum*100/ gaPreis)-100;
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