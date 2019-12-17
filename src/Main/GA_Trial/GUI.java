package GA_Trial;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class GUI extends Application {

    @Override

    public void start(Stage primaryStage) throws FileNotFoundException {

        GridPane gp = new GridPane();

        primaryStage.show();
        primaryStage.setTitle("GA-Trial");

        Label reiseziel = new Label("Reiseziel");
        ChoiceBox cb = new ChoiceBox(FXCollections.observableArrayList());
        cb.getItems().addAll("Chur", "Zürich", "Bern", "Olten");
        cb.getSelectionModel().selectFirst();

        Label monat = new Label("Monat");
        ChoiceBox cb2 = new ChoiceBox(FXCollections.observableArrayList());
        cb2.getItems().addAll("Januar", "Februar", "März", "April", "Mai", "Juni", "Juli", "August", "September", "Oktober", "November", "Dezember");
        cb2.getSelectionModel().selectFirst();

        primaryStage.setScene(new Scene(gp, 1000, 400));
        gp.setPadding(new Insets(20, 20, 20, 20));

        gp.add(reiseziel, 0, 0);
        gp.add(cb, 0, 1);

        gp.add(monat, 0,5);
        gp.add(cb2, 0,6);

        Label reports = new Label("Reiseauswertungen");

        TextArea tripReport = new TextArea();
        tripReport.setMaxSize(300, 1000);

        Label preisSum = new Label("Total Reisekosten");
        TextField sum = new TextField();

        Button showReisen = new Button("Zeige alle Reisen");
        showReisen.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ArrayList<Reise> ergebnis = new SQL_Persistence().getReise();

                tripReport.setText(String.valueOf(ergebnis).replace("[", "").replace("]", "").replace(",", ""));
            }
        });

        Label reisekosten = new Label("Reisekosten");
        TextField price = new TextField();


        Label gaRel = new Label("GA in % zu Totalkosten");
        TextField realation = new TextField();

        Label datumsfeld = new Label("Reisedatum");

//        LocalDate ld = LocalDate.now();
//        TextField date = new TextField();

//        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
//        String datumformatiert = dateFormat.format(ld);
//        date.setText(datumformatiert);

        // Datepicker
        DatePicker datePicker = new DatePicker();
        datePicker.setValue(LocalDate.now());
        datePicker.setShowWeekNumbers(false);
        // Converter für Datepicker
        StringConverter<LocalDate> converter = new StringConverter<LocalDate>() {
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
            @Override
            public String toString(LocalDate date) {
                if (date != null){
                    return dateFormatter.format(date);
                } else {
                    return "";
                }
            }

            @Override
            public LocalDate fromString(String string) {
                if (string != null && !string.isEmpty()){
                    return LocalDate.parse(string, dateFormatter);
                }else {
                    return null;
                }
            }
        };
        datePicker.setConverter(converter);
        datePicker.setPromptText("dd-MMM-yyyy");

        gp.add(reisekosten, 1, 0);
        gp.add(price, 1, 1);

        gp.add(datumsfeld, 2, 0);
//        gp.add(date, 2, 1);
        gp.add(datePicker, 2, 1);

        Button enterReise = new Button("Reise erfassen");
        enterReise.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

//                new SQL_Persistence().setTrip((String) cb.getSelectionModel().getSelectedItem(), price.getText(), date.getText());
                new SQL_Persistence().setTrip((String) cb.getSelectionModel().getSelectedItem(), price.getText(), datePicker.getValue().format(DateTimeFormatter.ofPattern("dd-MMM-yyyy")));
            }
        });


        gp.add(enterReise, 3, 1);
        gp.add(reports, 0, 3);

        gp.add(tripReport, 0, 4);


        gp.add(showReisen, 1, 4);

        gp.add(preisSum, 2, 3);
        gp.add(sum, 2, 4);

        Image im = new Image(new FileInputStream("picture/swisscom-logo.png"));

        ImageView image = new ImageView(im);

        gp.add(image, 3, 5);
        image.setFitHeight(100);
        image.setFitWidth(100);

        gp.add(gaRel, 3, 3);
        gp.add(realation, 3, 4);

        Button sumTotal = new Button("Reisetotal");
        gp.add(sumTotal, 2, 5);
        sumTotal.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                int ergebnis = new SQL_Persistence().getPreise();
                double ergebnis2 = new SQL_Persistence().gaRelation();

                sum.setText(String.valueOf(ergebnis));
                realation.setText(String.valueOf(ergebnis2));
            }
        });

        gp.setHgap(10);
        gp.setVgap(10);
        price.setText("50");

        cb.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Object selectedItem = cb.getSelectionModel().getSelectedItem();
                if ("Zürich".equals(selectedItem)) {
                    price.setText("80");

                } else if ("Bern".equals(selectedItem)) {
                    price.setText("100");

                } else if ("Chur".equals(selectedItem)) {
                    price.setText("50");

                } else if ("Olten".equals(selectedItem)) {
                    price.setText("70");
                }
            }
        });


        cb2.setOnAction(new EventHandler<ActionEvent>() {


            @Override
            public void handle(ActionEvent event) {
                Object selectedItem = cb2.getSelectionModel().getSelectedItem();

                if ("Januar".equals(selectedItem)){
                    ArrayList<Reise> abc = new SQL_Persistence().getMonthPerTrip("%Jan%");
                    tripReport.setText(String.valueOf(abc).replace("[", "").replace("]", "").replace(",", ""));

                }else if ("Februar".equals(selectedItem)){
                    ArrayList<Reise> abc = new SQL_Persistence().getMonthPerTrip("%Feb%");
                    tripReport.setText(String.valueOf(abc).replace("[", "").replace("]", "").replace(",", ""));

                }else if ("März".equals(selectedItem)){
                    ArrayList<Reise> abc = new SQL_Persistence().getMonthPerTrip("%Mar%");
                    tripReport.setText(String.valueOf(abc).replace("[", "").replace("]", "").replace(",", ""));

                }else if ("April".equals(selectedItem)){
                    ArrayList<Reise> abc = new SQL_Persistence().getMonthPerTrip("%Apr%");
                    tripReport.setText(String.valueOf(abc).replace("[", "").replace("]", "").replace(",", ""));

                }else if ("Mai".equals(selectedItem)){
                    ArrayList<Reise> abc = new SQL_Persistence().getMonthPerTrip("%Mai%");
                    tripReport.setText(String.valueOf(abc).replace("[", "").replace("]", "").replace(",", ""));


                }else if ("Juni".equals(selectedItem)){
                    ArrayList<Reise> abc = new SQL_Persistence().getMonthPerTrip("%Jun%");
                    tripReport.setText(String.valueOf(abc).replace("[", "").replace("]", "").replace(",", ""));


                }else if ("Juli".equals(selectedItem)){
                    ArrayList<Reise> abc = new SQL_Persistence().getMonthPerTrip("%Jul%");
                    tripReport.setText(String.valueOf(abc).replace("[", "").replace("]", "").replace(",", ""));

                }else if ("August".equals(selectedItem)){
                    ArrayList<Reise> abc = new SQL_Persistence().getMonthPerTrip("%Aug%");
                    tripReport.setText(String.valueOf(abc).replace("[", "").replace("]", "").replace(",", ""));


                }else if ("September".equals(selectedItem)){
                    ArrayList<Reise> abc = new SQL_Persistence().getMonthPerTrip("%Sep%");
                    tripReport.setText(String.valueOf(abc).replace("[", "").replace("]", "").replace(",", ""));


                }else if ("Oktober".equals(selectedItem)){
                    ArrayList<Reise> abc = new SQL_Persistence().getMonthPerTrip("%Oct%");
                    tripReport.setText(String.valueOf(abc).replace("[", "").replace("]", "").replace(",", ""));


                }else if ("November".equals(selectedItem)){
                    ArrayList<Reise> abc = new SQL_Persistence().getMonthPerTrip("%Nov%");
                    tripReport.setText(String.valueOf(abc).replace("[", "").replace("]", "").replace(",", ""));


                }else if ("Dezember".equals(selectedItem)){
                    ArrayList<Reise> abc = new SQL_Persistence().getMonthPerTrip("%Dez%");
                    tripReport.setText(String.valueOf(abc).replace("[", "").replace("]", "").replace(",", ""));
                }
            }
        });
    }
}
