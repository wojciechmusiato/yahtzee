package kostki.model;

public enum Figure {
    ACES, TWOS, THREES, FOURS, FIVES, SIXES, PAIR, TWOPAIR, THREE, KARETA, SFULL, BFULL, SSTRAIGHT, BSTREET, DOG, EVEN, ODD, CHANCE, YAHTZEE;

    @Override
    public String toString() {
        if (Figure.PAIR == this) {
            return "Pair";
        } else if (Figure.TWOPAIR == this) {
            return "Twopair";
        } else if (Figure.THREE == this) {
            return "Three";
        } else if (Figure.KARETA == this) {
            return "Four-of-a-kind";
        } else if (Figure.YAHTZEE == this) {
            return "Yahtzee";
        } else if (Figure.SSTRAIGHT == this) {
            return "Small Straight";
        } else if (Figure.BSTREET == this) {
            return "Big Straight";
        } else if (Figure.DOG == this) {
            return "Dog ";
        } else if (Figure.CHANCE == this) {
            return "Chance";
        } else if (Figure.BFULL == this) {
            return "Big Full";
        } else if (Figure.SFULL == this) {
            return "Small Full";
        } else if (Figure.ACES == this) {
            return "Aces";
        } else if (Figure.TWOS == this) {
            return "Twos";
        } else if (Figure.THREES == this) {
            return "Threes";
        } else if (Figure.FOURS == this) {
            return "Fours";
        } else if (Figure.FIVES == this) {
            return "Fives";
        } else if (Figure.SIXES == this) {
            return "Sixes";
        } else if (Figure.EVEN == this) {
            return "Even";
        } else if (Figure.ODD == this) {
            return "Odd";
        } else return "ERROR";
    }
}
