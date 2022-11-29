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
            cards.addAll(instantiateCards(type));
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
                cards.addAll(instantiateCards(cardType));
            }
        }
        Collections.shuffle(cards);
        this.cards = cards;
    }

    private List<Card> instantiateCards( CardType cardType){
        List<Card> cards = new ArrayList<>();
        CardFactory factory = new CardFactory();
        cards.add(factory.getCard(cardType));
        return cards;
    }

    /**
     * Draws a card from the deck: removes the card from the top
     * of the deck and returns it.
     * @return The card drawn.
     * precondition !isEmpty()
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