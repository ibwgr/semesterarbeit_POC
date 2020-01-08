package GA_Trial;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class SQL_Persistence implements Persistence  {


    String databse = "jdbc:mysql://localhost:3306/calculator";
    Connection con = null;
    String user = "java";
    String password = "java";


    public SQL_Persistence() { }


    public void DBConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(databse, user, password);
        }catch (SQLException ex){
            System.out.println("Ein Fehler ist aufgetreten");
            ex.printStackTrace();

        }catch (ClassNotFoundException ex){
            System.out.println("Datenbank nicht gefunden");
            ex.printStackTrace();
        }
    }


    public void setTrip(String destination, double preis, LocalDate datum) {
        try {
            DBConnection();
            if (con != null) {
                System.out.println("verbindung hergestellt");
                Statement stat = con.createStatement();
                stat.executeUpdate("INSERT INTO calculator.reise(destination, preis, datum) " +
                        "VALUES('"+destination+"','"+preis+"','"+datum+"')");
            }
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


    public List<Reise> getTrip() {

       ArrayList<Reise> al = new ArrayList<>();

        try {
            DBConnection();
            if (con != null) {
                System.out.println("verbindung hergestellt");
                Statement stat = con.createStatement();
                ResultSet x = stat.executeQuery("SELECT * FROM calculator.reise ORDER BY datum");
                while (x.next()){
                    Reise k1 = new Reise(
                            x.getInt("nr"),
                            x.getString("destination"),
                            x.getDouble("preis"),
                            x.getDate("datum").toLocalDate()
                    );
                    al.add(k1);
                }
            }
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
        return al;
    }

    public double getPrices() {
        double sum = 0;

        try {
            DBConnection();
            if (con != null) {
                System.out.println("verbindung hergestellt");
                Statement stat = con.createStatement();
                ResultSet x = stat.executeQuery("select preis from calculator.reise");
                while (x.next()){
                    double c = x.getInt("preis");
                    sum = sum + c;
                }
            }
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
        return sum;
    }


    public List<Reise> getMonthPerTrip(String monat) {

        ArrayList<Reise> al = new ArrayList<>();

        String a =  monat;
        String r = "SELECT * FROM calculator.reise where datum LIKE '"+a+"' ORDER BY datum";


        try {
            DBConnection();
            if (con != null) {
                System.out.println("verbindung hergestellt");
                Statement stat = con.createStatement();
                ResultSet x = stat.executeQuery(r);

                while (x.next()){
                    Reise k1 = new Reise(
                            x.getInt("nr"),
                            x.getString("destination"),
                            x.getDouble("preis"),
                            x.getDate("datum").toLocalDate()
                    );
                    al.add(k1);
                }
            }
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
        System.out.print(al);
        return al;
    }


    public double getPricePerMonth(String monat) {
        double sum = 0;

        String a =  monat;
        String r = "SELECT * FROM calculator.reise where datum LIKE '"+a+"'";

        try {
            DBConnection();
            if (con != null) {
                System.out.println("verbindung hergestellt");
                Statement stat = con.createStatement();
                ResultSet x = stat.executeQuery(r);

                while (x.next()){
                    double c = x.getDouble("preis");
                    sum = sum + c;
                }
            }
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
        return sum;
    }


    public void deleteTrip(int nr) {

        String r = "DELETE FROM calculator.reise where nr LIKE '"+nr+"'";

        try {
            DBConnection();
            if (con != null) {
                System.out.println("verbindung hergestellt");
                Statement stat = con.createStatement();
                int x = stat.executeUpdate(r);
            }
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
}