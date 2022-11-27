package Materials.Card;

import Materials.Dice.Dice;
import Player.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main_T {
    public static void main(String[] args){
        Player p = new Player();
        Deck d = new Deck();
        List<Dice> dice = new ArrayList<>(Arrays.asList( new Dice(),  new Dice(),  new Dice(), new Dice(), new Dice(), new Dice()));
        for(int x = 0; x < 10; x++){
            p.draw(d).executeRule(p, dice);
            System.out.println(p.getScore());
        }
    }
}
