package kostki;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception{

        FXMLLoader load = new FXMLLoader(this.getClass().getResource("fxml/InitialScreen.fxml"));
        AnchorPane pane = load.load();

        Scene scene = new Scene(pane,250,300);
        primaryStage.setTitle("Yahtzee");
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();





    }


    public static void main(String[] args) {
        launch(args);
    }
}
