package Materials.Dice;

/**
 * dice representation
 */
public class Dice implements Comparable{
    private final int MAX = 6;

    private int value;

    public Dice() {}
    public Dice(int value) {
        assert value >= 1 && value <= 6;
        this.value = value;}

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

    /**
     *
     * @param o the object to be compared.
     * @return the order of the object
     */
    @Override
    public int compareTo(Object o) {
        return  (this.getValue() < ((Dice) o).getValue() ? -1 : (this.getValue() == ((Dice) o).getValue() ? 0 : 1));
    }

    /**
     *
     * @param o
     * @return if the object is equal
     */
    @Override
    public boolean equals(Object o)
    {
        Dice d;
        if(!(o instanceof Dice))
        {
            return false;
        }
        else
        {
            d=(Dice)o;
            if(this.value== d.getValue())
            {
                return true;
            }
        }
        return false;
    }
}