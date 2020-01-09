package GA_Trial;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class GUI extends Application  {

    private Chart_GA chartGA;
    private Calculations calculations;
    private Persistence persistence;


    public GUI() {

        this.persistence = new SQL_Persistence();
        this.calculations = new Calculations(persistence);
        this.chartGA = new Chart_GA();

    }


    @Override
    public void start(Stage primaryStage) throws FileNotFoundException {



            // Gridpane
            GridPane gp = new GridPane();
            gp.setPadding(new Insets(20));
            gp.setHgap(10);
            gp.setVgap(10);

            gp.setGridLinesVisible(false);


            // Stackpane
            StackPane stack = new StackPane();

            // Labels
            Label erfassung = new Label("Erfassung");
            erfassung.setFont(Font.font("Cambria", 25) );
            Label startOrt = new Label("Von");
            Label reiseziel = new Label("Nach");
            Label reisedatum = new Label("Datum");
            Label kosten = new Label("Kosten");
            Label chur = new Label("Chur");
            Label auswertung = new Label("Auswertung");
            auswertung.setFont(Font.font("Cambria", 25) );
            Label preisSum = new Label("Total Reisekosten");
            Label gaRel = new Label("Preisverhältnis zu GA");

            // Textfelder
            TextField price = new TextField();
            TextField kostenTotal = new TextField();
            TextField relation = new TextField();
            TextField other = new TextField();

            // ComboBoxen
            ComboBox comboBoxZiel = new ComboBox(FXCollections.observableArrayList());
            ComboBox comboBoxMonat = new ComboBox(FXCollections.observableArrayList());

            //Buttons
            Button enterReise = new Button("Reise erfassen");
            gp.setHalignment(enterReise, HPos.RIGHT);
            Button refreshMonth = new Button("Aktualisiere Monat");
            Button showAll = new Button("Zeige alle Reisen");
            Button deleteTrip = new Button("Lösche Reise");




           // Image
            Image im = new Image(new FileInputStream("picture/logo.png"));
            ImageView image = new ImageView(im);
            image.setFitHeight(50);
            image.setFitWidth(50);

            FileInputStream input = new FileInputStream("picture/hintergrund.jpeg");
            Image i = new Image(input);

            BackgroundImage background = new BackgroundImage(
                    i, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                    BackgroundPosition.CENTER,
                    new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, true,true,true, true));
            Background bg = new Background(background);


            // DatePicker
            DatePicker datePicker = new DatePicker();


            // Stage
            BorderPane root = new BorderPane();
            Scene scene = new Scene(root,675,900);
            root.setCenter(gp);
            root.setTop(image);
            root.setBottom(stack);
            root.setBackground(bg);



            root.getStylesheets().addAll(getClass().getResource("chart-style.css").toExternalForm());
            root.setId("root");

            primaryStage.setScene(scene);
            primaryStage.setTitle("Swisscom GA-Calculator");
            primaryStage.show();


            // Table
            TableView<Reise> reiseTable = new TableView<>();

            TableColumn<Reise, String> number = new TableColumn<>("ID");
            TableColumn<Reise, String> destination= new TableColumn<>("Destination");
            TableColumn<Reise, String> preis = new TableColumn<>("Reisekosten");
            TableColumn<Reise, String> datum = new TableColumn<>("Reisedatum");

            number.setCellValueFactory(new PropertyValueFactory<>("nr"));
            destination.setCellValueFactory(new PropertyValueFactory<>("destination"));
            preis.setCellValueFactory(new PropertyValueFactory<>("preis"));
            datum.setCellValueFactory(new PropertyValueFactory<>("date"));

            number.setVisible(false);
            destination.setMinWidth(100);
            preis.setMinWidth(100);
            datum.setMinWidth(100);

            // Verhindert, dass aufgrund vom vorhandenen Platz eine leere Spalte erzeugt wird
            reiseTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);


            reiseTable.getColumns().addAll(number, destination, preis, datum);

            reiseTable.minWidth(400);


            // adds
            gp.add(erfassung,0,0,2,1);
            gp.add(startOrt,0,1);
            gp.add(reiseziel,1,1);
            gp.add(reisedatum,2,1);
            gp.add(kosten,3,1);
            gp.add(chur,0,2);
            gp.add(auswertung,0,5,2,1);
            gp.add(preisSum,2,19);
            gp.add(gaRel,3,19);
            gp.add(price,3,2);
            gp.add(kostenTotal,2,20, 1,2);
            gp.add(relation,3,20, 1, 2);
            gp.add(reiseTable,2,7,2,12);
            gp.add(comboBoxZiel,1,2);
            gp.add(datePicker,2,2);
            gp.add(enterReise,3,3);

            gp.add(refreshMonth,0,9, 3, 1);
            gp.add(comboBoxMonat,0,8,2,1);
            gp.add(showAll,0,10, 3,1);
            gp.add(deleteTrip, 0, 12, 2, 1);
            gp.add(chartGA.chart(), 0, 22, 4, 1);


            deleteTrip.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                try {
                    Reise r = reiseTable.getSelectionModel().getSelectedItem();
                    persistence.deleteTrip(r.nr);
                    if(comboBoxMonat.getSelectionModel().isEmpty()){
                        showAll.fire();
                    }else {
                        refreshMonth.fire();
                    }
                }catch (NullPointerException e){
                    System.out.println("Keine Reise ausgewählt");
                }
            }
        });


        showAll.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                ObservableList<Reise> data = FXCollections.observableArrayList(persistence.getTrip());
                kostenTotal.setText(String.valueOf(calculations.totalCost()));
                relation.setText(calculations.gaRelation() + "%");
                reiseTable.setItems(data);
                comboBoxMonat.getSelectionModel().clearSelection();
                gp.getChildren().remove(chartGA.chart());
                chartGA = new Chart_GA();
                gp.add(chartGA.chart(), 0, 22, 4, 1);
            }
        });

        comboBoxZiel.getItems().addAll("Zürich", "Bern", "Olten", "Anderer Zielort");
        comboBoxZiel.setPromptText("Bitte auswählen");

        comboBoxMonat.getItems().addAll("Januar", "Februar", "März", "April", "Mai", "Juni", "Juli", "August", "September", "Oktober", "November", "Dezember");
        comboBoxMonat.setPromptText("Monat auswählen");


        datePicker.setValue(LocalDate.now());
        datePicker.setShowWeekNumbers(false);

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


        enterReise.setOnAction (new EventHandler<ActionEvent>() {
            @Override
            public void handle (ActionEvent event) {
                if(comboBoxZiel.getSelectionModel().getSelectedItem() =="Anderer Zielort"){
                    if(other.getText().matches("[a-zA-Z, ä,ö,ü,è,à,é,Ä,Ö,Ü]+")) {
                        if (price.getText().matches("[0-9, .]+")) {
                                persistence.setTrip(other.getText(), Double.valueOf(price.getText()), datePicker.getValue());
                                if(comboBoxMonat.getSelectionModel().isEmpty()){
                                showAll.fire();
                                }else {
                                    refreshMonth.fire();
                                }
                        } else {
                                price.setText("Preis fehlt!");
                        }
                    } else {
                        price.setText("Reiseziel fehlt!");
                    }
                } else {
                    if(price.getText().matches("[0-9, .]+")){
                    new SQL_Persistence().setTrip((String) comboBoxZiel.getSelectionModel().getSelectedItem(), Double.valueOf(price.getText()), datePicker.getValue());
                        if(comboBoxMonat.getSelectionModel().isEmpty()){
                            showAll.fire();
                        }else {
                            refreshMonth.fire();
                        }
                    } else {
                        if (comboBoxZiel.getSelectionModel().isEmpty()){
                            price.setText("Kein Reiseziel gewählt!");
                        } else {
                            price.setText("Kein gültiger Betrag");
                        }
                    }
                }
            }
        } );


        comboBoxZiel.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Object selectedItem = comboBoxZiel.getSelectionModel().getSelectedItem();

                City city = City.from((String) selectedItem);
                switch (city) {
                    case ANDERERZIELORT:
                    price.clear();
                    price.setPromptText("Preis eingeben");
                    other.setVisible(true);
                    other.setPromptText("Reiseziel");
                    try {
                        gp.add(other, 1, 3);
                    } catch (IllegalArgumentException e) {
                    }
                    break;
                    default:
                        price.setText(String.valueOf(city.getPrice()));
                        other.clear();
                        other.setVisible(false);
                }
            }
        });

        refreshMonth.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    Object selectedItem = comboBoxMonat.getSelectionModel().getSelectedItem();
                    String month = Calculations.showMonth(selectedItem.toString());
                    ObservableList<Reise> abc = FXCollections.observableArrayList(persistence.getMonthPerTrip(month));
                    double pp = persistence.getPricePerMonth(month);
                    reiseTable.setItems(abc);
                    kostenTotal.setText(String.valueOf(pp));
                    relation.setText((int) (pp * 100 / (calculations.gaPerMonth) - 100) + "%");
                    gp.getChildren().remove(chartGA.chart());
                    chartGA = new Chart_GA();
                    gp.add(chartGA.chart(), 0, 22, 4, 1);
                } catch (NullPointerException npe){
                    System.out.println("Kein Monat ausgewählt");
                    comboBoxMonat.setPromptText("MONAT WÄHLEN!");
                }
            }
        });

        comboBoxMonat.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (comboBoxMonat.getValue() != null) {
                    Object selectedItem = comboBoxMonat.getSelectionModel().getSelectedItem();
                    String month = Calculations.showMonth(selectedItem.toString());
                    ObservableList<Reise> abc = FXCollections.observableArrayList(persistence.getMonthPerTrip(month));
                    double pp = persistence.getPricePerMonth(month);
                    reiseTable.setItems(abc);
                    kostenTotal.setText(String.valueOf(pp));
                    relation.setText((int) (pp * 100 / (calculations.gaPerMonth) - 100) + "%");
                }
            }
        });

        showAll.fire();
    }
}