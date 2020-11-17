package kostki;

import kostki.model.Figure;
import kostki.model.Player;
import kostki.model.Roll;

import java.util.*;

public class ThirdRound implements Round {
    GameProperties gameProperties = Context.getInstance().getGameProperties();
    ArrayList<Integer> haltedNumbers = new ArrayList<>(Collections.nCopies(5, 0));
    int rollNr = 0;
    Roll r = new Roll();
    GameScreenController gsc;

    public ThirdRound(GameScreenController gameScreenController) {
        gsc = gameScreenController;
    }

    int figureIndex;

    @Override
    public void gameRoll() {
        if (rollNr < 3) {
            rollNr++;
        } else {
            rollNr = 1;
            haltedNumbers = new ArrayList<>(Collections.nCopies(5, 0));
            gsc.resetDeuceStyle();
            r = new Roll();
        }

        r.roll(haltedNumbers, rollNr);
        ArrayList<Integer> a = r.getDiceList();
        gsc.setDeuceImages(a);

        HashMap<Figure, Integer> figMap = r.getFigureList();
        ArrayList<Figure> allFigures = new ArrayList<>(Arrays.asList(Figure.values()));
        if (figMap.containsKey(allFigures.get(figureIndex))) {
            gsc.figureList.add(allFigures.get(figureIndex) + " \t\t" + figMap.get(allFigures.get(figureIndex)));
        } else {
            gsc.figureList.add(allFigures.get(figureIndex) + " \t\t" + 0);
        }


        if (gsc.figureList.isEmpty()) {
            for (Figure f : gsc.currentPlayer.getFigureList()) {
                if (Arrays.asList(Figure.values()).contains(f)) {
                    gsc.figureList.add(f + "\t\t" + 0);
                }
            }
        }
        System.out.println(r.getRollNumber());
        if (rollNr == 3) {
            gsc.disableRollButton(true);
        }
        gsc.setListView();
        gsc.throwNrLabel.setText("Rzut nr " + rollNr);

    }

    @Override
    public void endGameRoll() {
        haltedNumbers = new ArrayList<>(Collections.nCopies(5, 0));
        gsc.resetDeuceStyle();

        String fig = gsc.figures.getSelectionModel().getSelectedItem().replaceAll("\\P{L}+", "");

        String score = gsc.figures.getSelectionModel().getSelectedItem().replaceAll("\\s+", "");
        score = score.replaceAll("[A-Za-zżźćńółęąśŻŹĆĄŚĘŁÓŃ]*", "");

        if (Arrays.asList(Figure.ONES, Figure.TWOS, Figure.THREES, Figure.FOURS, Figure.FIVES, Figure.SIXES).contains(gameProperties.stringToEnum(fig))) {
            System.out.println(score);
            System.out.println(gsc.currentPlayer.getSchoolScore());
            gsc.currentPlayer.addSchoolScore(Integer.parseInt(score));
        } else {
            gsc.currentPlayer.addUniScore(Integer.parseInt(score));
        }

        gsc.putScore(score, gsc.figToTable.get(fig));

        gsc.currentPlayer.removeFigure(gameProperties.stringToEnum(fig));
        boolean containsSchool = false;
        for (Figure f : Arrays.asList(Figure.ONES, Figure.TWOS, Figure.THREES, Figure.FOURS, Figure.FIVES, Figure.SIXES)) {
            if (gsc.currentPlayer.getFigureList().contains(f)) {
                containsSchool = true;
                break;
            }
        }
        if (!containsSchool && gsc.currentPlayer.schoolScoreOnceFlag) {
            gsc.currentPlayer.schoolScoreOnceFlag = false;
            gsc.currentPlayer.verifySchoolScore();
            gsc.putScore(String.valueOf(gsc.currentPlayer.getSchoolScore()), 9);
        }

        if (gsc.currentPlayer.getFigureList().isEmpty()) {
            gsc.putScore(String.valueOf(gsc.currentPlayer.getUniScore() + gsc.currentPlayer.getSchoolScore() + gsc.currentPlayer.getOverallScore()),24);

            gsc.currentPlayer.resetFigureList();
            gsc.currentPlayer.addOverallScore(gsc.currentPlayer.getUniScore() + gsc.currentPlayer.getSchoolScore());
            gsc.currentPlayer.setSchoolScore(0);
            gsc.currentPlayer.setUniScore(0);
            gsc.currentPlayer.schoolScoreOnceFlag = true;
            if (gameProperties.playerIndex == GameProperties.numOfPlayers - 1) {
                gsc.disableRollButton(true);
                gsc.disableEndRollButton(true);
                Player max = gameProperties.getPlayers().get(0);
                for(Player p : gameProperties.getPlayers()){
                    if(p.getOverallScore() > max.getOverallScore()){
                        max = p;
                    }
                }
                gsc.setMessage("WYGRANKO!!! BRAWO DLA "+ max.getPlayerName() );
                gsc.musicPlayer.playMusic();
                return;
            }
        }
        if (gameProperties.playerIndex == GameProperties.numOfPlayers - 1)
            figureIndex++;
        gsc.figureList.clear();
        gsc.disableEndRollButton(true);
        gsc.disableRollButton(false);
        rollNr = 0;
        gameProperties.playerIndex++;
        gsc.currentPlayer = gameProperties.getNextPlayer();
        gsc.setMessage(gsc.currentPlayer.getPlayerName() + "'s turn");

    }

    @Override
    public void setHaltedNumbers(int nr, int i) {
        haltedNumbers.set(nr, i);
    }

}
