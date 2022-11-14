package Materials.Card;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Represents a general-purpose stack of cards. New CardStack
 * instances are initially empty.
 */
public class CardStack implements Iterable<Card>
{
    private final List<Card> aCards;

    public CardStack()
    {
        aCards = new ArrayList<>();
    }


    public CardStack(Iterable<Card> pCards)
    {
        aCards = new ArrayList<>();
        for( Card card : pCards )
        {
            aCards.add(card);
        }
    }

    /**
     * Removes the card at the top of the stack and returns it.
     *
     * @return The card formerly at the top of the stack.
     * @pre !isEmpty()
     */
    public Card pop()
    {
        assert !isEmpty();
        return aCards.remove(aCards.size() - 1);
    }

    /**
     * @return The card at the top of the stack.
     * @pre !isEmpty()
     */
    public Card peek()
    {
        assert !isEmpty();
        return aCards.get(aCards.size() - 1);
    }

    /**
     * Remove all cards from the stack.
     */
    public void clear()
    {
        aCards.clear();
    }

    /**
     * @return True if there are no cards in the stack.
     */
    public boolean isEmpty()
    {
        return aCards.size() == 0;
    }

    @Override
    public Iterator<Card> iterator() { return aCards.iterator(); }
}