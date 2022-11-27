package Materials.Card;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Represents a deck of playing cards that can be inherited.
 */
public class Deck implements Iterable<Card> {
    private List<Card> cards = new ArrayList<>();

    /**
     * Creates a new deck of cards, shuffled.
     */
    public Deck()
    {
        shuffle();
    }

    public Deck(CardType type)
    {
        shuffle(type);
    }

    public void shuffle(CardType type){
        List<Card> cards = new ArrayList<>();
        for (int amount = 0; amount < type.getNumberOfCards(); amount++){
            cards.addAll(instantiateCards(cards, type));
        }
        Collections.shuffle(cards);
        this.cards = cards;
    }
    /**
     * Reinitializes the deck with all cards, and shuffles them.
     */
    public void shuffle()
    {
        List<Card> cards = new ArrayList<>();
        for( CardType cardType : CardType.values() )
        {
            for (int amount = 0; amount < cardType.getNumberOfCards(); amount++){
                cards.addAll(instantiateCards(cards, cardType));
            }
        }
        Collections.shuffle(cards);
        this.cards = cards;
    }

    private List<Card> instantiateCards(List<Card> cards, CardType cardType){
        if (cardType.equals(CardType.STOP)){
            cards.add(new StopCard(cardType));
        } else if (cardType.equals(CardType.CLOVERLEAF)){
            cards.add(new CloverleafCard(cardType));
        } else if (cardType.equals(CardType.BONUSx200)){
            cards.add(new BonusCard(cardType));
        } else if (cardType.equals(CardType.BONUSx300)){
            cards.add(new BonusCard(cardType));
        } else if (cardType.equals(CardType.BONUSx400)){
            cards.add(new BonusCard(cardType));
        } else if (cardType.equals(CardType.BONUSx500)){
            cards.add(new BonusCard(cardType));
        } else if (cardType.equals(CardType.BONUSx600)){
            cards.add(new BonusCard(cardType));
        } else if (cardType.equals(CardType.X2)){
            cards.add(new DoubleCard(cardType));
        } else if (cardType.equals(CardType.PLUSMINUS)){
            cards.add(new PlusMinusCard(cardType));
        } else if (cardType.equals(CardType.FIREWORKS)){
            cards.add(new FireworksCard(cardType));
        } else if (cardType.equals(CardType.STRAIGHT)){
            cards.add(new StraightCard(cardType));
        }
        return cards;
    }

    /**
     * Draws a card from the deck: removes the card from the top
     * of the deck and returns it.
     * @return The card drawn.
     * @pre !isEmpty()
     */
    public Card draw()
    {
        assert !isEmpty();
        return cards.remove(cards.size()-1);//last value
    }

    /**
     * @return True if and only if there are no cards in the deck.
     */
    public boolean isEmpty()
    {
        return cards.isEmpty();
    }

    @Override
    public Iterator<Card> iterator()
    {
        return cards.iterator();
    }
}