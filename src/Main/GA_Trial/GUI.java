package GA_Trial;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.*;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.awt.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;


public class GUI extends Application {

    public double GAperMonth = 438.75;

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

            // Textarea
            TextArea tripReport = new TextArea();
            gp.setHalignment(tripReport, HPos.RIGHT);
            tripReport.setMaxSize(375, 300);


            // ComboBoxen
            ComboBox comboBoxZiel = new ComboBox(FXCollections.observableArrayList());
            ComboBox comboBoxMonat = new ComboBox(FXCollections.observableArrayList());

            //Buttons
            Button enterReise = new Button("Reise erfassen");
            gp.setHalignment(enterReise, HPos.RIGHT);
            Button showMonth = new Button("Zeige Monat");
            Button showAll = new Button("Zeige alle Reisen");
            Button deleteTrip = new Button("Lösche Reise");
            Button update = new Button("Update Diagramm");


           // Image
            /*Image im = new Image(new FileInputStream("picture/logo.png"));
            ImageView image = new ImageView(im);
            image.setFitHeight(50);
            image.setFitWidth(50);*/

            FileInputStream input = new FileInputStream("picture/logo_Kopie.png");
            Image i = new Image(input);


            BackgroundImage background = new BackgroundImage(i, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
            Background bg = new Background(background);



            // DatePicker
            DatePicker datePicker = new DatePicker();


            // Stage
            BorderPane root = new BorderPane();
            Scene scene = new Scene(root,675,900);
            root.setCenter(gp);
            //root.setTop(image);
            root.setBottom(stack);
            root.setBackground(bg);



            root.getStylesheets().addAll(getClass().getResource("chart-style.css").toExternalForm());
            root.setId("root");

            primaryStage.setScene(scene);
            primaryStage.setTitle("GA-Trial");
            primaryStage.show();

            // Table
            TableView<Reise> reiseTable = new TableView<>();

            TableColumn<Reise, String> number = new TableColumn<>("ID");
            TableColumn<Reise, String> destination= new TableColumn<>("Destination");
            TableColumn<Reise, String> preis = new TableColumn<>("Reisekosten");
            TableColumn<Reise, Locale> datum = new TableColumn<>("Reisedatum");

            number.setCellValueFactory(new PropertyValueFactory<>("nr"));
            destination.setCellValueFactory(new PropertyValueFactory<>("destination"));
            preis.setCellValueFactory(new PropertyValueFactory<>("preis"));
            datum.setCellValueFactory(new PropertyValueFactory<>("datum"));

            number.setMaxWidth(30);
            destination.setMinWidth(100);
            preis.setMinWidth(100);
            datum.setMinWidth(100);


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

            gp.add(showMonth,0,8, 2, 1);
            gp.add(comboBoxMonat,0,9,3,1);
            gp.add(showAll,0,10, 3,1);
            gp.add(deleteTrip, 0, 11, 2, 1);
            gp.add(update, 0,12, 2,1);



            deleteTrip.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Reise r = reiseTable.getSelectionModel().getSelectedItem();
                new SQL_Persistence().deleteReise(r.nr);
                showAll.fire();
            }
        });


        showAll.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                ObservableList<Reise> data = FXCollections.observableArrayList(new SQL_Persistence().getReise());
                kostenTotal.setText(String.valueOf(Calculations.totalCost()));
                relation.setText(Calculations.gaRelation() + "%");
                reiseTable.setItems(data);
            }
        });

        comboBoxZiel.getItems().addAll("Zürich", "Bern", "Olten");
        comboBoxZiel.setPromptText("Bitte auswählen");

        comboBoxMonat.getItems().addAll("Alle", "Januar", "Februar", "März", "April", "Mai", "Juni", "Juli", "August", "September", "Oktober", "November", "Dezember");
        comboBoxMonat.setPromptText("Bitte auswählen");


        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis(0, 500,50);

        final LineChart<String, Number> lineChart = new LineChart<>(xAxis, yAxis);

        xAxis.setCategories(FXCollections .<String>observableArrayList(Arrays.asList
                ("Jan", "Feb", "Mär", "Apr", "Mai", "Jun", "Jul", "Aug", "Sep", "Okt", "Nov", "Dez")));

        yAxis.setLabel("Reisekosten");

        XYChart.Series<String, Number> series2 = new XYChart.Series<>();


        series2.getData().add(new XYChart.Data<>("Jan", GAperMonth));
        series2.getData().add(new XYChart.Data<>("Feb", GAperMonth));
        series2.getData().add(new XYChart.Data<>("Mar", GAperMonth));
        series2.getData().add(new XYChart.Data<>("Apr", GAperMonth));
        series2.getData().add(new XYChart.Data<>("Mai", GAperMonth));
        series2.getData().add(new XYChart.Data<>("Jun", GAperMonth));
        series2.getData().add(new XYChart.Data<>("Jul", GAperMonth));
        series2.getData().add(new XYChart.Data<>("Aug", GAperMonth));
        series2.getData().add(new XYChart.Data<>("Sep", GAperMonth));
        series2.getData().add(new XYChart.Data<>("Okt", GAperMonth));
        series2.getData().add(new XYChart.Data<>("Nov", GAperMonth));
        series2.getData().add(new XYChart.Data<>("Dez", GAperMonth));

        XYChart.Series<String, Number> series1 = new XYChart.Series<>();

        int jan = new SQL_Persistence().getPricePerMonth("%-01-%");
        series1.getData().add(new XYChart.Data<>("Jan", jan));

        int feb = new SQL_Persistence().getPricePerMonth("%-02-%");
        series1.getData().add(new XYChart.Data<>("Feb", feb));

        int mar = new SQL_Persistence().getPricePerMonth("%-03-%");
        series1.getData().add(new XYChart.Data<>("Mär", mar));

        int apr = new SQL_Persistence().getPricePerMonth("%-04-%");
        series1.getData().add(new XYChart.Data<>("Apr", apr));

        int mai = new SQL_Persistence().getPricePerMonth("%-05-%");
        series1.getData().add(new XYChart.Data<>("Mai", mai));

        int jun = new SQL_Persistence().getPricePerMonth("%-06-%");
        series1.getData().add(new XYChart.Data<>("Jun", jun));

        int jul = new SQL_Persistence().getPricePerMonth("%-07-%");
        series1.getData().add(new XYChart.Data<>("Jul", jul));

        int aug = new SQL_Persistence().getPricePerMonth("%-08-%");
        series1.getData().add(new XYChart.Data<>("Aug", aug));

        int sep = new SQL_Persistence().getPricePerMonth("%-09-%");
        series1.getData().add(new XYChart.Data<>("Sep", sep));

        int okt = new SQL_Persistence().getPricePerMonth("%-10-%");
        series1.getData().add(new XYChart.Data<>("Okt", okt));

        int nov = new SQL_Persistence().getPricePerMonth("%-11-%");
        series1.getData().add(new XYChart.Data<>("Nov", nov));

        int dez = new SQL_Persistence().getPricePerMonth("%-12-%");
        series1.getData().add(new XYChart.Data<>("Dez", dez));


        lineChart.getStylesheets().addAll(getClass().getResource("chart-style.css").toExternalForm());
        lineChart.setLegendSide(Side.BOTTOM);
        series2.setName("GA pro Monat");
        series1.setName("Einzelfahrten");

        gp.add(lineChart, 0,22,4,1);
        lineChart.getData().addAll(series1, series2);

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


        update.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                primaryStage.close();
                Platform.runLater(()-> {
                    try {
                        new GUI().start(new Stage());
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                });
            }
        });


        enterReise.setOnAction (new EventHandler<ActionEvent>() {
            @Override
            public void handle (ActionEvent event) {

                    if (price.getText().isEmpty() ||  comboBoxZiel.getSelectionModel().isEmpty()) {
                        price.setText("Reiseziel fehlt!");

                    }else if (price.getText().matches("[0-9]+")){
                        new SQL_Persistence().setTrip((String) comboBoxZiel.getSelectionModel().getSelectedItem(), price.getText(), datePicker.getValue());
                        showAll.fire();

                    } else {
                        price.setText("Kein gültiger Betrag");
                    }
            }
        });


        comboBoxZiel.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Object selectedItem = comboBoxZiel.getSelectionModel().getSelectedItem();
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

        showAll.fire();

        comboBoxMonat.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Object selectedItem = comboBoxMonat.getSelectionModel().getSelectedItem();

                if ("Januar".equals(selectedItem)){
                    ObservableList<Reise> abc = FXCollections.observableArrayList(new SQL_Persistence().getMonthPerTrip("%-01-%"));
                    int pp = new SQL_Persistence().getPricePerMonth("%-01-%");
                    reiseTable.setItems(abc);
                    kostenTotal.setText(String.valueOf(pp));
                    relation.setText(String.valueOf((int) (pp*100/(Calculations.GAPreis/12)-100)+"%"));


                }else if ("Februar".equals(selectedItem)){
                    ObservableList<Reise> abc = FXCollections.observableArrayList(new SQL_Persistence().getMonthPerTrip("%-02-%"));
                    int pp = new SQL_Persistence().getPricePerMonth("%-02-%");
                    reiseTable.setItems(abc);
                    kostenTotal.setText(String.valueOf(pp));
                    relation.setText(String.valueOf((int) (pp*100/(Calculations.GAPreis/12)-100)+"%"));


                }else if ("März".equals(selectedItem)){
                    ObservableList<Reise> abc = FXCollections.observableArrayList(new SQL_Persistence().getMonthPerTrip("%-03-%"));
                    int pp = new SQL_Persistence().getPricePerMonth("%-03-%");
                    reiseTable.setItems(abc);
                    kostenTotal.setText(String.valueOf(pp));
                    relation.setText(String.valueOf((int) (pp*100/(Calculations.GAPreis/12)-100)+"%"));


                }else if ("April".equals(selectedItem)){
                    ObservableList<Reise> abc = FXCollections.observableArrayList(new SQL_Persistence().getMonthPerTrip("%-04-%"));
                    int pp = new SQL_Persistence().getPricePerMonth("%-04-%");
                    reiseTable.setItems(abc);
                    kostenTotal.setText(String.valueOf(pp));
                    relation.setText(String.valueOf((int) (pp*100/(Calculations.GAPreis/12)-100)+"%"));


                }else if ("Mai".equals(selectedItem)){
                    ObservableList<Reise> abc = FXCollections.observableArrayList(new SQL_Persistence().getMonthPerTrip("%-05-%"));
                    int pp = new SQL_Persistence().getPricePerMonth("%-05-%");
                    reiseTable.setItems(abc);
                    kostenTotal.setText(String.valueOf(pp));
                    relation.setText(String.valueOf((int) (pp*100/(Calculations.GAPreis/12)-100)+"%"));


                }else if ("Juni".equals(selectedItem)){
                    ObservableList<Reise> abc = FXCollections.observableArrayList(new SQL_Persistence().getMonthPerTrip("%-06-%"));
                    int pp = new SQL_Persistence().getPricePerMonth("%-06-%");
                    reiseTable.setItems(abc);
                    kostenTotal.setText(String.valueOf(pp));
                    relation.setText(String.valueOf((int) (pp*100/(Calculations.GAPreis/12)-100)+"%"));


                }else if ("Juli".equals(selectedItem)){
                    ObservableList<Reise> abc = FXCollections.observableArrayList(new SQL_Persistence().getMonthPerTrip("%-07-%"));
                    int pp = new SQL_Persistence().getPricePerMonth("%-07-%");
                    reiseTable.setItems(abc);
                    kostenTotal.setText(String.valueOf(pp));
                    relation.setText(String.valueOf((int) (pp*100/(Calculations.GAPreis/12)-100)+"%"));


                }else if ("August".equals(selectedItem)){
                    ObservableList<Reise> abc = FXCollections.observableArrayList(new SQL_Persistence().getMonthPerTrip("%-08-%"));
                    int pp = new SQL_Persistence().getPricePerMonth("%-08-%");
                    reiseTable.setItems(abc);
                    kostenTotal.setText(String.valueOf(pp));
                    relation.setText(String.valueOf((int) (pp*100/(Calculations.GAPreis/12)-100)+"%"));


                }else if ("September".equals(selectedItem)){
                    ObservableList<Reise> abc = FXCollections.observableArrayList(new SQL_Persistence().getMonthPerTrip("%-09-%"));
                    int pp = new SQL_Persistence().getPricePerMonth("%-09-%");
                    reiseTable.setItems(abc);
                    kostenTotal.setText(String.valueOf(pp));
                    relation.setText(String.valueOf((int) (pp*100/(Calculations.GAPreis/12)-100)+"%"));


                }else if ("Oktober".equals(selectedItem)){
                    ObservableList<Reise> abc = FXCollections.observableArrayList(new SQL_Persistence().getMonthPerTrip("%-10-%"));
                    int pp = new SQL_Persistence().getPricePerMonth("%-10-%");
                    reiseTable.setItems(abc);
                    kostenTotal.setText(String.valueOf(pp));
                    relation.setText(String.valueOf((int) (pp*100/(Calculations.GAPreis/12)-100)+"%"));


                }else if ("November".equals(selectedItem)){
                    ObservableList<Reise> abc = FXCollections.observableArrayList(new SQL_Persistence().getMonthPerTrip("%-11-%"));
                    int pp = new SQL_Persistence().getPricePerMonth("%-11-%");
                    reiseTable.setItems(abc);
                    kostenTotal.setText(String.valueOf(pp));
                    relation.setText(String.valueOf((int) (pp*100/(Calculations.GAPreis/12)-100)+"%"));


                }else if ("Dezember".equals(selectedItem)){
                    ObservableList<Reise> abc = FXCollections.observableArrayList(new SQL_Persistence().getMonthPerTrip("%-12-%"));
                    int pp = new SQL_Persistence().getPricePerMonth("%-12-%");
                    reiseTable.setItems(abc);
                    kostenTotal.setText(String.valueOf(pp));
                    relation.setText(String.valueOf((int) (pp*100/(Calculations.GAPreis/12)-100)+"%"));

                }else if ("Alle".equals(selectedItem)){
                    showAll.fire();
                }
            }
        });
    }
}
