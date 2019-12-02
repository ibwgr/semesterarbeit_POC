package GA_Trial;

import javax.swing.*;

public class Reise {

    public String destination;
    public String vorname;
    public String nachname;
    public String preis;



    public Reise(String destination, String vorname, String nachname, String preis) {
        this.destination = destination;
        this.vorname = vorname;
        this.nachname = nachname;
        this.preis = preis;

    }


    public Reise(String preis){
        this.preis=preis;
    }


    @Override
    public String toString() {
        return vorname + " " + nachname + " " + destination + " " + preis;
    }
}
