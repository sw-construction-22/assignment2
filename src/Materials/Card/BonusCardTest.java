package Materials.Card;

import Game.NormalState;
import Game.NullState;
import Game.TuttoState;
import Materials.Combinations.Combination;
import Materials.Dice.Dice;
import Player.Player;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BonusCardTest {

    Player p = new Player();
    List<Dice> diceSet = new ArrayList<>(Arrays.asList( new Dice(),  new Dice(),  new Dice(), new Dice(), new Dice(), new Dice()));

    @Test
    public void bonusCard200Test(){
        Card card = new BonusCard(CardType.BONUSx200);
        card.executeRule(p, diceSet);
        //assertEquals(c.evaluateState(NO_TRIPPLETS_BUT_ONES.size()), NormalState.state());
    }

}
