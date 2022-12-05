package Game;

import Materials.Card.Card;
import Materials.Card.CardType;
import Materials.Card.Deck;
import Materials.Card.PlusMinusCard;
import Materials.Dice.Dice;
import Player.Player;

import java.util.*;
/**
 * author: daniel lutziger
 * - marked point cooperation with orestis bollano
 */
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
        players = initializeAllPlayers();
        // sort players
        dice = new ArrayList<>(Arrays.asList(new Dice(), new Dice(), new Dice(), new Dice(), new Dice(), new Dice()));

        getAlphabetical();
        for (Player x : players){
            System.out.println(x.getName());
        }
    }
    public void playGame(){
        while(gameState.equals(GameState.RUNNING)){
            playerloop: for (Player p : players) {
                GameTurn gameTurn = new GameTurn(p, dice);
                GameState turnState = GameState.FIRSTROLL;

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
                            gameTurn.setState(GameState.REROLL_TUTTO);
                        } else {
                            //set state to end
                            gameTurn.setState(GameState.END_TUTTO);
                        }
                    }
                    if (gameTurn.getState().equals(GameState.END) || gameTurn.getState().equals(GameState.END_TUTTO)){
                        for(int x = 0; x < gameTurn.getDrawnCards().size(); x++){
                            if (gameTurn.getDrawnCards().get(x).getState().equals(GameState.END_TUTTO) || gameTurn.getDrawnCards().get(x).getState().equals(GameState.REROLL_TUTTO)){
                                for (Integer i : gameTurn.getDrawnCards().get(x).getScoredPoints()){
                                    p.addTemporary(i);
                                }
                                if (gameTurn.getDrawnCards().get(x).getCard().getCardType() == CardType.PLUSMINUS) {
                                    PlusMinusCard q = (PlusMinusCard) gameTurn.getDrawnCards().get(x).getCard();
                                    List<Player> subtractors = q.applyCardEffect(p.getTemporary(), getGameLeader(), p);
                                    for (Player r : players) {
                                        for (Player s : subtractors) {
                                            if (s.getScore() == r.getScore() && s.getName() == r.getName()){
                                                r.setScore(s.getScore());
                                            }
                                        }
                                    }
                                } else {
                                    p.addScore(gameTurn.getDrawnCards().get(x).getCard().applyCardEffect(p.getTemporary()));
                                }
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
                    }  else if (gameTurn.getState().equals(GameState.NULL)){ //NULL
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
    public List<Player> initializeAllPlayers(){
        System.out.println("How many players should participate?");
        boolean invalidInput = true;
        int numOfPlayers = 0;
        List<Player> gamers = new ArrayList<Player>();
        while(invalidInput){
            try{
                numOfPlayers = getScoreInput();
                assert numOfPlayers >= 2 && numOfPlayers <= 4;
                invalidInput = false;
            } catch (Exception e){
                System.out.println("Enter a valid input pls");
            } catch (AssertionError ae){
                System.out.println("Enter a valid input pls");
            }
        }
        for(int x = 0; x < numOfPlayers; x++){
            gamers.add(initializeSinglePlayer());
        }
        return gamers;
    }

    public Player initializeSinglePlayer(){
        System.out.println("Enter the player name");
        String name = getInput();
        return new Player(name);
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
                goalScore = getScoreInput();
                invalidInput = false;
            } catch (Exception e) {
                System.out.println("Your entries were wrong, please enter again in a valid format!");
            }
        }
    }

    /**
     * author: daniel lutziger in pair programming cooperation with orestis bollano
     */
    public boolean playerTurnDecision(){
        System.out.println("Do you want to start your roll (R) or see the scoreboard (S)?");
        boolean invalidInput = true;
        while(invalidInput){
            try {
                String nL = getInput();
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

    public String getInput() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }
    public int getScoreInput(){
        Scanner sc = new Scanner(System.in);
        return sc.nextInt();
    }

    public List<Player> getPlayers(){
        return players;
    }
    public void setPlayers(List<Player> players){
        this.players = players;
    }
    public int getGoalsScore(){
        return goalScore;
    }
    public GameState getGameState(){
        return this.gameState;
    }
    public void setGameState(GameState gs){
        this.gameState = gs;
    }

    public List<Dice> getDice() {
        return dice;
    }

    public void setDice(List<Dice> dice) {
        this.dice = dice;
    }

    public void setDeck(Deck deck) {
        this.deck = deck;
    }
}
