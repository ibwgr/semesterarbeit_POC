package GA_Trial;

import javafx.collections.FXCollections;
import javafx.geometry.Side;
import javafx.scene.chart.*;

import java.util.Arrays;


public class Chart_GA {

        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis(0, 500, 50);

        final LineChart<String, Number> lineChart = new LineChart<>(xAxis, yAxis);
        XYChart.Series<String, Number> series2 = new XYChart.Series<>();
        XYChart.Series<String, Number> series1 = new XYChart.Series<>();


   public Chart_GA(){ }

    public Chart chart () {

            xAxis.setCategories(FXCollections.<String>observableArrayList(Arrays.asList
                    ("Jan", "Feb", "Mär", "Apr", "Mai", "Jun", "Jul", "Aug", "Sep", "Okt", "Nov", "Dez")));

            yAxis.setLabel("Reisekosten");

            series2.getData().add(new XYChart.Data<>("Jan", Calculations.gaPerMonth));
            series2.getData().add(new XYChart.Data<>("Feb", Calculations.gaPerMonth));
            series2.getData().add(new XYChart.Data<>("Mar", Calculations.gaPerMonth));
            series2.getData().add(new XYChart.Data<>("Apr", Calculations.gaPerMonth));
            series2.getData().add(new XYChart.Data<>("Mai", Calculations.gaPerMonth));
            series2.getData().add(new XYChart.Data<>("Jun", Calculations.gaPerMonth));
            series2.getData().add(new XYChart.Data<>("Jul", Calculations.gaPerMonth));
            series2.getData().add(new XYChart.Data<>("Aug", Calculations.gaPerMonth));
            series2.getData().add(new XYChart.Data<>("Sep", Calculations.gaPerMonth));
            series2.getData().add(new XYChart.Data<>("Okt", Calculations.gaPerMonth));
            series2.getData().add(new XYChart.Data<>("Nov", Calculations.gaPerMonth));
            series2.getData().add(new XYChart.Data<>("Dez", Calculations.gaPerMonth));


            double jan = new SQL_Persistence().getPricePerMonth("%-01-%");
            series1.getData().add(new XYChart.Data<>("Jan", jan));

            double feb = new SQL_Persistence().getPricePerMonth("%-02-%");
            series1.getData().add(new XYChart.Data<>("Feb", feb));

            double mar = new SQL_Persistence().getPricePerMonth("%-03-%");
            series1.getData().add(new XYChart.Data<>("Mär", mar));

            double apr = new SQL_Persistence().getPricePerMonth("%-04-%");
            series1.getData().add(new XYChart.Data<>("Apr", apr));

            double mai = new SQL_Persistence().getPricePerMonth("%-05-%");
            series1.getData().add(new XYChart.Data<>("Mai", mai));

            double jun = new SQL_Persistence().getPricePerMonth("%-06-%");
            series1.getData().add(new XYChart.Data<>("Jun", jun));

            double jul = new SQL_Persistence().getPricePerMonth("%-07-%");
            series1.getData().add(new XYChart.Data<>("Jul", jul));

            double aug = new SQL_Persistence().getPricePerMonth("%-08-%");
            series1.getData().add(new XYChart.Data<>("Aug", aug));

            double sep = new SQL_Persistence().getPricePerMonth("%-09-%");
            series1.getData().add(new XYChart.Data<>("Sep", sep));

            double okt = new SQL_Persistence().getPricePerMonth("%-10-%");
            series1.getData().add(new XYChart.Data<>("Okt", okt));

            double nov = new SQL_Persistence().getPricePerMonth("%-11-%");

            series1.getData().add(new XYChart.Data<>("Nov", nov));

            double dez = new SQL_Persistence().getPricePerMonth("%-12-%");
            series1.getData().add(new XYChart.Data<>("Dez", dez));


            lineChart.getStylesheets().addAll(getClass().getResource("chart-style.css").toExternalForm());
            lineChart.setLegendSide(Side.BOTTOM);
            series2.setName("GA pro Monat");
            series1.setName("Einzelfahrten");

            if(lineChart.getData().isEmpty()) {
                    lineChart.getData().addAll(series1, series2);
            }

            return lineChart;

        }
   }