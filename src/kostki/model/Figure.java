package kostki.model;

public enum Figure {
    ONES, TWOS, THREES, FOURS, FIVES, SIXES, PAIR, TWOPAIR, THREE, KARETA, SFULL, BFULL, SSTREET, BSTREET, DOG, EVEN, ODD, CHANCE, GENERAL;

    @Override
    public String toString() {
        if (Figure.PAIR == this) {
            return "Para";
        } else if (Figure.TWOPAIR == this) {
            return "Dwie Pary";
        } else if (Figure.THREE == this) {
            return "Trójka";
        } else if (Figure.KARETA == this) {
            return "Kareta";
        } else if (Figure.GENERAL == this) {
            return "Generał";
        } else if (Figure.SSTREET == this) {
            return "Mały Street";
        } else if (Figure.BSTREET == this) {
            return "Duży Street";
        } else if (Figure.DOG == this) {
            return "Pies ";
        } else if (Figure.CHANCE == this) {
            return "Szansa";
        } else if (Figure.BFULL == this) {
            return "Duży Full";
        } else if (Figure.SFULL == this) {
            return "Mały Full";
        } else if (Figure.ONES == this) {
            return "Jedynki";
        } else if (Figure.TWOS == this) {
            return "Dwójki";
        } else if (Figure.THREES == this) {
            return "Trójki";
        } else if (Figure.FOURS == this) {
            return "Czwórki";
        } else if (Figure.FIVES == this) {
            return "Piątki";
        } else if (Figure.SIXES == this) {
            return "Szóstki";
        } else if (Figure.EVEN == this) {
            return "Parzyste";
        } else if (Figure.ODD == this) {
            return "Nieparzyste";
        } else return "?";
    }
}
