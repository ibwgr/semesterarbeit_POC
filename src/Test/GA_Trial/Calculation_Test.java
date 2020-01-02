package GA_Trial;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class Calculation_Test extends Calculations{


    @Test
    public void testGARelationCalculation(){
        new Calculations().gaRelation(555.0);
        int gaRelation = (int)(555.0*100/ gaPreis)-100;
        assertEquals(gaRelation, -90);
        }
    }