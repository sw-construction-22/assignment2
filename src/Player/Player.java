package Player;

import Materials.Card.Card;
import Materials.Card.Deck;
import Materials.Combinations.DicePattern;
import Materials.Dice.Dice;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Player {
    private String name;
    private int score = 0;
    private int temporary = 0;

    public Player(String name){
        this.name = name;
    }
    public Player(Player player){
        this.name = player.getName();
        this.score = player.getScore();
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

    public void addScore(int scoreToAdd){
        score += scoreToAdd;
    }

    public int getScore(){
        return score;
    }
    public void setScore(int score){
        score = score;
    }
    public int getTemporary(){return temporary;}
    public void addTemporary(int temp){temporary += temp;}
    public void resetTempScore(){temporary = 0;}
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

            heldBack = new ArrayList<>(resultList);
        }
        return heldBack;
    }

    public String getName(){return this.name;}
}
