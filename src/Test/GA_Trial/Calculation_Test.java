
package GA_Trial;

import org.junit.Before;
import org.junit.Test;


import static GA_Trial.Calculations.gaPreis;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class Calculation_Test {

    public Persistence persistence = mock(Persistence.class);

    @Before
    public void setup() {
    }

    @Test
    public void testGARelationCalculation(){
        when(persistence.getPrices()).thenReturn(500.0);

        int calculatedGARelation = new Calculations(persistence).gaRelation();
        int gaRelation = (int)(500.0*100/ gaPreis)-100;
        assertEquals(calculatedGARelation, gaRelation);
    }

    @Test
    public void testGARelationCalculation2(){
        when(persistence.getPrices()).thenReturn(-500.0);

        int calculatedGARelation = new Calculations(persistence).gaRelation();
        int gaRelation = (int)(-500.0*100/ gaPreis)-100;
        assertEquals(calculatedGARelation, gaRelation);
    }

    @Test
    public void showMonthTest() {
        String monthPattern = Calculations.showMonth("Januar");
        assertEquals("%-01-%", monthPattern);
    }
}

