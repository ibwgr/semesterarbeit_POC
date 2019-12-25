package GA_Trial;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import javax.sql.DataSource;
import java.sql.*;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SQL_Persistence_Test  {

    public Reise r;

    @InjectMocks SQL_Persistence sql_persistence;
    @Mock private Connection mockConnection = null;
    @Mock private PreparedStatement mockStatement;
    @Mock private DataSource ds;
    @Mock ResultSet rs;
    @Mock DriverManager dm;



    @Before
    public void setUp() throws Exception{

        if (mockConnection !=null ){
            when(mockConnection.prepareStatement(any(String.class))).thenReturn(mockStatement);
            when(ds.getConnection()).thenReturn(mockConnection);

            r = new Reise("Bern", "100", "23-12-2019");

            when(rs.first()).thenReturn(true);
            when(rs.getString(1)).thenReturn("Bern");
            when(rs.getString(2)).thenReturn("100");
            when(rs.getString(3)).thenReturn("23-12-2019");
            when(mockStatement.executeQuery()).thenReturn(rs);
        }
    }

   @Test
    public void shouldTestSetTrip() {
        new SQL_Persistence().setTrip("Bern", "100", "23-12-2019");
    }

    @Test
    public void shouldTestGetTrip() {
        new SQL_Persistence().getReise();
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
            new SQL_Persistence().getReise();
            Class.forName("com.mysql.jdbc.Driver");
            mockConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/calculator", "hallo", "hallo");
        } catch (ClassNotFoundException ex) {
            System.out.println("Datenbank nicht gefunden");

        } catch (SQLException ex) {
            System.out.println("Ein Fehler ist aufgetreten");
        }
    }

    @Test
    public void shouldTestSetTripSQLException(){

        try {
            new SQL_Persistence().setTrip("Bern", "100" ,"23-12-2019");
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
            new SQL_Persistence().getReise();
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
    public void shouldTestGetPricePerMonth(){
        new SQL_Persistence().getPricePerMonth("Dezember");
    }

    @Test
    public void shouldTestGetPriceForChart(){
        new SQL_Persistence().getPriceForChart("Dezember");
    }

    @Test
    public void testDBConnection(){
        new SQL_Persistence().DBConnection();
    }

    @Test
    public void testGetMonthPerTrip(){
        new SQL_Persistence().getMonthPerTrip("Dezember");
    }

}
