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

    /**
     * Reinitializes the deck with all cards, and shuffles them.
     */
    public void shuffle()
    {
        List<Card> cards = new ArrayList<>();
        for( CardType cardType : CardType.values() )
        {
            for (int amount = 0; amount < cardType.getNumberOfCards(); amount++){
                if (cardType.equals(CardType.STOP)){
                    cards.add(new StopCard(cardType));
                } else if (cardType.equals(CardType.CLOVERLEAF)){
                    cards.add(new CloverleafCard(cardType));
                } else if (cardType.equals(CardType.BONUSx200)){
                    cards.add(new Bonusx200Card(cardType));
                } else if (cardType.equals(CardType.BONUSx300)){
                    cards.add(new Bonusx300Card(cardType));
                } else if (cardType.equals(CardType.BONUSx400)){
                    cards.add(new Bonusx400Card(cardType));
                } else if (cardType.equals(CardType.BONUSx500)){
                    cards.add(new Bonusx500Card(cardType));
                } else if (cardType.equals(CardType.BONUSx600)){
                    cards.add(new Bonusx600Card(cardType));
                } else if (cardType.equals(CardType.X2)){
                    cards.add(new DoubleCard(cardType));
                } else if (cardType.equals(CardType.PLUSMINUS)){
                    cards.add(new PlusMinusCard(cardType));
                } else if (cardType.equals(CardType.FIREWORKS)){
                    cards.add(new FireworksCard(cardType));
                } else if (cardType.equals(CardType.STRAIGHT)){
                    cards.add(new StraightCard(cardType));
                }
            }
        }
        Collections.shuffle(cards);
        this.cards = cards;
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