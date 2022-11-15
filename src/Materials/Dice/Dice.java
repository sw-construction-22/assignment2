package Materials.Dice;
public class Dice {
    private final int MAX = 6;

    private int value;

    public Dice() {}

    /**
     * Rolle the dice
     * @return rolled dice value
     */
    public int roll()
    {
        value = (int)(Math.random() * MAX) + 1;
        return value;
    }

    public int getValue(){
        return value;
    }

    /**
     * @return String representation of the dice
     */
    public String toString()
    {
        String result = Integer.toString(value);
        return result;
    }
}