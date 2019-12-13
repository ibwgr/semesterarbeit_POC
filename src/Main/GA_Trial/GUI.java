package GA_Trial;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;


public class GUI extends JFrame {

    String[] ziele = {"Chur", "Bern", "Zürich"};

    JComboBox<String> desti = new JComboBox(ziele);

    LocalDate x = LocalDate.now();

    JFrame c = new JFrame("GA-Calculation");
    JTabbedPane tp = new JTabbedPane();

    JPanel p = new JPanel();
    JPanel q = new JPanel();
    JPanel r = new JPanel();

    JButton b1 = new JButton("Reise eintragen");
    JButton b2 = new JButton("Auswertung Reisekosten");
    JButton b3 = new JButton("Zeige alle Reisen");

    JTextField date = new JTextField(String.valueOf(x));
    JTextField vorname = new JTextField(10);
    JTextField nachname = new JTextField(10);
    JTextField preis = new JTextField(10);

    JLabel firstname = new JLabel("Vorname");
    JLabel lastname = new JLabel("Nachname");
    JLabel price = new JLabel("Preis");
    JLabel ga = new JLabel("GA-Verhältnis %");
    JLabel su = new JLabel("Summe Reisekosten CHF");

    JTextArea reisen = new JTextArea(10, 10);
    JTextField area = new JTextField(5);
    JTextField value = new JTextField(5);

    ImageIcon ii = new ImageIcon("picture/swisscom-logo.png");
    JLabel image = new JLabel(ii, JLabel.CENTER);


    public void GUI() {

        getSelectedPrice();
        c.setVisible(true);
        c.setSize(600, 500);
        c.add(p);
        c.add(tp);

        b1.setPreferredSize(new Dimension(150, 50));
        b1.setBorderPainted(true);

        b2.setPreferredSize(new Dimension(200, 50));
        b2.setBorderPainted(true);

        b3.setPreferredSize(new Dimension(150, 50));
        b3.setBorderPainted(true);

        p.setVisible(true);
        p.setLayout(new FlowLayout());
        p.add(firstname);
        p.add(vorname);
        p.add(lastname);
        p.add(nachname);
        p.add(price);
        p.add(preis);
        p.add(date);
        p.add(b1);
        p.add(desti);

        q.add(ga);
        q.add(value);
        q.add(su);
        q.add(area);
        q.add(b2);
        q.add(b3);
        q.add(reisen);

        area.setVisible(true);
        area.setText(showPreise());
        area.setBounds(50, 50, 200, 200);

        tp.add("Input", p);
        tp.add("Reports", q);
        tp.setBounds(50, 50, 200, 200);
        p.add(image);
        preis.setText("50");

        enterTrip();
        showReise();

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
            }
        });
    }


    public void enterTrip() {
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Reise trip = null;
                try {
                    trip = validateInput(vorname.getText(), nachname.getText(), (String) desti.getSelectedItem(), preis.getText(), date.getText());
                } catch (ParseException ex) {
                    ex.printStackTrace();
                }

                new SQL_Persistence().setTrip(trip);
            }
        });
    }

    private Reise validateInput(String vorname, String nachname, String destination, String preis, String datum) throws ParseException {

        //DatumValidirung noch erfassen inkl. Fehlermeldung

        Date d = new SimpleDateFormat("yyyy-MM-dd").parse(String.valueOf(date.getText()));

        return new Reise(vorname, nachname, destination, preis, datum);
    }


    public String showPreise() {
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

    public String showReise() {
        b3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<Reise> ergebnis = new SQL_Persistence().getReise();

                reisen.setText(String.valueOf(ergebnis).replace("[", "").replace("]", "").replace(",", ""));
            }
        });
        return null;
    }

    public String getSelectedPrice() {

        desti.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Object selectedItem = desti.getSelectedItem();
                if ("Zürich".equals(selectedItem)) {
                    preis.setText("80");

                } else if ("Bern".equals(selectedItem)) {
                    preis.setText("100");

                } else if ("Chur".equals(selectedItem)) {
                    preis.setText("50");
                }
            }
        });
        return null;
    }
}