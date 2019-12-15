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
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;

public class GUI_Trial extends Application {

    @Override

        public void start(Stage primaryStage) throws FileNotFoundException {

        GridPane gp = new GridPane();

        primaryStage.show();
        primaryStage.setTitle("GA-Trial");

        Label reiseziel= new Label("Reiseziel");
        ChoiceBox cb = new ChoiceBox(FXCollections.observableArrayList());
        cb.getItems().addAll("Chur", "Zürich", "Bern", "Olten");
        cb.getSelectionModel().selectFirst();

        primaryStage.setScene(new Scene(gp, 1000, 400));
        gp.setPadding(new Insets(20, 20, 20, 20));

        gp.add(reiseziel, 0, 0);
        gp.add(cb, 0, 1);

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
        TextField date = new TextField();
        date.setText(String.valueOf(LocalDate.now()));

        gp.add(reisekosten, 1, 0);
        gp.add(price, 1, 1);

        gp.add(datumsfeld, 2, 0);
        gp.add(date, 2, 1);

        Button enterReise = new Button("Reise erfassen");
        enterReise.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                new SQL_Persistence().setTrip((String) cb.getSelectionModel().getSelectedItem(), price.getText(), date.getText());
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

        gp.add(image, 3,5);
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
    }


    public static void main(String[] args) {
        launch(args);

    }
}
