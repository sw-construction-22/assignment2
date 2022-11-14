package Materials.Card;

public enum CardType {
    BONUSx200(5, 0, '0', 200),
    BONUSx300(5, 0, '0', 300),
    BONUSx400(5, 0, '0', 400),
    BONUSx500(5, 0, '0', 500),
    BONUSx600(5, 0, '0', 600),
    STRAIGHT(5, 0, '0', 0),
    CLOVERLEAF(1, 0, '0', 0),
    FIREWORKS(5, 0, '0', 0),
    STOP(10, 0, '0', 0),
    PLUSMINUS(5, 0, '0', 0),
    X2(5, 0, '0', 0);

    private final int numberOfCards;
    private final int ruleOfCard;

    private final char patternOfCard;
    private final int points;

    /**
     * Constructor to create a new ship type
     * @param numberOfCards the amount of cards of this type existing in the game
     * @param ruleOfCard the rule of the card (for the gameflow)
     * @param patternOfCard the required dice pattern
     */
    CardType(int numberOfCards, int ruleOfCard, char patternOfCard, int points) {
        this.numberOfCards = numberOfCards;
        this.ruleOfCard = ruleOfCard;
        this.patternOfCard = patternOfCard;
        this.points = points;
    }

    public int getNumberOfCards() {
        return numberOfCards;
    }

    public int getRuleOfCard() {
        return ruleOfCard;
    }

    public char getPatternOfCard() {
        return patternOfCard;
    }

    public int getPoints() {
        return points;
    }
}
