package GA_Trial;

public class Reise extends GUI{

    public String destination;
    public String preis;
    public String datum;



    public Reise( String destination, String preis, String datum) {

        this.destination = destination;
        this.preis = preis;
        this.datum = datum;
    }

    public String getDestination() {
        return destination;
    }

    public String getPreis() {
        return preis;
    }

    public String getDatum() {
        return datum;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public void setPreis(String preis) {
        this.preis = preis;
    }

    public void setDatum(String datum) {
        this.datum = datum;
    }

    @Override
    public String toString() {
        return datum + "\t\t" + destination + "\t\t\t" + "CHF " + preis + ".-\n";
    }
}
