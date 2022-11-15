package Materials.Dice;
public class Dice {
    private final int MAX = 6;  // maximum face value

    private int value;  // current value showing on the die

    public Dice() {value = roll();}

    /**
     * Rolle the dice
     * @return rolled dice value
     */
    private int roll()
    {
        value = (int)(Math.random() * MAX) + 1;
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