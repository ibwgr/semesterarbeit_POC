package GA_Trial;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import java.time.LocalDate;

public class Reise_Test {



    @Test
    public void testGetDestination(){
        String destination = "Bern";
        Reise reise = new Reise(1, "Bern", Double.parseDouble("100"), LocalDate.of(2019,12, 19));
        reise.getDestination();
        assertEquals(reise.getDestination(), destination);
    }

    @Test
    public void testSetDestination(){
        Reise reise = new Reise(1, "Bern", Double.parseDouble("100"), LocalDate.of(2019,12, 19));
        String destination = "Bern";
        reise.setDestination(destination);
        String result = reise.getDestination();
        assertEquals(destination, result);
    }

    @Test
    public void testGetPrice(){
        Double preis = Double.parseDouble("100");
        Reise reise = new Reise(1, "Bern", Double.parseDouble("100"), LocalDate.of(2019,12, 19));
        reise.getPreis();
        assertEquals(Double.valueOf(reise.getPreis()), preis);
    }

    @Test
    public void testSetPrice(){
        Reise reise = new Reise(1, "Bern", Double.parseDouble("100"), LocalDate.of(2019,12, 19));
        Double preis = Double.parseDouble("100");
        reise.setPreis(preis);
        Double result = reise.getPreis();
        assertEquals(preis, result);
    }

    @Test
    public void testGetNr(){
       int nummer = 1;
        Reise reise = new Reise(1, "Bern", Double.parseDouble("100"), LocalDate.of(2019,12, 19));
        reise.getNr();
        assertEquals((reise.getNr()), nummer);
    }

    @Test
    public void testSetNr(){
        Reise reise = new Reise(1, "Bern", Double.parseDouble("100"), LocalDate.of(2019,12, 19));
        int nummer = 1;
        reise.setNr(nummer);
        int result = reise.getNr();
        assertEquals(nummer, result);
    }

    @Test
    public void testGetDatum(){
        LocalDate datum = LocalDate.of(2019,12,19);
        Reise reise = new Reise(1, "Bern", Double.parseDouble("100"), LocalDate.of(2019,12, 19));
        reise.getDatum();
        assertEquals((reise.getDatum()), datum);
    }

    @Test
    public void testSetDatum(){
        String datum = "19. Dez 2019";
        Reise reise = new Reise(1, "Bern", Double.parseDouble("100"), LocalDate.of(2019,12, 19));
        reise.setDatum(LocalDate.of(2019,12,19));
        String result = reise.getDate();
        assertEquals(datum, result);
    }


    @Test
    public void testGetDate(){
        String datum = "19. Dez 2019";
        Reise reise = new Reise(1, "Bern", Double.parseDouble("100"), LocalDate.of(2019,12, 19));
        reise.getDate();
        String result = reise.getDate();
        assertEquals(datum, result);
    }
}
