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



    @Override
    public String toString() {
        return datum + "\t\t" + destination + "\t\t\t" + "CHF " + preis + ".-\n";
    }
}
