package GA_Trial;

import java.time.LocalDate;

public class Reise extends GUI{

    public int nr;
    public String destination;
    public String preis;
    public LocalDate datum;



    public Reise( int nr, String destination, String preis, LocalDate datum) {

        this.nr = nr;
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

    public int getNr() {
        return nr;
    }

    public void setNr(int nr) {
        this.nr = nr;
    }

    @Override
    public String toString() {
        return nr + "\t\t" + datum + "\t\t" + destination + "\t\t\t" + "CHF " + preis + ".-\n";
    }
}
