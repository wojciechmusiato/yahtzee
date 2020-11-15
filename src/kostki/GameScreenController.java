package kostki;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import kostki.model.Player;

import java.util.*;


public class GameScreenController {
    @FXML
    public Button confirmButton;
    @FXML
    public Button nextSong;
    @FXML
    public Button prevSong;
    @FXML
    public Label throwNrLabel;
    @FXML
    private Button musicButton;
    MusicPlayer musicPlayer = new MusicPlayer(this);
    Player currentPlayer;
    ArrayList<Image> actualimages = new ArrayList<>();
    ObservableList<String> figureList = FXCollections.observableArrayList();
    Round round;
    HashMap<String, Integer> figToTable;
    private GameProperties gameProperties;

    @FXML
    public Button endRollButton;
    @FXML
    public Button rollButton;
    @FXML
    ListView<String> figures = new ListView<>();
    @FXML
    ScrollPane scrollPaneScore;
    @FXML
    private Label infoText;
    @FXML
    private HBox deuceBox;
    @FXML
    GridPane scorePane;

    @FXML
    public void initialize() {
        gameProperties = Context.getInstance().getGameProperties();
        initializeScoreTable();
    }


    private void initializeScoreTable() {
        figToTable = new HashMap<>();
        figToTable.put("Jedynki", 3);
        figToTable.put("Dwójki", 4);
        figToTable.put("Trójki", 5);
        figToTable.put("Czwórki", 6);
        figToTable.put("Piątki", 7);
        figToTable.put("Szóstki", 8);
        figToTable.put("Para", 11);
        figToTable.put("DwiePary", 12);
        figToTable.put("Trójka", 13);
        figToTable.put("Kareta", 14);
        figToTable.put("MałyFull", 15);
        figToTable.put("DużyFull", 16);
        figToTable.put("MałyStreet", 17);
        figToTable.put("DużyStreet", 18);
        figToTable.put("Pies", 19);
        figToTable.put("Parzyste", 20);
        figToTable.put("Nieparzyste", 21);
        figToTable.put("Szansa", 22);
        figToTable.put("Generał", 23);

        scorePane.add(new Label("1"), 0, 3, 1, 1);
        scorePane.add(new Label("2"), 0, 4, 1, 1);
        scorePane.add(new Label("3"), 0, 5, 1, 1);
        scorePane.add(new Label("4"), 0, 6, 1, 1);
        scorePane.add(new Label("5"), 0, 7, 1, 1);
        scorePane.add(new Label("6"), 0, 8, 1, 1);
        scorePane.add(new Label("Σ"), 0, 9, 1, 1);
        scorePane.add(new Label("P"), 0, 11, 1, 1);
        scorePane.add(new Label("2P"), 0, 12, 1, 1);
        scorePane.add(new Label("T"), 0, 13, 1, 1);
        scorePane.add(new Label("K"), 0, 14, 1, 1);
        scorePane.add(new Label("MF"), 0, 15, 1, 1);
        scorePane.add(new Label("DF"), 0, 16, 1, 1);
        scorePane.add(new Label("MS"), 0, 17, 1, 1);
        scorePane.add(new Label("DS"), 0, 18, 1, 1);
        scorePane.add(new Label("P"), 0, 19, 1, 1);
        scorePane.add(new Label("PA"), 0, 20, 1, 1);
        scorePane.add(new Label("NP"), 0, 21, 1, 1);
        scorePane.add(new Label("SZ"), 0, 22, 1, 1);
        scorePane.add(new Label("G"), 0, 23, 1, 1);
        scorePane.add(new Label("Σ"), 0, 24, 1, 1);
        ArrayList<ColumnConstraints> constraints = new ArrayList<>();
        int i, j;
        for (i = 0; i < 3; i++) {
            for (j = 0; j < GameProperties.numOfPlayers; j++) {

                scorePane.add(new Label(gameProperties.getPlayers().get(j).getPlayerName()), GameProperties.numOfPlayers * i + j + 1, 2);
                constraints.add(new ColumnConstraints(50, 50, 100, Priority.ALWAYS, HPos.CENTER, true));

            }
        }
        scorePane.getColumnConstraints().addAll(constraints);

        for (i = 0; i < 1 + GameProperties.numOfPlayers * 3; i++) {
            for (j = 0; j < 25; j++) {
                StackPane p = new StackPane();
                if (j != 1 && j != 10 || i == 0) {
                    p.setStyle("-fx-border-color: BLACK;");
                    //p.setStyle("-fx-border-style: solid solid hidden solid; -fx-border-color: red;");
                } else if (j != 1 && j != 10 || i == GameProperties.numOfPlayers * 3) {
                    p.setStyle("-fx-border-style: solid solid solid hidden; -fx-border-color: black;");
                }
                p.setMaxWidth(Double.MAX_VALUE);
                scorePane.add(p, i, j);
                GridPane.setColumnIndex(p, i);
                GridPane.setRowIndex(p, j);

            }

        }

        Label l = new Label("SZKOŁA");
        l.setStyle("\"-fx-background-color: #FFFFFF;\"");

        scorePane.add(l, 1, 1, gameProperties.getPlayers().size() * 3, 1);
        scorePane.add(new Label("UNIWERSYTET"), 1, 10, gameProperties.getPlayers().
                size() * 3, 1);
        throwNrLabel.setText("Rzucaj debilu");


        actualimages.add(new Image(getClass().getResourceAsStream("fxml/images/1.jpg")));
        actualimages.add(new Image(getClass().getResourceAsStream("fxml/images/2.jpg")));
        actualimages.add(new Image(getClass().getResourceAsStream("fxml/images/3.jpg")));
        actualimages.add(new Image(getClass().getResourceAsStream("fxml/images/4.jpg")));
        actualimages.add(new Image(getClass().getResourceAsStream("fxml/images/5.jpg")));
        actualimages.add(new Image(getClass().getResourceAsStream("fxml/images/6.jpg")));
        currentPlayer = gameProperties.getNextPlayer();
        figures.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (!figures.getSelectionModel().getSelectedItems().isEmpty()) {
                if(gameProperties.roundNumber == 1 ) {
                    if (((SecondRound) round).rollNr == 3) {
                        endRollButton.setDisable(false);
                        rollButton.setDisable(true);
                    } else if (!confirmButton.isVisible()) {
                        rollButton.setDisable(false);
                        endRollButton.setDisable(false);
                    } else {
                        rollButton.setDisable(true);
                    }
                }else {
                    endRollButton.setDisable(false);
                }
            }
            confirmButton.setDisable(false);
        });

        round = new FirstRound(this);
        endRollButton.setDisable(true);
        infoText.setText(currentPlayer.getPlayerName() + "'s turn");
    }


    public void setGameProperties(GameProperties gameProperties) {
        this.gameProperties = gameProperties;
    }


    public void makeRoll(ActionEvent actionEvent) {
        endRollButton.setDisable(true);
        figureList.clear();
        round.gameRoll();

    }

    private void setBorder(MouseEvent mouseEvent, int nr) {
        if (!((Pane) mouseEvent.getSource()).getStyle().isEmpty()) {
            ((Pane) mouseEvent.getSource()).setStyle(null);
            round.setHaltedNumbers(nr, 0);
        } else {
            ((Pane) mouseEvent.getSource()).setStyle(
                    "-fx-border-style: solid inside;" +
                            "-fx-border-width: 3;" +
                            "-fx-border-radius: 2;" +
                            "-fx-border-color: blue;");
            round.setHaltedNumbers(nr, 1);
        }
    }

    public void dice1Clicked(MouseEvent mouseEvent) {
        setBorder(mouseEvent, deuceBox.getChildren().indexOf(mouseEvent.getSource()));
    }

    public void dice2Clicked(MouseEvent mouseEvent) {
        setBorder(mouseEvent, deuceBox.getChildren().indexOf(mouseEvent.getSource()));
    }

    public void dice3Clicked(MouseEvent mouseEvent) {
        setBorder(mouseEvent, deuceBox.getChildren().indexOf(mouseEvent.getSource()));
    }

    public void dice4Clicked(MouseEvent mouseEvent) {
        setBorder(mouseEvent, deuceBox.getChildren().indexOf(mouseEvent.getSource()));
    }

    public void dice5Clicked(MouseEvent mouseEvent) {
        setBorder(mouseEvent, deuceBox.getChildren().indexOf(mouseEvent.getSource()));
    }

    public void endRoll(ActionEvent actionEvent) {
        round.endGameRoll();
    }

    private Node getNodeFromGridPane(GridPane gridPane, int col, int row) {
        for (int i = 26; i < gridPane.getChildren().size(); i++) {
            if (GridPane.getColumnIndex(gridPane.getChildren().get(i)) == col && GridPane.getRowIndex(gridPane.getChildren().get(i)) == row) {
                return gridPane.getChildren().get(i);
            }
        }
        return null;
    }

    public void resetDeuceStyle() {
        for (int i = 0; i < deuceBox.getChildren().size(); i++) {
            deuceBox.getChildren().get(i).setStyle(null);
        }
    }

    public void setDeuceImages(ArrayList<Integer> a) {
        for (int i = 0; i < deuceBox.getChildren().size(); i++) {
            StackPane s = ((StackPane) deuceBox.getChildren().get(i));
            ImageView v = ((ImageView) s.getChildren().get(0));
            v.setImage(actualimages.get(a.get(i) - 1));
        }
    }

    public void disableRollButton(boolean b) {
        rollButton.setDisable(b);
    }

    public void setListView() {
        figures.setItems(figureList);
    }

    public void disableEndRollButton(boolean b) {
        endRollButton.setDisable(b);
    }

    public void putScore(String score, Integer index) {
        ((StackPane) getNodeFromGridPane(scorePane, 1 + (gameProperties.roundNumber * GameProperties.numOfPlayers) +
                gameProperties.playerIndex, index)).getChildren().add(new Label(score));
    }

    public void setSecondRound() {
        round = new SecondRound(this);
    }
    public void setThirdRound(){
        round = new ThirdRound(this);
    }
    public void onConfirm(ActionEvent actionEvent) {
        confirmButton.setVisible(false);
        rollButton.setDisable(false);
        ((SecondRound) round).lockChoice();
    }
    public void setMessage(String message){
        infoText.setText(message);
    }

    public void playMusic(ActionEvent actionEvent) {
        musicPlayer.playMusic();
    }

    public void stopMusic(ActionEvent actionEvent) {
        musicPlayer.stop();
    }

    public void prevSong(ActionEvent actionEvent) {
        musicPlayer.prev();
    }

    public void nextSong(ActionEvent actionEvent) {
        musicPlayer.next();
    }
}
