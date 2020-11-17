package kostki;

import javafx.beans.property.*;
import javafx.collections.ObservableList;
import kostki.model.Figure;
import kostki.model.Player;

import java.util.ArrayList;

public class GameProperties {



    public ArrayList<Player> getPlayers() {
        return players; }

    public int playerIndex = 0 ;
    private ArrayList<Player> players;
    public static int numOfPlayers;
    public int roundNumber = 0;
    private ObservableList<String> playerNumberList;
    private ListProperty<String> listProperty = new SimpleListProperty<>();

    private InitialScreenController initialScreenController;

    public GameProperties() {
        this.players = new ArrayList<>();

    }
    public void nextRound(){
        roundNumber++;

    }
    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }


    public void setPlayerNames(ArrayList<String> playerNames) {
        for (int i = 0; i < numOfPlayers; i++) {
            players.add(new Player(playerNames.get(i)));
        }
    }

    public Player getNextPlayer() {
        if (playerIndex == numOfPlayers) playerIndex = 0;
        Player p = players.get(playerIndex);
        return p;
    }

    public Figure stringToEnum(String s){
        switch (s){
            case "Pair":        return Figure.PAIR;
            case "Twopair":    return Figure.TWOPAIR;
            case "Three":      return Figure.THREE;
            case "Four-of-a-kind":      return Figure.KARETA;
            case "Yahtzee":     return Figure.YAHTZEE;
            case "SmallStraight":  return Figure.SSTRAIGHT;
            case "BigStraight":  return Figure.BSTREET;
            case "Dog":        return Figure.DOG;
            case "Chance":      return Figure.CHANCE;
            case "SmallFull":    return Figure.SFULL;
            case "BigFull":    return Figure.BFULL;
            case "Aces":     return Figure.ACES;
            case "Twos":      return Figure.TWOS;
            case "Threes":      return Figure.THREES;
            case "Fours":     return Figure.FOURS;
            case "Fives":      return Figure.FIVES;
            case "Sixes":     return Figure.SIXES;
            case "Odd":     return Figure.ODD;
            case "Even":     return Figure.EVEN;

        }
        return Figure.PAIR;
    }
}
