package GA_Trial;

import java.util.Date;

public class Reise {

    public String destination;
    public String vorname;
    public String nachname;
    public int preis;
    public Date datum;



    public Reise(String vorname, String nachname, String destination, int preis, Date datum) {
        this.vorname = vorname;
        this.nachname = nachname;
        this.destination = destination;
        this.preis = preis;
        this.datum = datum;
    }




    @Override
    public String toString() {
        return vorname + " " + nachname + " " + destination + " " + preis + datum;
    }
}
