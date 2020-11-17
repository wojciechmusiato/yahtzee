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
            case "Para":        return Figure.PAIR;
            case "DwiePary":    return Figure.TWOPAIR;
            case "Trójka":      return Figure.THREE;
            case "Kareta":      return Figure.KARETA;
            case "Generał":     return Figure.GENERAL;
            case "MałyStreet":  return Figure.SSTREET;
            case "DużyStreet":  return Figure.BSTREET;
            case "Pies":        return Figure.DOG;
            case "Szansa":      return Figure.CHANCE;
            case "MałyFull":    return Figure.SFULL;
            case "DużyFull":    return Figure.BFULL;
            case "Jedynki":     return Figure.ONES;
            case "Dwójki":      return Figure.TWOS;
            case "Trójki":      return Figure.THREES;
            case "Czwórki":     return Figure.FOURS;
            case "Piątki":      return Figure.FIVES;
            case "Szóstki":     return Figure.SIXES;
            case "Nieparzyste":     return Figure.ODD;
            case "Parzyste":     return Figure.EVEN;

        }
        return Figure.PAIR;
    }
}
