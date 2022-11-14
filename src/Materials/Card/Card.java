package Materials.Card;

public class Card
{
    // Flyweight store
    private static final Card[] CARDS = new Card[CardType.values().length];

    private final CardType aCardType;

    // Initialization of the flyweight store
    static
    {
        for( CardType cardType : CardType.values() )
        {
            for (int amount = 0; amount < cardType.getNumberOfCards(); amount++){
                CARDS[cardType.ordinal()] = new Card(cardType);
            }
        }
    }

    // Private constructor
    private Card(CardType pCardType)
    {
        aCardType = pCardType;
    }

    /**
     * @param pCardType The rank of the requested card.
     * @return The unique Card instance with pRank and pSuit
     * @pre pRank != null && pSuit != null
     */
    public static Card get(CardType pCardType)
    {
        assert pCardType != null;
        return CARDS[pCardType.ordinal()];
    }

    /**
     * @return The rank of the card.
     */
    public CardType getRank()
    {
        return aCardType;
    }

    @Override
    public String toString()
    {
        return String.format("%s", aCardType);
    }
}