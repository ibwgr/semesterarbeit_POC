package GA_Trial;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;


public class GUI extends JFrame{


    String [] ziele = {"Zuerich", "Bern", "Chur"};
    JComboBox desti = new JComboBox(ziele);

    String a = (String) desti.getSelectedItem();

    LocalDate datum =  LocalDate.now();

    JFrame c = new JFrame("GA-Calculation");
    JTabbedPane tp = new JTabbedPane();

    JPanel p = new JPanel();
    JPanel q = new JPanel();
    JPanel r = new JPanel();
    JButton b1 = new JButton("Reise eintragen");
    JButton b2 = new JButton("Auswertung Reisekosten");
    JTextField date = new JTextField(String.valueOf(datum));
    JTextField vorname = new JTextField(10);
    JTextField nachname = new JTextField(10);
    JTextField destination = new JTextField(10);
    JTextField preis = new JTextField(10);
    JLabel firstname = new JLabel("Vorname");
    JLabel lastname = new JLabel("Nachname");
    JLabel price = new JLabel("Preis");
    JLabel ort = new JLabel("Zielort", JLabel.LEFT);
    JLabel ga = new JLabel("GA-Verhältnis %");
    JLabel su = new JLabel("Summe Reisekosten CHF");

    //JTextArea zielort = new JTextArea();
    JTextField area=new JTextField(5);
    JTextField value = new JTextField(5);

    ImageIcon ii = new ImageIcon("picture/swisscom-logo.png");
    JLabel image = new JLabel(ii, JLabel.CENTER);



    public void newGUI(){


        c.setVisible(true);
        c.setSize(600,500);
        c.add(p);
        c.add(tp);

        p.setVisible(true);
        p.setLayout(new FlowLayout());
        p.add(firstname);
        p.add(vorname);
        p.add(lastname);
        p.add(nachname);
        p.add(price);
        p.add(preis);
        p.add(ort);
        p.add(destination);
        p.add(date);
        p.add(b1);
        p.add(desti);


        q.add(ga);
        q.add(value);
        q.add(su);
        q.add(area);
        q.add(b2);

        area.setVisible(true);
        area.setText(showPreise());
        area.setBounds(50,50,200,200);

        tp.add("Input", p);
        tp.add("Reports", q);
        tp.setBounds(50,50,200,200);
        p.add(image);


        enterTrip();


    }





    public GUI() {

    }


    public void enterTrip(){
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Reise trip = null;
                try {
                    trip = validateInput(vorname.getText(), nachname.getText(), destination.getText(), preis.getText(), date.getText());
                } catch (ParseException ex) {
                    ex.printStackTrace();
                }
                new SQL_Persistence().setTrip(trip);
            }
        });
    }

    private Reise validateInput(String vorname, String nachname, String destination, String preisValue, String datum) throws ParseException {


        Date date = new SimpleDateFormat("yyyy-MM-dd").parse(datum);
        int preis = Integer.parseInt(preisValue);

        return new Reise(vorname, nachname, destination, preis, date);
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
