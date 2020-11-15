package kostki;

import javafx.application.Platform;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.ArrayList;


public class InitialScreenController {
    GameProperties gameProperties = new GameProperties();
    GameScreenController   gameScreenController = new GameScreenController();


    @FXML
    VBox vBoxPlayers;

    @FXML
    Button exitButton;

    @FXML
    Button startButton;

    @FXML
    ComboBox<String> comboPlayerNumber = new ComboBox<>();

    ArrayList<String> playerNames = new ArrayList<>();
    ObservableList<String> playerNumber;

    @FXML
    public void initialize() {
        gameProperties = Context.getInstance().getGameProperties();
        gameScreenController = new GameScreenController();
        playerNumber = FXCollections.observableArrayList("1","2","3","4");
        comboPlayerNumber.setItems(playerNumber);
        gameScreenController.setGameProperties(gameProperties);
        showActualPlayers(2);
    }

    @FXML
    private void showPlayers(ActionEvent actionEvent) {
        String playerNum = ((ComboBox<String>)actionEvent.getSource()).getSelectionModel().getSelectedItem();
        playerNames.clear();
        vBoxPlayers.getChildren().clear();
        showActualPlayers(Integer.parseInt(playerNum));

    }
    private void showActualPlayers(int num){
        for(int i = 0; i < num; i++){
            TextField textField = new TextField("Koteł "+ (i + 1));
            vBoxPlayers.getChildren().add(textField);
        }
        GameProperties.numOfPlayers=num;

    }

    public void exit(ActionEvent actionEvent) {
        Platform.exit();
    }

    public void startGame(ActionEvent actionEvent) throws IOException {
        for(int i = 0; i < GameProperties.numOfPlayers ; i++){
            playerNames.add(((TextField)vBoxPlayers.getChildren().get(i)).getText());
        }
        gameProperties.setPlayerNames(playerNames);
        FXMLLoader load = new FXMLLoader(this.getClass().getResource("fxml/GameScreen.fxml"));
        try {
            AnchorPane root = load.load();
            Stage stage = new Stage();
            root.setStyle("-fx-background-color: WHITE");
            Image image = new Image(getClass().getResourceAsStream("fxml/images/roll.png"));
            stage.getIcons().add(image);
            stage.setTitle("bones");
            stage.setScene(new Scene(root, 760, 580));
            stage.setResizable(false);
            stage.show();
            ((Node) (actionEvent.getSource())).getScene().getWindow().hide();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(gameProperties + "1");


    }



}
