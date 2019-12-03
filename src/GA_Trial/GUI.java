package GA_Trial;

import javax.swing.*;
import javax.swing.plaf.basic.BasicSplitPaneUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.ArrayList;

public class GUI extends JFrame{

    // String [] destination = {"Zuerich", "Bern", "Chur"};
    LocalDate datum =  LocalDate.now();

    JFrame c = new JFrame("Hallo");
    JPanel p = new JPanel();
    JButton b1 = new JButton("Eintragen");
    JButton b2 = new JButton("Total Reisekosten");
    JTextField date = new JTextField(String.valueOf(datum));
    JTextField vorname = new JTextField(10);
    JTextField nachname = new JTextField(10);
    JTextField destination = new JTextField(10);
    JTextField preis = new JTextField(10);
    JLabel firstname = new JLabel("Vorname");
    JLabel lastname = new JLabel("nachname");
    JLabel price = new JLabel("Preis");
    JLabel ort = new JLabel("Zielort");
    JLabel ga = new JLabel("GA-Verhältnis");
    JTextArea zielort = new JTextArea();
    JTextArea area=new JTextArea("Welcome to javatpoint");
    JTextField value = new JTextField(10);





    public void newGUI(){

        c.setVisible(true);
        c.setSize(400,400);
        c.add(p);
        p.setVisible(true);

        p.setLayout(new FlowLayout());
        p.add(firstname);
        p.add(vorname);
        p.add(lastname);
        p.add(nachname);
        p.add(price);
        p.add(preis);
        p.add(b1);
        p.add(ort);
        p.add(destination);
        p.add(b2);
        p.add(area);
        area.setVisible(true);
        area.setText(showPreise());
        p.add(ga);
        p.add(value);

        enterTrip();

    }


    //JList <String> list = new JList<String>(destination);


    public GUI() {

    }


    public void enterTrip(){
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Reise trip = validateInput(vorname.getText(), nachname.getText(), destination.getText(), preis.getText());

                new SQL_Persistence().setTrip(trip);
            }
        });
    }

    private Reise validateInput(String vorname, String nachname, String destination, String preisValue) {
        // validate & return valid trip

        int preis = Integer.parseInt(preisValue);

        return new Reise(vorname, nachname, destination, preis);
    }

    public String showPreise(){
        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
              int ergebnis = new SQL_Persistence().getPreise();
              double ergebnis2 = new SQL_Persistence().gaRelation();

                area.setText(String.valueOf(ergebnis));
                value.setText(String.valueOf(ergebnis2));



            }
        });
        return null;
    }


}
