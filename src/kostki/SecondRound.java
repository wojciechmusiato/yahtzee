package kostki;

import kostki.model.Figure;
import kostki.model.Roll;

import java.lang.reflect.Array;
import java.util.*;

public class SecondRound implements Round {
    public Figure selectedFigure;
    GameProperties gameProperties = Context.getInstance().getGameProperties();
    ArrayList<Integer> haltedNumbers = new ArrayList<>(Collections.nCopies(5, 0));
    public int rollNr = 0;
    Roll r = new Roll();
    GameScreenController gsc;
    public SecondRound(GameScreenController gameScreenController) {
        gsc = gameScreenController;
    }

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
        System.out.println(gsc.currentPlayer.getPlayerName());

        r.roll(haltedNumbers, rollNr);
        ArrayList<Integer> a = r.getDiceList();
        gsc.setDeuceImages(a);

        HashMap<Figure, Integer> figMap = r.getFigureList();
        Iterator it = figMap.entrySet().iterator();

        if(rollNr>1){

            while (it.hasNext()) {
                Map.Entry pair = (Map.Entry) it.next();
                if (gsc.currentPlayer.getFigureList().contains((Figure) pair.getKey()) && selectedFigure == pair.getKey()) {
                    gsc.figureList.add(pair.getKey() + " \t\t" + pair.getValue());
                }
                it.remove();
            }
            if (gsc.figureList.isEmpty()) {
                        gsc.figureList.add(selectedFigure + "\t\t" + 0);


            }
        }else{

            gsc.confirmButton.setVisible(true);
            gsc.confirmButton.setDisable(true);
            gsc.disableRollButton(true);

            for( int i = 0 ; i < gsc.currentPlayer.getFigureList().size();i++){
                if(figMap.containsKey(gsc.currentPlayer.getFigureList().get(i))){
                    gsc.figureList.add(gsc.currentPlayer.getFigureList().get(i) + "  \t\t" + figMap.get(gsc.currentPlayer.getFigureList().get(i)));
                }else{
                    gsc.figureList.add(gsc.currentPlayer.getFigureList().get(i) + " \t\t" + 0);
                }


            }

        }
        System.out.println(selectedFigure + " 3");

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
        String score = gsc.figures.getSelectionModel().getSelectedItem().replaceAll("\\s+","");
        score = score.replaceAll("[A-Za-zżźćńółęąśŻŹĆĄŚĘŁÓŃ]*", "");

        if (Arrays.asList(Figure.ONES, Figure.TWOS, Figure.THREES, Figure.FOURS, Figure.FIVES, Figure.SIXES).contains(gameProperties.stringToEnum(fig))) {
            System.out.println(score);
            System.out.println(gsc.currentPlayer.getSchoolScore());
            gsc.currentPlayer.addSchoolScore(Integer.parseInt(score));
        } else {
            gsc.currentPlayer.addUniScore(Integer.parseInt(score));
        }

        gsc.putScore(score,gsc.figToTable.get(fig));

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
            gsc.putScore(String.valueOf(gsc.currentPlayer.getSchoolScore()),9);
        }

        if (gsc.currentPlayer.getFigureList().isEmpty()) {
            gsc.putScore(String.valueOf(gsc.currentPlayer.getUniScore() + gsc.currentPlayer.getSchoolScore() + gsc.currentPlayer.getOverallScore()),24);

            gsc.currentPlayer.resetFigureList();
            gsc.currentPlayer.addOverallScore(gsc.currentPlayer.getUniScore() + gsc.currentPlayer.getSchoolScore() );
            gsc.currentPlayer.setSchoolScore(0);
            gsc.currentPlayer.setUniScore(0);
            gsc.currentPlayer.schoolScoreOnceFlag = true;
            if (gameProperties.playerIndex == GameProperties.numOfPlayers-1) {
                gameProperties.nextRound();
                gsc.setThirdRound();
            }
        }
        gsc.figureList.clear();
        gsc.disableEndRollButton(true);
        gsc.disableRollButton(false);


        rollNr = 0;
        gameProperties.playerIndex++;
        gsc.currentPlayer = gameProperties.getNextPlayer();
        gsc.setMessage(gsc.currentPlayer.getPlayerName() + "'s turn");
        gsc.throwNrLabel.setText("Rzucaj, " + GameProperties.randomSlur() );

    }
    @Override
    public void setHaltedNumbers(int nr, int i){
        haltedNumbers.set(nr, i);
    }

    public void lockChoice() {
        String fig = gsc.figures.getSelectionModel().getSelectedItem().replaceAll("\\P{L}+", "");
        String score = gsc.figures.getSelectionModel().getSelectedItem().replaceAll("\\s+","");
        score = score.replaceAll("[A-Za-zżźćńółęąśŻŹĆĄŚĘŁÓŃ]*", "");

        selectedFigure = gameProperties.stringToEnum(fig);
        gsc.figureList.clear();
        gsc.figureList.add(fig + " \t\t" + score);
        gsc.setListView();
        gsc.endRollButton.setDisable(true);
    }

}
