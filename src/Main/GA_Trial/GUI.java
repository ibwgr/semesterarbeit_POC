package GA_Trial;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.io.FileInputStream;
import java.io.IOException;


public class GUI extends Application {


    @Override
    public void start(Stage primaryStage) throws IOException {

        GridPane gp = FXMLLoader.load(getClass().getResource(
                "FXML-View.fxml"
        ));


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
                new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, true, true, true, true));
        Background bg = new Background(background);


        // Stage
        BorderPane root = new BorderPane();
        Scene scene = new Scene(root, 675, 900);
        root.setCenter(gp);
        root.setTop(image);
        root.setBackground(bg);


        root.getStylesheets().addAll(getClass().getResource("chart-style.css").toExternalForm());
        root.setId("root");

        primaryStage.setScene(scene);
        primaryStage.setTitle("Swisscom GA-Calculator");
        primaryStage.show();

    }
}