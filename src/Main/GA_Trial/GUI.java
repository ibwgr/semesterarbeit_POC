package Main.GA_Trial;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;


public class GUI extends Application {


    @Override

    public void start(Stage primaryStage) throws FileNotFoundException {

        GridPane gp = new GridPane();

        primaryStage.show();
        primaryStage.setTitle("GA-Trial");

        primaryStage.setScene(new Scene(gp, 1000, 700));
        gp.setPadding(new Insets(20, 20, 20, 20));

        Label reiseziel = new Label("Reiseziel");
        ChoiceBox cb = new ChoiceBox(FXCollections.observableArrayList());
        cb.getItems().addAll("Chur", "Zürich", "Bern", "Olten");
        cb.getSelectionModel().selectFirst();

        Label monat = new Label("Monat");
        ChoiceBox cb2 = new ChoiceBox(FXCollections.observableArrayList());
        cb2.getItems().addAll("Alle", "Januar", "Februar", "März", "April", "Mai", "Juni", "Juli", "August", "September", "Oktober", "November", "Dezember");
        cb2.getSelectionModel().selectFirst();

        Label reports = new Label("Reiseauswertungen");

        TextArea tripReport = new TextArea();
        tripReport.setMaxSize(300, 1000);

        Label preisSum = new Label("Total Reisekosten");
        TextField sum = new TextField();

        Label reisekosten = new Label("Reisekosten");
        TextField price = new TextField();

        Label gaRel = new Label("GA in % zu Totalkosten");
        TextField realation = new TextField();

        Label datumsfeld = new Label("Reisedatum");

        gp.setHgap(10);
        gp.setVgap(10);
        price.setText("50");

        Button showReisen = new Button("Zeige alle Reisen");
        showReisen.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ArrayList<Reise> ergebnis = new SQL_Persistence().getReise();

                tripReport.setText(String.valueOf(ergebnis).replace("[", "").replace("]", "").replace(",", ""));
            }
        });

        DatePicker datePicker = new DatePicker();
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

        Button enterReise = new Button("Reise erfassen");
        enterReise.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                new SQL_Persistence().setTrip((String) cb.getSelectionModel().getSelectedItem(), price.getText(), datePicker.getValue().format(DateTimeFormatter.ofPattern("dd-MMM-yyyy")));
            }
        });

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

            int jan = new SQL_Persistence().getPriceForChart("%Jan%");
            series1.getData().add(new XYChart.Data<>("Jan", jan));

            int feb = new SQL_Persistence().getPriceForChart("%Feb%");
            series1.getData().add(new XYChart.Data<>("Feb", feb));

            int mar = new SQL_Persistence().getPriceForChart("%Mär%");
            series1.getData().add(new XYChart.Data<>("Mär", mar));

            int apr = new SQL_Persistence().getPriceForChart("%Apr%");
            series1.getData().add(new XYChart.Data<>("Apr", apr));

            int mai = new SQL_Persistence().getPriceForChart("%Mai%");
            series1.getData().add(new XYChart.Data<>("Mai", mai));

            int jun = new SQL_Persistence().getPriceForChart("%Jun%");
            series1.getData().add(new XYChart.Data<>("Jun", jun));

            int jul = new SQL_Persistence().getPriceForChart("%Jul%");
            series1.getData().add(new XYChart.Data<>("Jul", jul));

            int aug = new SQL_Persistence().getPriceForChart("%Aug%");
            series1.getData().add(new XYChart.Data<>("Aug", aug));

            int sep = new SQL_Persistence().getPriceForChart("%Sep%");
            series1.getData().add(new XYChart.Data<>("Sep", sep));

            int okt = new SQL_Persistence().getPriceForChart("%Okt%");
            series1.getData().add(new XYChart.Data<>("Okt", okt));

            int nov = new SQL_Persistence().getPriceForChart("%Nov%");
            series1.getData().add(new XYChart.Data<>("Nov", nov));

            int dez = new SQL_Persistence().getPriceForChart("%Dez%");
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

            gp.add(stackedBarChart, 0, 7);
            gp.add(lineChart, 0, 7);

        Image im = new Image(new FileInputStream("picture/swisscom-logo.png"));
        ImageView image = new ImageView(im);
        image.setFitHeight(100);
        image.setFitWidth(100);

        gp.add(reiseziel, 0, 0);
        gp.add(cb, 0, 1);
        gp.add(monat, 0,5);
        gp.add(cb2, 0,6);

        gp.add(enterReise, 3, 1);
        gp.add(reports, 0, 3);
        gp.add(tripReport, 0, 4);
        gp.add(showReisen, 1, 4);
        gp.add(preisSum, 2, 3);
        gp.add(sum, 2, 4);
        gp.add(image, 3, 7);
        gp.add(gaRel, 3, 3);
        gp.add(realation, 3, 4);
        gp.add(reisekosten, 1, 0);
        gp.add(price, 1, 1);
        gp.add(datumsfeld, 2, 0);
        gp.add(datePicker, 2, 1);


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
                    int pp = new SQL_Persistence().getPricePerMonth("%Jan%");
                    tripReport.setText(String.valueOf(abc).replace("[", "").replace("]", "").replace(",", ""));
                    sum.setText(String.valueOf(pp));

                }else if ("Februar".equals(selectedItem)){
                    ArrayList<Reise> abc = new SQL_Persistence().getMonthPerTrip("%Feb%");
                    int pp = new SQL_Persistence().getPricePerMonth("%Feb%");
                    tripReport.setText(String.valueOf(abc).replace("[", "").replace("]", "").replace(",", ""));
                    sum.setText(String.valueOf(pp));

                }else if ("März".equals(selectedItem)){
                    ArrayList<Reise> abc = new SQL_Persistence().getMonthPerTrip("%Mar%");
                    int pp = new SQL_Persistence().getPricePerMonth("%Mar%");
                    tripReport.setText(String.valueOf(abc).replace("[", "").replace("]", "").replace(",", ""));
                    sum.setText(String.valueOf(pp));

                }else if ("April".equals(selectedItem)){
                    ArrayList<Reise> abc = new SQL_Persistence().getMonthPerTrip("%Apr%");
                    int pp = new SQL_Persistence().getPricePerMonth("%Apr%");
                    tripReport.setText(String.valueOf(abc).replace("[", "").replace("]", "").replace(",", ""));
                    sum.setText(String.valueOf(pp));

                }else if ("Mai".equals(selectedItem)){
                    ArrayList<Reise> abc = new SQL_Persistence().getMonthPerTrip("%Mai%");
                    int pp = new SQL_Persistence().getPricePerMonth("%Mai%");
                    tripReport.setText(String.valueOf(abc).replace("[", "").replace("]", "").replace(",", ""));
                    sum.setText(String.valueOf(pp));

                }else if ("Juni".equals(selectedItem)){
                    ArrayList<Reise> abc = new SQL_Persistence().getMonthPerTrip("%Jun%");
                    int pp = new SQL_Persistence().getPricePerMonth("%Jun%");
                    tripReport.setText(String.valueOf(abc).replace("[", "").replace("]", "").replace(",", ""));
                    sum.setText(String.valueOf(pp));

                }else if ("Juli".equals(selectedItem)){
                    ArrayList<Reise> abc = new SQL_Persistence().getMonthPerTrip("%Jul%");
                    int pp = new SQL_Persistence().getPricePerMonth("%Jul%");
                    tripReport.setText(String.valueOf(abc).replace("[", "").replace("]", "").replace(",", ""));
                    sum.setText(String.valueOf(pp));

                }else if ("August".equals(selectedItem)){
                    ArrayList<Reise> abc = new SQL_Persistence().getMonthPerTrip("%Aug%");
                    int pp = new SQL_Persistence().getPricePerMonth("%Aug%");
                    tripReport.setText(String.valueOf(abc).replace("[", "").replace("]", "").replace(",", ""));
                    sum.setText(String.valueOf(pp));

                }else if ("September".equals(selectedItem)){
                    ArrayList<Reise> abc = new SQL_Persistence().getMonthPerTrip("%Sep%");
                    int pp = new SQL_Persistence().getPricePerMonth("%Sep%");
                    tripReport.setText(String.valueOf(abc).replace("[", "").replace("]", "").replace(",", ""));
                    sum.setText(String.valueOf(pp));

                }else if ("Oktober".equals(selectedItem)){
                    ArrayList<Reise> abc = new SQL_Persistence().getMonthPerTrip("%Okt%");
                    int pp = new SQL_Persistence().getPricePerMonth("%Okt%");
                    tripReport.setText(String.valueOf(abc).replace("[", "").replace("]", "").replace(",", ""));
                    sum.setText(String.valueOf(pp));

                }else if ("November".equals(selectedItem)){
                    ArrayList<Reise> abc = new SQL_Persistence().getMonthPerTrip("%Nov%");
                    int pp = new SQL_Persistence().getPricePerMonth("%Nov%");
                    tripReport.setText(String.valueOf(abc).replace("[", "").replace("]", "").replace(",", ""));
                    sum.setText(String.valueOf(pp));

                }else if ("Dezember".equals(selectedItem)){
                    ArrayList<Reise> abc = new SQL_Persistence().getMonthPerTrip("%Dez%");
                    int pp = new SQL_Persistence().getPricePerMonth("%Dez%");

                    tripReport.setText(String.valueOf(abc).replace("[", "").replace("]", "").replace(",", ""));
                    sum.setText(String.valueOf(pp));

                }else if ("Alle".equals(selectedItem)){
                    ArrayList<Reise> abc = new SQL_Persistence().getMonthPerTrip("%%");
                    int pp = new SQL_Persistence().getPricePerMonth("%%");

                    tripReport.setText(String.valueOf(abc).replace("[", "").replace("]", "").replace(",", ""));
                    sum.setText(String.valueOf(pp));
                }
            }
        });
    }
}
