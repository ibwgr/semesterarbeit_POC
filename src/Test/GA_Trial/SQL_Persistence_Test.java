package GA_Trial;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import static org.mockito.Mockito.*;

public class SQL_Persistence_Test {



    @Test
    public void testgetPreiseDB() throws Exception {
        SQL_Persistence x = mock(SQL_Persistence.class);
        int b = x.getPreise();
        Assertions.assertEquals(b, 0);
    }

}
