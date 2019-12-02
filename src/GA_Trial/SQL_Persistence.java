package GA_Trial;
import java.sql.*;
import java.util.ArrayList;


public class SQL_Persistence extends GUI {


    String databse = "jdbc:mysql://localhost:3306/calculator";
    Connection con = null;
    String user = "root";
    String password = "salens15";


    public  GUI setTrip(String vorname, String nachname, String destination, String preis) {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(databse, user, password);
            if (con != null) {
                System.out.println("verbindung hergestellt");
                Statement stat = con.createStatement();
                stat.executeUpdate("INSERT INTO calculator.Reise(vorname, nachname, destination, preis) VALUES('"+vorname+"','"+nachname+"','"+destination+"','"+preis+"')");
            }
        } catch (ClassNotFoundException ex) {
            System.out.println("Datenbank nicht gefunden");
            ex.printStackTrace();
        } catch (SQLException ex) {
            System.out.println("Ein Fehler ist aufgetreten");
            ex.printStackTrace();

        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
        return null;
    }


    public ArrayList<Reise> getReise() {

        ArrayList<Reise> al = new ArrayList<>();

        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(databse, user, password);
            if (con != null) {
                System.out.println("verbindung hergestellt");
                Statement stat = con.createStatement();
                ResultSet x = stat.executeQuery("SELECT * FROM calculator.reise");
                while (x.next()){
                    Reise k1 = new Reise(
                            x.getString("destination"),
                            x.getString("vorname"),
                            x.getString("nachname"),
                            x.getString("preis")
                    );
                    al.add(k1);
                }
            }
        } catch (ClassNotFoundException ex) {
            System.out.println("Datenbank nicht gefunden");
            ex.printStackTrace();
        } catch (SQLException ex) {
            System.out.println("Ein Fehler ist aufgetreten");
            ex.printStackTrace();
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
        System.out.println(al);
        return al;
    }

    public ArrayList<Reise> getPreise() {

        ArrayList<Reise> al = new ArrayList<>();

        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(databse, user, password);
            if (con != null) {
                System.out.println("verbindung hergestellt");
                Statement stat = con.createStatement();
                ResultSet x = stat.executeQuery("SELECT preis FROM calculator.reise");
                while (x.next()){
                    Reise k1 = new Reise(
                            x.getString("preis")
                    );
                    al.add(k1);

                }
            }
        } catch (ClassNotFoundException ex) {
            System.out.println("Datenbank nicht gefunden");
            ex.printStackTrace();
        } catch (SQLException ex) {
            System.out.println("Ein Fehler ist aufgetreten");
            ex.printStackTrace();
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }

        for (Reise r : al ){
            System.out.println(r.preis);
        }

        return al;
    }

}
