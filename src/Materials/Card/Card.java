package Materials.Card;

public abstract class Card implements CardRule {
    protected static final Card[] CARDS = new Card[CardType.values().length];
    protected CardType cardType;

    public static Card get(CardType pCardType) {
        assert pCardType != null;
        return CARDS[pCardType.ordinal()];
    }

    /**
     * @return The the card type.
     */
    public CardType getCardType()
    {
        return cardType;
    }
}
