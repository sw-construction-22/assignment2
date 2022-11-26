package Materials.Combinations;

import Materials.Dice.Dice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum DicePattern {
    TRIPPLE_ONE(new ArrayList<>(Arrays.asList(new Dice(1), new Dice(1), new Dice(1)))),
    TRIPPLE_TWO(new ArrayList<>(Arrays.asList(new Dice(2), new Dice(2), new Dice(2)))),
    TRIPPLE_THREE(new ArrayList<>(Arrays.asList(new Dice(3), new Dice(3), new Dice(3)))),
    TRIPPE_FOUR(new ArrayList<>(Arrays.asList(new Dice(4), new Dice(4), new Dice(4)))),
    TRIPPLE_FIVE(new ArrayList<>(Arrays.asList(new Dice(5), new Dice(5), new Dice(5)))),
    TRIPPLE_SIX(new ArrayList<>(Arrays.asList(new Dice(6), new Dice(6), new Dice(6)))),
    STRAIGHT(new ArrayList<>(Arrays.asList(new Dice(1), new Dice(2), new Dice(3), new Dice(4), new Dice(5), new Dice(6)))),
    SINGLE_ONE(new ArrayList<>(Arrays.asList(new Dice(1)))),
    SINGLE_FIVE(new ArrayList<>(Arrays.asList(new Dice(5))));

    private final List<Dice> requiredPattern;
    DicePattern(List<Dice> requiredPattern){
        this.requiredPattern = requiredPattern;
    }

    public List<Dice> getRequiredPattern(){
        return new ArrayList<>(requiredPattern);
    }

    public String toString(){
        return this.name();
    }
}
