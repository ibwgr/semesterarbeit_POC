package GA_Trial;

import org.junit.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDate;
import org.junit.Test;
import static org.junit.Assert.*;



public class SQL_Persistence_Test  {


    @InjectMocks SQL_Persistence sql_persistence;
    @Mock private Connection mockConnection;
    @Mock private PreparedStatement mockStatement;
    @Mock private DataSource ds;
    @Mock ResultSet rs;
    @Mock DriverManager dm;



    @Before
    public void setUp() {

        MockitoAnnotations.initMocks(this);
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
    public void shouldTestDBConnectionSQLException(){

        try {
            new SQL_Persistence().DBConnection();
            Class.forName("com.mysql.jdbc.Driver");
            mockConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/calculator", "hallo", "hallo");
        } catch (ClassNotFoundException ex) {
            System.out.println("Datenbank nicht gefunden");

        } catch (SQLException ex) {
            System.out.println("Ein Fehler ist aufgetreten");
        }
    }

    @Test
    public void shouldTestDBConnectionClassNotFoundException(){

        try {
            new SQL_Persistence().DBConnection();
            Class.forName("xxx.mysql.jdbc.Driver");
            mockConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/calculator", "java", "java");
        } catch (ClassNotFoundException ex) {
            System.out.println("Datenbank nicht gefunden");

        } catch (SQLException ex) {
            System.out.println("Ein Fehler ist aufgetreten");
        }
    }


    @Test
    public void shouldTestGetTripSQLException(){

        try {
            new SQL_Persistence().getTrip();
            Class.forName("com.mysql.jdbc.Driver");
            mockConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/calculator", "hallo", "hallo");
        } catch (ClassNotFoundException ex) {
            System.out.println("Datenbank nicht gefunden");

        } catch (SQLException ex) {
            System.out.println("Ein Fehler ist aufgetreten");
        }
    }



    @Test
    public void shouldTestCloseConnection() {
        try {
            new SQL_Persistence().getTrip();
            Class.forName("com.mysql.jdbc.Driver");
            mockConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/calculator", "java", "java");
        } catch (ClassNotFoundException ex) {
            System.out.println("Datenbank nicht gefunden");

        } catch (SQLException ex) {
            System.out.println("Ein Fehler ist aufgetreten");
        }finally {
            if (mockConnection != null){
                try {
                    mockConnection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    @Test
    public void shouldTestDeleteTripException() {
        try {
            new SQL_Persistence().deleteTrip(0);
            Class.forName("com.mysql.jdbc.Driver");
            mockConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/calculator", "hallo", "hallo");
        } catch (ClassNotFoundException ex) {
            System.out.println("Datenbank nicht gefunden");

        } catch (SQLException ex) {
            System.out.println("Ein Fehler ist aufgetreten");
        }finally {
            if (mockConnection != null){
                try {
                    mockConnection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}