package Game;

import Materials.Card.Card;
import Materials.Card.Deck;
import Materials.Dice.Dice;
import Player.Player;

import java.util.*;

public class GameOperator {
    private List<Player> players;
    private Deck deck;
    private List<Dice> dice;
    private int goalScore;
    private GameState gameState = GameState.RUNNING;
    public GameOperator() {
        deck = new Deck();
        deck.isEmpty();
        setGoalScore();
        // Input how many players
        players = new ArrayList<>(Arrays.asList(new Player("Hansel"), new Player("Gretel"), new Player("Arnold")));
        // sort players
        dice = new ArrayList<>(Arrays.asList(new Dice(), new Dice(), new Dice(), new Dice(), new Dice(), new Dice()));

        getAlphabetical();
        for (Player x : players){
            System.out.println(x.getName());
        }
        while(gameState.equals(GameState.RUNNING)){
            playerloop: for (Player p : players) {
                GameTurn gameTurn = new GameTurn(p, dice);
                GameState turnState = GameState.FIRSTROLL;

                /**
                 * Check if the state is not over and execute the stuff ! TODO
                 */
                while(!turnState.equals(GameState.CLOVERLEAF) &&
                        !turnState.equals(GameState.WIN) &&
                        !turnState.equals(GameState.END) &&
                        !turnState.equals(GameState.NULL)){
                    System.out.println("-------------- START TURN ------------------");
                    System.out.println("Player's move: " + p.getName());
                    System.out.println("Player's score: " + p.getScore());
                    while(playerTurnDecision()){ //while the user wants to see the scoreboard
                        for(Player x : getScoreboard()){
                            System.out.println(x.getName() + " : " + x.getScore());
                        }
                    }
                    if(deck.isEmpty()){
                        deck.shuffle();
                    }
                    Card c = p.draw(deck);
                    System.out.println("Drawn Card: " + c.getCardType());
                    gameTurn.addCard(c);
                    gameTurn = c.executeTurn(gameTurn);

                    if(gameTurn.getState().equals(GameState.TUTTO)){
                        turnState = GameState.TUTTO;
                        gameTurn.tuttoScored();
                        //ask if end turn or not
                        if (p.reroll()) {
                            //set all 6 dice again
                            gameTurn.setDice(dice);
                        } else {
                            //set state to end
                            gameTurn.setState(GameState.END_TUTTO);
                        }
                    }
                    if (gameTurn.getState().equals(GameState.END) || gameTurn.getState().equals(GameState.END_TUTTO)){
                        for(int x = 0; x < gameTurn.getDrawnCards().size(); x++){
                            if (gameTurn.getDrawnCards().get(x).getState().equals(GameState.END_TUTTO)){
                                for (Integer i : gameTurn.getDrawnCards().get(x).getScoredPoints()){
                                    p.addTemporary(i);
                                }
                                p.addScore(gameTurn.getDrawnCards().get(x).getCard().applyCardEffect(p.getTemporary()));
                                p.resetTempScore();
                            }else {
                                for (Integer i : gameTurn.getDrawnCards().get(x).getScoredPoints()){
                                    p.addScore(i);
                                }
                            }
                        }
                        turnState = GameState.END;
                    } else if (gameTurn.getState().equals(GameState.CLOVERLEAF)){
                        turnState = GameState.CLOVERLEAF;
                        gameState = GameState.CLOVERLEAF;
                        System.out.println("THE WINNER OF THE GAME IS : " + p.getName());
                        break playerloop;
                    }  else { //NULL
                        turnState = GameState.NULL;
                    }

                }
                System.out.println("------------------------------------------------------");
                System.out.println("Your current score is the following: " + p.getScore());
                System.out.println("-------------- END TURN ------------------");

                if(getGameLeader().get(0).getScore() > goalScore &&
                        p.getName() == players.get(players.size()-1).getName() &&
                        p.getScore() == players.get(players.size()-1).getScore()){
                    gameState = GameState.WIN;
                    turnState = GameState.WIN;
                    break playerloop;
                }
            }
        }
        System.out.println("GAME OVER! The required points of " + goalScore + " is reached");
        if(getGameLeader().get(0).getScore() > goalScore){
            for(Player x : getScoreboard()){
                System.out.println(x.getName() + " : " + x.getScore());
            }
        }
    }

    public List<Player> getAlphabetical(){
        Comparator<Player> byName = Comparator.comparing(Player::getName);
        Collections.sort(players, byName);
        return players;
    }

    public List<Player> getScoreboard(){
        Comparator<Player> byScore = Comparator.comparing(Player::getScore);
        List<Player> copy = new ArrayList<>(players);
        Collections.sort(copy, byScore);Collections.reverse(copy);
        return copy;
    }
    public List<Player> getGameLeader(){
        Comparator<Player> byScore = Comparator.comparing(Player::getScore);
        List<Player> copy = new ArrayList<>(players);
        Collections.sort(copy, byScore);
        Collections.reverse(copy); //highest player on top
        List<Player> leaders = new ArrayList<>();
        for (Player p : copy){
            if (p.getScore() == copy.get(0).getScore()){
                leaders.add(p);
            }
        }
        return leaders;
    }

    public void setGoalScore(){
        System.out.println("What should be the goal score of the game until someone achieves a victory?");
        boolean invalidInput = true;
        while(invalidInput){
            try {
                Scanner scanner = new Scanner(System.in);
                goalScore = scanner.nextInt();
                invalidInput = false;
            } catch (Exception e) {
                System.out.println("Your entries were wrong, please enter again in a valid format!");
            }
        }
    }

    public boolean playerTurnDecision(){
        System.out.println("Do you want to start your roll (R) or see the scoreboard (S)?");
        boolean invalidInput = true;
        while(invalidInput){
            try {
                Scanner scanner = new Scanner(System.in);
                String nL = scanner.nextLine();
                assert nL.length() == 1;
                char decision = nL.charAt(0);
                if (decision == 'R' || decision == 'r'){
                    return false;
                } else if (decision == 'S' || decision == 's'){
                    return true;
                } else{
                    System.out.println("Sorry, the expected input is either R (run) || S (scoreboard)");
                }
            } catch (Exception e) {
                System.out.println("Your entries were wrong, please enter again in a valid format!");
            } catch (AssertionError assertionError) {
                System.out.println("Your input is supposed to be sized to 1, please enter your input again");
            }
        }
        return false;
    }
}
