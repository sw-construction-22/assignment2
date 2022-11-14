package Materials.Card;

public class StopDecorator implements CardSource
{
    private final CardSource aElement;

    public StopDecorator(CardSource pCardSource)
    { aElement = pCardSource; }
    public boolean isEmpty()
    { return aElement.isEmpty(); }
    public Card draw()
    {
        // 1. Delegate the original request to the decorated object
        Card card = aElement.draw();
        // 2. Implement the decoration

        /**
         * TODO: END TURN
         */

        return card;
    }
}
