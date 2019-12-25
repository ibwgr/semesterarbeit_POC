package GA_Trial;

import java.time.LocalDate;
import java.util.Date;

public class Reise extends GUI{

    public String destination;
    public String preis;
    public LocalDate datum;



    public Reise( String destination, String preis, LocalDate datum) {

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

    public LocalDate getDatum() {
        return datum;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public void setPreis(String preis) {
        this.preis = preis;
    }

    public void setDatum(LocalDate datum) {
        this.datum = datum;
    }

    @Override
    public String toString() {
        return datum + "\t\t" + destination + "\t\t\t" + "CHF " + preis + ".-\n";
    }
}
