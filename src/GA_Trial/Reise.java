package GA_Trial;

import javax.swing.*;
import java.math.BigDecimal;

public class Reise {

    public String destination;
    public String vorname;
    public String nachname;
    public int preis;



    public Reise(){}


    public Reise(String destination, String vorname, String nachname, int preis) {
        this.destination = destination;
        this.vorname = vorname;
        this.nachname = nachname;
        this.preis = preis;
    }




    @Override
    public String toString() {
        return vorname + " " + nachname + " " + destination + " " + preis;
    }
}
