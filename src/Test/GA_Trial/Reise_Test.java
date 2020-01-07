package GA_Trial;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import java.time.LocalDate;

public class Reise_Test {


    Reise reise = new Reise(1, "Bern", Double.parseDouble("100"), LocalDate.of(2019,12, 19));
    String destination = "Bern";
    Double preis = Double.parseDouble("100");
    int nummer = 1;
    LocalDate datum = LocalDate.of(2019,12,19);
    String date = "19. Dez 2019";

    @Test
    public void testGetDestination(){
        reise.getDestination();
        assertEquals(reise.getDestination(), destination);
    }

    @Test
    public void testSetDestination(){
        reise.setDestination(destination);
        String result = reise.getDestination();
        assertEquals(destination, result);
    }

    @Test
    public void testGetPrice(){
        reise.getPreis();
        assertEquals(Double.valueOf(reise.getPreis()), preis);
    }

    @Test
    public void testSetPrice(){
        reise.setPreis(preis);
        Double result = reise.getPreis();
        assertEquals(preis, result);
    }

    @Test
    public void testGetNr(){
       reise.getNr();
       assertEquals((reise.getNr()), nummer);
    }

    @Test
    public void testSetNr(){
        reise.setNr(nummer);
        int result = reise.getNr();
        assertEquals(nummer, result);
    }

    @Test
    public void testGetDatum(){
        reise.getDatum();
        assertEquals((reise.getDatum()), datum);
    }

    @Test
    public void testSetDatum(){
        String result = reise.getDate();
        assertEquals(date, result);
    }

    @Test
    public void testGetDate(){
        reise.getDate();
        String result = reise.getDate();
        assertEquals(date, result);
    }
}