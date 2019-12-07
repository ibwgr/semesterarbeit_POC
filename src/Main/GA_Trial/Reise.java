package GA_Trial;

public class Reise {

    public String destination;
    public String vorname;
    public String nachname;
    public String preis;
    public String datum;


    public Reise(String vorname, String nachname, String destination, String preis, String datum) {
        this.vorname = vorname;
        this.nachname = nachname;
        this.destination = destination;
        this.preis = preis;
        this.datum = datum;
    }


    @Override
    public String toString() {
        return datum + " " + vorname+ " " + nachname + " " + destination + " " + "CHF " +  preis + ".-\n";
    }
}
