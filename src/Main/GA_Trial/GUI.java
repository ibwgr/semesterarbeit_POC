package GA_Trial;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;


public class GUI extends Application {


    @Override

    public void start(Stage primaryStage) throws FileNotFoundException {

        // Gridpane
        GridPane gp = new GridPane();
        gp.setPadding(new Insets(20));
        gp.setHgap(10);
        gp.setVgap(10);

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


        // Image
        Image im = new Image(new FileInputStream("picture/logo.png"));
        ImageView image = new ImageView(im);
        image.setFitHeight(50);
        image.setFitWidth(50);


        // DatePicker
        DatePicker datePicker = new DatePicker();


        // Stage
        BorderPane root = new BorderPane();
        Scene scene = new Scene(root,675,900);
        root.setCenter(gp);
        root.setTop(image);
        root.setBottom(stack);
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
        gp.add(preisSum,2,10);
        gp.add(gaRel,3,10);
        gp.add(price,3,2);
        gp.add(kostenTotal,2,11);
        gp.add(relation,3,11);
        gp.add(reiseTable,2,6,2,4);
        gp.add(comboBoxZiel,1,2);
        gp.add(comboBoxMonat,1,6);
        gp.add(enterReise,3,3);
        gp.add(showMonth,0,6);
        gp.add(showAll,0,7);
        gp.add(datePicker,2,2);
        gp.add(deleteTrip, 1, 7);



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



            CategoryAxis xAxis = new CategoryAxis();
            xAxis.setCategories(FXCollections.<String>observableArrayList(Arrays.asList
                    ("Jan", "Feb", "Mär", "Apr", "Mai", "Jun", "Jul", "Aug", "Sep", "Okt", "Nov", "Dez")));


            NumberAxis yAxis = new NumberAxis();
            yAxis.setLabel("Reisekosten");

            final LineChart<String, Number> lineChart = new LineChart<>(xAxis, yAxis);
            XYChart.Series<String, Number> series2 = new XYChart.Series<>();

            series2.getData().add(new XYChart.Data<>("Jan", 438.75));
            series2.getData().add(new XYChart.Data<>("Feb", 438.75));
            series2.getData().add(new XYChart.Data<>("Mar", 438.75));
            series2.getData().add(new XYChart.Data<>("Apr", 438.75));
            series2.getData().add(new XYChart.Data<>("Mai", 438.75));
            series2.getData().add(new XYChart.Data<>("Jun", 438.75));
            series2.getData().add(new XYChart.Data<>("Jul", 438.75));
            series2.getData().add(new XYChart.Data<>("Aug", 438.75));
            series2.getData().add(new XYChart.Data<>("Sep", 438.75));
            series2.getData().add(new XYChart.Data<>("Okt", 438.75));
            series2.getData().add(new XYChart.Data<>("Nov", 438.75));
            series2.getData().add(new XYChart.Data<>("Dez", 438.75));

            final StackedBarChart<String, Number> stackedBarChart = new StackedBarChart<>(xAxis, yAxis);
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


            stackedBarChart.getData().addAll(series1);
            lineChart.getData().addAll(series2);

            stackedBarChart.setLegendVisible(true);
            lineChart.getStylesheets().addAll(getClass().getResource("chart-style.css").toExternalForm());

            lineChart.setLegendVisible(false);
            lineChart.setAnimated(false);
            lineChart.setCreateSymbols(true);
            lineChart.setAlternativeRowFillVisible(false);
            lineChart.setAlternativeColumnFillVisible(false);
            lineChart.setHorizontalGridLinesVisible(false);
            lineChart.setVerticalGridLinesVisible(false);
            lineChart.getXAxis().setVisible(false);
            lineChart.getYAxis().setVisible(false);
            lineChart.setLegendSide(Side.RIGHT);
            series2.setName("GA Kosten pro Monat");
            series1.setName("Einzelfahrten");
            ObservableList list = stack.getChildren();
            list.addAll(stackedBarChart,lineChart);


        enterReise.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                new SQL_Persistence().setTrip((String) comboBoxZiel.getSelectionModel().getSelectedItem(), price.getText(), datePicker.getValue());
                showAll.fire();
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
