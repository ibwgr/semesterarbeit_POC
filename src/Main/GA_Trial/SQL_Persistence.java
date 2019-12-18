package Main.GA_Trial;

import java.sql.*;
import java.util.ArrayList;


public class SQL_Persistence extends GUI{


    String databse = "jdbc:mysql://localhost:3306/calculator";
    Connection con = null;
    String user = "java";
    String password = "java";



    public final double gaPreis = 5000;
    int sum = 0;


    public void setTrip(String destination, String preis, String datum) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(databse, user, password);
            if (con != null) {
                System.out.println("verbindung hergestellt");
                Statement stat = con.createStatement();
                stat.executeUpdate("INSERT INTO calculator.reise(destination, preis, datum) " +
                        "VALUES('"+destination+"','"+preis+"','"+datum+"')");
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
                ResultSet x = stat.executeQuery("SELECT * FROM calculator.reise");
                while (x.next()){
                    Reise k1 = new Reise(
                            x.getString("destination"),
                            x.getString("preis"),
                            x.getString("datum")
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

        System.out.print(al);
        return al;
    }

    public int getPreise() {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(databse, user, password);
            if (con != null) {
                System.out.println("verbindung hergestellt");
                Statement stat = con.createStatement();
                ResultSet x = stat.executeQuery("select preis from calculator.reise;");
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
                ResultSet x = stat.executeQuery("select preis from calculator.reise;");
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



    public ArrayList<Reise> getMonthPerTrip(String monat) {

        ArrayList<Reise> al = new ArrayList<>();

        String a =  monat;
        String r = "SELECT * FROM calculator.reise where datum LIKE '"+a+"'";


        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(databse, user, password);
            if (con != null) {
                System.out.println("verbindung hergestellt");
                Statement stat = con.createStatement();
                ResultSet x = stat.executeQuery(r);

                while (x.next()){
                    Reise k1 = new Reise(
                            x.getString("destination"),
                            x.getString("preis"),
                            x.getString("datum")
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

        System.out.print(al);

        return al;
    }


    public int getPricePerMonth(String monat) {

        String a =  monat;
        String r = "SELECT * FROM calculator.reise where datum LIKE '"+a+"'";

        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(databse, user, password);
            if (con != null) {
                System.out.println("verbindung hergestellt");
                Statement stat = con.createStatement();
                ResultSet x = stat.executeQuery(r);

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
        return sum;
    }


    public int getPriceForChart(String month) {

        String y = month;

        String r = "SELECT * FROM calculator.reise where datum LIKE '"+y+"'";

        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(databse, user, password);
            if (con != null) {
                System.out.println("verbindung hergestellt");
                Statement stat = con.createStatement();
                ResultSet x = stat.executeQuery(r);

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
        return sum;
    }



}


