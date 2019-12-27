package GA_Trial;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Reise extends GUI{

    public int nr;
    public String destination;
    public double preis;
    public LocalDate datum;



    public Reise( int nr, String destination, double preis, LocalDate datum) {

        this.nr = nr;
        this.destination = destination;
        this.preis = preis;
        this.datum = datum;
    }

//    public Reise(int nr, String bern, String s, LocalDate of) {
//        super();
//    }

    public String getDestination() {
        return destination;
    }

    public double getPreis() {
        return preis;
    }

    public LocalDate getDatum() {
        return datum;
    }

    public String getDate(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd. MMM yyyy");
        String formattedString = datum.format(formatter);
        return formattedString;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public void setPreis(double preis) {
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
        return nr + "\t\t" + datum + "\t\t" + destination + "\t\t\t" + "CHF " + preis + "\n";
    }
}
