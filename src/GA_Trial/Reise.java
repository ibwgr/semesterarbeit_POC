package GA_Trial;

import javax.swing.*;
import java.math.BigDecimal;

public class Reise {

    public String destination;
    public String vorname;
    public String nachname;
    public int preis;



    public Reise(String vorname, String nachname, String destination, int preis) {
        this.vorname = vorname;
        this.nachname = nachname;
        this.destination = destination;
        this.preis = preis;
    }




    @Override
    public String toString() {
        return vorname + " " + nachname + " " + destination + " " + preis;
    }
}
