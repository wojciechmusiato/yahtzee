package kostki;

import javafx.beans.property.*;
import javafx.collections.ObservableList;
import kostki.model.Figure;
import kostki.model.Player;

import java.util.ArrayList;
import java.util.Random;

public class GameProperties {

    public static String randomSlur() {
        switch (new Random().nextInt(20)+1){
            case 1: return "dziadu";
            case 2: return "BABO";
            case 3: return "kochanie";
            case 4: return "dzidziu";
            case 5: return "ty ciulu";
            case 6: return "przyjacielu";
            case 7: return "bo czas leci!";
            case 8: return "koteł";
            case 9: return "cinziu";
            case 10: return "bo nie ręczę za siebie";
            case 11: return "rzucaj Kirwa";
            case 12: return "gepardziku";
            case 13: return "fujarko";
            case 14: return "skarbeńku";
            case 15: return "misiu";
            case 16: return "mala blondyneczko";
            case 17: return "mała ;)";
            case 18: return "bo wpierdol bd";
            case 19: return "serio.......";
            case 20: return "ale jebac te kości prądem";
        }
        return "suko";
    }

    public ArrayList<Player> getPlayers() {
        return players; }

    int playerIndex = 0 ;
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
