package Materials.Card;

import Game.GameState;
import Game.GameTurn;
import Materials.Combinations.DicePattern;
import java.util.List;
/**
 * author: Daniel Lutziger
 */
public class FireworksCard extends Card implements CardRule {

    protected FireworksCard(CardType cardType){
        assert cardType != null;
        super.cardType = cardType;
    }

    @Override
    public int applyCardEffect(int current) {
        return current;
    }


    @Override
    public GameTurn executeTurn(GameTurn gameTurn) {
        System.out.println("Run turn");
        do {
            gameTurn.getP().roll(gameTurn.getDice());
            List<DicePattern> patterns = evaluateRoll(gameTurn.getDice());
            if (patterns.size() > 0) {
                // PATTERN FOUND (VALID)
                gameTurn = checkForTuttoOrEnd(gameTurn, patterns);
            } else {
                // NULL
                gameTurn.setState(GameState.END);
                return gameTurn;
            }
        }while(gameTurn.getState().equals(GameState.REROLL));
        return gameTurn;
    }
}
