package Materials.Combinations;

import Materials.Dice.Dice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum DicePattern {
    TRIPPLE_ONE(new ArrayList<>(Arrays.asList(new Dice(1), new Dice(1), new Dice(1))), 1000),
    TRIPPLE_TWO(new ArrayList<>(Arrays.asList(new Dice(2), new Dice(2), new Dice(2))), 200),
    TRIPPLE_THREE(new ArrayList<>(Arrays.asList(new Dice(3), new Dice(3), new Dice(3))), 300),
    TRIPPE_FOUR(new ArrayList<>(Arrays.asList(new Dice(4), new Dice(4), new Dice(4))), 400),
    TRIPPLE_FIVE(new ArrayList<>(Arrays.asList(new Dice(5), new Dice(5), new Dice(5))), 500),
    TRIPPLE_SIX(new ArrayList<>(Arrays.asList(new Dice(6), new Dice(6), new Dice(6))), 600),
    STRAIGHT(new ArrayList<>(Arrays.asList(new Dice(1), new Dice(2), new Dice(3), new Dice(4), new Dice(5), new Dice(6))),  2000),
    SINGLE_ONE(new ArrayList<>(Arrays.asList(new Dice(1))), 100),
    SINGLE_FIVE(new ArrayList<>(Arrays.asList(new Dice(5))), 50);

    private final List<Dice> requiredPattern;
    private final int value;
    DicePattern(List<Dice> requiredPattern, int value){
        this.requiredPattern = requiredPattern;this.value = value;
    }

    public List<Dice> getRequiredPattern(){
        return new ArrayList<>(requiredPattern);
    }
    public int getValue(){return this.value;};
}
