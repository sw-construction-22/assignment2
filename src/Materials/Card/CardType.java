package Materials.Card;
/**
 * author: daniel lutziger
 * every card type consists of the number of cards as well as the points that should be provided with it
 */
public enum CardType {
    BONUSx200(5, 200),
    BONUSx300(5, 300),
    BONUSx400(5, 400),
    BONUSx500(5, 500),
    BONUSx600(5, 600),
    STRAIGHT(5,  2000),
    CLOVERLEAF(1, 0),
    FIREWORKS(5,  0),
    STOP(10,  0),
    PLUSMINUS(5,  1000),
    X2(5,  0);

    private final int numberOfCards;
    private final int points;

    /**
     * Constructor to create a new ship type
     * @param numberOfCards the amount of cards of this type existing in the game
     */
    CardType(int numberOfCards, int points) {
        this.numberOfCards = numberOfCards;
        this.points = points;
    }

    public int getNumberOfCards() {
        return numberOfCards;
    }

    public int getPoints() {
        return points;
    }
}
