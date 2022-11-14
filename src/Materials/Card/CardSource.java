package Materials.Card;

public interface CardSource
{
    /**
     * Removes a card from the source and returns it.
     *
     * @return The card that was removed from the source.
     * @pre !isEmpty()
     */
    Card draw();
    /**
     * @return True if there is no card in the source.
     */
    boolean isEmpty();
}
