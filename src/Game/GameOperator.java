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
        initializeGame();
    }

    /**
     * initialize the game
     * - create a new deck
     * - set the goal score to end the game
     * - initialize all the players
     * - sort the players in an alphabetical order
     * - print the order for the game to start
     */
    public void initializeGame(){
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

    /**
     * the actual game manager
     * outer loop: run the game
     *  player loop: while the game is running loop through all players
     *      inner loop: run the turn for a user until he ends the turn or scores a null
     *      player loop: get broken if a cloverleaf card leads to victory
     *
     */
    public void playGame(){
        // outer loop
        while(gameState.equals(GameState.RUNNING)){
            // playerloop : breaks if a user scores a win with a cloverleaf card
            playerloop: for (Player p : players) {
                //initialize new game turn, where card and points are added for each turn
                GameTurn gameTurn = new GameTurn(p, dice);
                GameState turnState = GameState.FIRSTROLL;

                //while a user wants / has to roll the dice
                while(!turnState.equals(GameState.CLOVERLEAF) &&
                        !turnState.equals(GameState.WIN) &&
                        !turnState.equals(GameState.END) &&
                        !turnState.equals(GameState.NULL)){

                    //print that the turn starts
                    System.out.println("-------------- START TURN ------------------");
                    System.out.println("Player's move: " + p.getName());
                    System.out.println("Player's score: " + p.getScore());

                    //let the user decide whether he wants to see the scoreboard or roll
                    while(playerTurnDecision()){
                        for(Player x : getScoreboard()){
                            System.out.println(x.getName() + " : " + x.getScore());
                        }
                    }

                    //check if the deck is empty and reshuffle the cards
                    if(deck.isEmpty()){
                        deck.shuffle();
                    }

                    //draw a card and add it to the current turn
                    Card c = p.draw(deck);
                    System.out.println("Drawn Card: " + c.getCardType());
                    gameTurn.addCard(c);

                    //execute the game flow of the card
                    gameTurn = c.executeTurn(gameTurn);

                    //evaluate the game flow of the turn
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

                    //after the tutto evaluation check if the user wants to end his turn, scored an overleaf, or scored a null
                    if (gameTurn.getState().equals(GameState.END) || gameTurn.getState().equals(GameState.END_TUTTO)){
                        //if the user wants to end the turn iterate through all the turn objects as the cards are stored in there
                        for(int x = 0; x < gameTurn.getDrawnCards().size(); x++){
                            //by iterating through the cards, the points for each card can be read and if the user scored a tutto with that certain card the effect can be applied
                            if (gameTurn.getDrawnCards().get(x).getState().equals(GameState.END_TUTTO) || gameTurn.getDrawnCards().get(x).getState().equals(GameState.REROLL_TUTTO)){
                                for (Integer i : gameTurn.getDrawnCards().get(x).getScoredPoints()){
                                    p.addTemporary(i);
                                }
                                //in case that the card the user had was a plus minus card the game leader(s) have the be deducted some points
                                if (gameTurn.getDrawnCards().get(x).getCard().getCardType() == CardType.PLUSMINUS) {
                                    PlusMinusCard q = (PlusMinusCard) gameTurn.getDrawnCards().get(x).getCard();
                                    //the method returns the users where the points have to be deducted
                                    List<Player> subtractors = q.applyCardEffect(p.getTemporary(), getGameLeader(), p);
                                    //iterate through all users and the deductors and deduct the points from the leaders
                                    for (Player r : players) {
                                        for (Player s : subtractors) {
                                            if (s.getScore() == r.getScore() && s.getName() == r.getName()){
                                                r.setScore(s.getScore());
                                            }
                                        }
                                    }
                                } else {
                                    //apply the effect of a card with all the scored points of the user with that specific card
                                    p.addScore(gameTurn.getDrawnCards().get(x).getCard().applyCardEffect(p.getTemporary()));
                                }
                                p.resetTempScore();
                            }else {
                                // in case a tutto was not scored with the card just add the points to the users score
                                for (Integer i : gameTurn.getDrawnCards().get(x).getScoredPoints()){
                                    p.addScore(i);
                                }
                            }
                        }
                        turnState = GameState.END;
                    } else if (gameTurn.getState().equals(GameState.CLOVERLEAF)){
                        //the method gets executed if the user wins by a cloverleaf card
                        //that meaans that the turn immediately ends
                        turnState = GameState.CLOVERLEAF;
                        gameState = GameState.CLOVERLEAF;
                        System.out.println("THE WINNER OF THE GAME IS : " + p.getName());
                        break playerloop;
                    }  else if (gameTurn.getState().equals(GameState.NULL)){
                        //NULL
                        turnState = GameState.NULL;
                    }

                }
                // print the end of the turn
                System.out.println("------------------------------------------------------");
                System.out.println("Your current score is the following: " + p.getScore());
                System.out.println("-------------- END TURN ------------------");

                // evaluate whether a user won by the amounts of points
                // if so, finish the turn for all users
                if(getGameLeader().get(0).getScore() > goalScore &&
                        p.getName() == players.get(players.size()-1).getName() &&
                        p.getScore() == players.get(players.size()-1).getScore()){
                    gameState = GameState.WIN;
                    turnState = GameState.WIN;
                    break playerloop;
                }
            }
        }
        // the game was finished as a user won due to points
        System.out.println("GAME OVER! The required points of " + goalScore + " is reached");
        if(getGameLeader().get(0).getScore() > goalScore){
            for(Player x : getScoreboard()){
                System.out.println(x.getName() + " : " + x.getScore());
            }
        }
    }

    /**
     * @return list of all players which were created
     */
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

    /**
     * @return abstracted single player creation
     */
    public Player initializeSinglePlayer(){
        System.out.println("Enter the player name");
        String name = getInput();
        return new Player(name);
    }

    /**
     * @return alphabetically sorted users
     */
    public List<Player> getAlphabetical(){
        Comparator<Player> byName = Comparator.comparing(Player::getName);
        Collections.sort(players, byName);
        return players;
    }

    /**
     * @return copy of the users, ordered by score
     */
    public List<Player> getScoreboard(){
        Comparator<Player> byScore = Comparator.comparing(Player::getScore);
        List<Player> copy = new ArrayList<>(players);
        Collections.sort(copy, byScore);Collections.reverse(copy);
        return copy;
    }
    /**
     * @return copy of the users, with the highest score
     */
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

    /**
     * setting the goal score which has to be achieved in the game
     */
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
     * allowing the player decision
     * @return either true or flase depending on the action of the user (R) | (S)
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

    /**
     * abstraction of the user input
     * @return the userinput for strings
     */
    public String getInput() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }
    /**
     * abstraction of the user input
     * @return the userinput for integers
     */
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
