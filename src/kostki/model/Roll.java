package kostki.model;


import java.util.*;

public class Roll {
    private int rollNumber = 1;
    private ArrayList<Integer> diceList = new ArrayList<>(Collections.nCopies(5, 0));
    private List<Integer> points = new ArrayList<>();
    private ArrayList<Figure> figureList = new ArrayList<>();
    private HashMap<Figure, Integer> figureMap = new HashMap<>();
    private int firstRollMultiplier = 1;
    private ArrayList<Integer> freq;

    public Roll roll(ArrayList<Integer> haltedDeuce, int rollNo) {
        figureMap.clear();
        if (rollNo == 1) {
            firstRollMultiplier = 2;
            haltedDeuce = new ArrayList<>(Collections.nCopies(5, 0));

        } else {
            firstRollMultiplier = 1;
        }
        Random rand = new Random();
        rand.setSeed(System.currentTimeMillis());
        for (int i = 0; i < 5; i++) {
            if (haltedDeuce.get(i) == 0) {
                Integer r = rand.nextInt(6) + 1;
                diceList.set(i, r);
            }

        }
        System.out.println(diceList);
        getFigures();

        return this;
    }

    private void getFigures() {
        int i;
        freq = new ArrayList<>(Collections.nCopies(6, 0));
        for (int x : diceList) {
            freq.set(x - 1, freq.get(x - 1) + 1);
        }
        boolean isStreet = true;
        List<Integer> pairs = new ArrayList<>();

        for (i = 0; i < 6; i++) {
            pairs.add(i + 1);
            if (freq.get(i) == 2) {
                figureMap.put(Figure.PAIR, 2 * (i + 1));
                isStreet = false;
            } else if (freq.get(i) >= 3) {
                figureMap.put(Figure.PAIR, 2 * (i + 1));
                figureMap.put(Figure.THREE, 3 * (i + 1));
                if (freq.get(i) == 4) {
                    figureMap.put(Figure.TWOPAIR, 4 * (i + 1));
                    figureMap.put(Figure.KARETA, 4 * (i + 1) * firstRollMultiplier);
                } else if (freq.get(i) == 5) {
                    figureMap.put(Figure.PAIR, 12);
                    figureMap.put(Figure.TWOPAIR, 24);
                    figureMap.put(Figure.KARETA, 24 * firstRollMultiplier);
                    figureMap.put(Figure.GENERAL, 5 * (i + 1) * firstRollMultiplier);
                    figureMap.put(Figure.THREE, 18);
                    figureMap.put(Figure.ONES, 2);
                    figureMap.put(Figure.TWOS, 4);
                    figureMap.put(Figure.THREES, 6);
                    figureMap.put(Figure.FOURS, 8);
                    figureMap.put(Figure.FIVES, 10);
                    figureMap.put(Figure.SIXES, 12);
                    figureMap.put(Figure.SSTREET, 15 * firstRollMultiplier);
                    figureMap.put(Figure.BSTREET, 20 * firstRollMultiplier);
                    figureMap.put(Figure.SFULL, 21 * firstRollMultiplier);
                    figureMap.put(Figure.BFULL, 30 * firstRollMultiplier);
                    figureMap.put(Figure.DOG, 19 * firstRollMultiplier);
                    figureMap.put(Figure.ODD, 25 * firstRollMultiplier);
                    figureMap.put(Figure.EVEN, 30 * firstRollMultiplier);
                    figureMap.put(Figure.CHANCE, 30);
                    return;
                }
                isStreet = false;
            } else {
                pairs.remove(pairs.size() - 1);
            }
        }
        if (pairs.size() == 2) {
            figureMap.put(Figure.TWOPAIR, 2 * pairs.get(0) + 2 * pairs.get(1));
        }
        if (freq.get(1) == 0 && freq.get(3) == 0 && freq.get(5) == 0) {
            figureMap.put(Figure.ODD, (freq.get(0) + freq.get(2) * 3 + freq.get(4) * 5) * firstRollMultiplier);
        } else if (freq.get(0) == 0 && freq.get(2) == 0 && freq.get(4) == 0) {
            figureMap.put(Figure.EVEN, (freq.get(1) * 2 + freq.get(3) * 4 + freq.get(5) * 6) * firstRollMultiplier);
        }
        if (isStreet) {
            if (freq.get(0) == 0) {
                figureMap.put(Figure.BSTREET, 20 * firstRollMultiplier);
            } else if (freq.get(5) == 0) {
                figureMap.put(Figure.SSTREET, 15 * firstRollMultiplier);
            } else {
                if (freq.get(1) == 0) {
                    figureMap.put(Figure.DOG, 19 * firstRollMultiplier);
                } else if (freq.get(2) == 0) {
                    figureMap.put(Figure.DOG, 18 * firstRollMultiplier);
                } else if (freq.get(3) == 0) {
                    figureMap.put(Figure.DOG, 17 * firstRollMultiplier);
                } else {
                    figureMap.put(Figure.DOG, 16 * firstRollMultiplier);
                }
            }
        }
        figureMap.put(Figure.CHANCE, freq.get(0) + 2 * freq.get(1) + 3 * freq.get(2) + 4 * freq.get(3) + 5 * freq.get(4) + 6 * freq.get(5));
        figureMap.put(Figure.ONES, freq.get(0) - 3);
        figureMap.put(Figure.TWOS, 2 * freq.get(1) - 6);
        figureMap.put(Figure.THREES, 3 * freq.get(2) - 9);
        figureMap.put(Figure.FOURS, 4 * freq.get(3) - 12);
        figureMap.put(Figure.FIVES, 5 * freq.get(4) - 15);
        figureMap.put(Figure.SIXES, 6 * freq.get(5) - 18);
        if (figureMap.containsKey(Figure.THREE)) {
            for (i = 0; i < 6; i++) {
                if (freq.get(i) == 2) {
                    if (figureMap.get(Figure.THREE) / 3 > 3) {
                        figureMap.put(Figure.BFULL, (figureMap.get(Figure.THREE) + (i + 1) * 2) * firstRollMultiplier);
                    } else {
                        figureMap.put(Figure.SFULL, (figureMap.get(Figure.THREE) + (i + 1) * 2) * firstRollMultiplier);
                    }
                }
            }
        }
    }

    public int getRollNumber() {
        return rollNumber;
    }

    public ArrayList<Integer> getDiceList() {
        return diceList;
    }

    public List<Integer> getPoints() {
        return points;
    }

    public HashMap<Figure, Integer> getFigureList() {
        return figureMap;
    }

}