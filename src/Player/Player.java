package Player;

import Game.Game;
import Materials.Dummy.CardEffect;
import Materials.Dummy.Combinations;
import Materials.Dummy.Deck;
import Materials.Dummy.Dice;

public class Player {
    String name;
    int points;

    public void turn(){
        //choose to display points or roll dices
        Deck.drawCard();
        CardEffect.evaluateCard();
        Dice.rollDices();
        Combinations.evaluateRoll();
        GameTurn.combinationPoints();
        GameTurn.applyEffect();
        GameTurn.userDecisionContinue();
        GameTurn.userDecisionDices();
        //jump to tutto or reroll
    }
}
