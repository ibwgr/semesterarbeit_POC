package GA_Trial;
import java.sql.*;
import java.util.ArrayList;


public class SQL_Persistence {


    String databse = "jdbc:mysql://sql7.freesqldatabase.com:3306/sql7314160";
    Connection con = null;
    String user = "sql7314160";
    String password = "L2cryr653U";

    public final double gaPreis = 5000;
    int sum = 0;


    public void setTrip(Reise trip) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(databse, user, password);
            if (con != null) {
                System.out.println("verbindung hergestellt");
                Statement stat = con.createStatement();
                stat.executeUpdate("INSERT INTO sql7314160.reise(vorname, nachname, destination, preis) VALUES('"+trip.vorname+"','"+trip.nachname+"','"+trip.destination+"','"+trip.preis+"')");
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
    }


    public ArrayList<Reise> getReise() {

        ArrayList<Reise> al = new ArrayList<>();

        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(databse, user, password);
            if (con != null) {
                System.out.println("verbindung hergestellt");
                Statement stat = con.createStatement();
                ResultSet x = stat.executeQuery("SELECT * FROM sql7314160.reise");
                while (x.next()){
                    Reise k1 = new Reise(
                            x.getString("destination"),
                            x.getString("vorname"),
                            x.getString("nachname"),
                            x.getInt("preis")
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

    public int getPreise() {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(databse, user, password);
            if (con != null) {
                System.out.println("verbindung hergestellt");
                Statement stat = con.createStatement();
                ResultSet x = stat.executeQuery("select preis from sql7314160.reise;");
                while (x.next()){
                    int c = x.getInt("preis");
                    sum = sum + c;
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
            System.out.println(sum);
        return sum;
        }



    public double gaRelation() {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(databse, user, password);
            if (con != null) {
                System.out.println("verbindung hergestellt");
                Statement stat = con.createStatement();
                ResultSet x = stat.executeQuery("select preis from sql7314160.reise;");
                while (x.next()){
                    int c = x.getInt("preis");
                    sum = sum + c;
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
        double z = Math.round((sum/gaPreis*100) * 100.0) / 100.0;
        return z;
    }
}


