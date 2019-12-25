package GA_Trial;

import org.junit.Test;
import org.mockito.Mock;

import javax.sql.DataSource;

public class Calculation_Test {

    @Mock
    private DataSource ds;


    @Test
    public void testShowAllCalculation(){
        new Calculations(ds).showAll();
    }

    @Test
    public void testTotalCostCalculation(){
        new Calculations(ds).totalCost();
    }

    @Test
    public void testGARelationCalculation(){
        new Calculations(ds).gaRelation();
    }
}
