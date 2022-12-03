package Player;

import Materials.Card.Card;
import Materials.Card.Deck;
import Materials.Combinations.Combination;
import Materials.Combinations.DicePattern;
import Materials.Dice.Dice;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * author: Daniel Lutziger
 */
public class Player {//implements Comparable{
    private String name;
    private int temporary = 0;
    private int score = 0;

    public Player(String name){
        this.name = name;
    }
    /**
     * Roll dice
     * @return rolled dice elements
     */
    public List<Dice> roll(List<Dice> dice)
    {
        System.out.println("Dice roll result: ");
        for (Dice d : dice) {
            d.roll();
            System.out.print(d.getValue() + " ");
        }
        System.out.print("\n");
        return dice;
    }

    /**
     * Draw card
     */
    public Card draw(Deck deck) {
        assert !deck.isEmpty();
        return deck.draw();
    }

    public void addTemporary(int temporary){
        this.temporary += temporary;
    }

    public void addScore(){
        score += temporary;
        System.out.println("Your current score is the following: " + score);
        System.out.println("------------------------------------------------------");
    }

    public int getScore(){
        return score;
    }

    public void turnStart(){
        temporary = 0;
    }

    public boolean reroll(){
        boolean invalidInput = true;
        System.out.println("Do you want to reroll (R) or end the turn (E)?");
        while(invalidInput){
            try {
                Scanner scanner = new Scanner(System.in);
                String nL = scanner.nextLine();
                assert nL.length() == 1;
                char decision = nL.charAt(0);
                if (decision == 'R' || decision == 'r'){
                    return true;
                } else if (decision == 'E' || decision == 'e'){
                    return false;
                } else{
                    System.out.println("Sorry, the expected input is either R (run) || E (end)");
                }
            } catch (Exception e) {
                System.out.println("Your entries were wrong, please enter again in a valid format!");
            } catch (AssertionError assertionError) {
                System.out.println("Your input is supposed to be sized to 1, please enter your input again");
            }
        }
        return false;
    }

    public List<Dice> holdBack(List<DicePattern> patterns, List<Dice> dice){
        assert !dice.isEmpty();
        List<Dice> heldBack = new ArrayList<>();
        boolean invalidInput = true;
        System.out.println("Which dice pattern do you want to hold back? Enter the index, separated by comma please:");
        while(invalidInput){
            List<Dice> resultList = new ArrayList<>();
            try {
                Scanner scanner = new Scanner(System.in);
                String[] inputs = scanner.nextLine().split(",");
                assert inputs.length > 0;
                for(int x = 0; x < inputs.length; x++){
                    resultList.addAll(patterns.get(Integer.parseInt(inputs[x])-1).getRequiredPattern()); //input = 1, index is 0
                }
                invalidInput = false;
            } catch (Exception e) {
                System.out.println("Your entries were wrong, please enter all again in a valid format!");
            }
            heldBack = new ArrayList<>(dice);
            for(Dice current : resultList){
                heldBack.remove(current);
            }
        }
        return heldBack;
    }

    /**
     * probably return the game state victory or something
     * REGULAR FLOW FOR BONUS, DOUBLE CARDS
     *
     * IRREGULAR FLOW HAS FIREWORKS CARD
     * IRREGULAR FLOW HAS PLUS MINUS CARD
     * IRREGULAR FLOW HAS STRAIGHT CARD
     *
     */
    public void turn(Card c, List<Dice> dice, Combination combination){
        int tuttoScore = 0;
        temporary = 0;
        while (tuttoScore < 1) {
            if (combination.evaluateRoll(dice, c.getCardType()).size() > 0) {
                // PATTERN FOUND (VALID)
                if (combination.dicePatternSize() == dice.size()) {
                    System.out.println("TUTTO SCORED");
                    tuttoScore += 1;
                    addTemporary(c.getCardType().getPoints());
                    c.applyCardEffect(temporary);
                    // TUTTO SCORED
                    // GIVE BACK ALL DICE
                } else {
                    // HOLD BACK DICE
                    System.out.println("Current max points from roll: " + combination.dicePatternMaxPoints());
                    int index = 1;
                    for(DicePattern pattern : combination.getFoundPatterns()){
                        System.out.println(index++ + " " + pattern.toString());
                    }
                    if (reroll()){
                        dice = holdBack(combination.getFoundPatterns(), dice);
                    } else {
                        addTemporary(combination.dicePatternMaxPoints());
                        addScore();
                        break;
                    }
                }
            } else {
                // NULL
                System.out.println("NULL");
                break;
            }
        }
    }

    public String getName(){return this.name;}


    /**
     *
     * @param o the object to be compared.
     * @return the order of the object
     *//*
    @Override
    public int compareTo(Object o) {
        return this.name.compareTo(((Player) o).getName());
    }

    /**
     *
     * @param o
     * @return if the object is equal
     *//*
    @Override
    public boolean equals(Object o)
    {
        if(!(o instanceof Player))
        {
            return false;
        }
        else
        {
            Player p=(Player)o;
            if(this.score== p.getScore())
            {
                return true;
            }
        }
        return false;
    }*/

}
