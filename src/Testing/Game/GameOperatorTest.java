package Testing.Game;

import Game.GameOperator;
import Game.GameState;
import Game.GameTurn;
import Materials.Card.BonusCard;
import Materials.Card.CardType;
import Materials.Card.Deck;
import Materials.Card.FireworksCard;
import Materials.Dice.Dice;
import Player.Player;
import Testing.Card.DoubleCardTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GameOperatorTest {
    GameOperator go;
    @BeforeEach
    public void setup() {
        setupTest();
    }

    private void setupTest() {
        go = mock(GameOperator.class);
    }

    @Test
    public void testGetInput(){
        Mockito.when(go.getInput()).thenReturn("hello");
        assertEquals("hello", go.getInput());
    }
    @Test
    public void testNumberInput(){
        Mockito.when(go.getScoreInput()).thenReturn(3);
        assertEquals(3, go.getScoreInput());
    }
    @Test
    public void testInitializeSinglePlayer(){
        GameOperator mocked = mock(GameOperator.class);
        when(mocked.getInput()).thenReturn("Ready Player 1");
        when(mocked.initializeSinglePlayer()).thenCallRealMethod();
        assertEquals(new Player("Ready Player 1").getName(), mocked.initializeSinglePlayer().getName());
        assertEquals(new Player("Ready Player 1").getScore(), mocked.initializeSinglePlayer().getScore());
        assertEquals(new Player("Ready Player 1").getTemporary(), mocked.initializeSinglePlayer().getTemporary());
    }
    @Test
    public void testInitializeAllPlayers(){
        GameOperator mocked = mock(GameOperator.class);
        when(mocked.getInput()).thenReturn("Ready Player 1");
        when(mocked.getScoreInput()).thenReturn(4);
        when(mocked.initializeSinglePlayer()).thenCallRealMethod();
        when(mocked.initializeAllPlayers()).thenCallRealMethod();
        assertEquals(4, mocked.initializeAllPlayers().size());
        assertEquals(new Player("Ready Player 1").getScore(), mocked.initializeAllPlayers().get(0).getScore());
    }
    @Test
    public void testGetAlphabetical(){
        GameOperator mocked = mock(GameOperator.class);
        String[] names = {"Gerti", "Trudi", "Gunther", "Beni"};
        when(mocked.initializeSinglePlayer()).thenCallRealMethod();
        when(mocked.getPlayers()).thenCallRealMethod();
        when(mocked.getAlphabetical()).thenCallRealMethod();
        List<Player> players = new ArrayList();
        for(String name : names){
            when(mocked.getInput()).thenReturn(name);
            players.add(mocked.initializeSinglePlayer());
        }
        when(mocked.initializeAllPlayers()).thenReturn(players);
        Mockito.doCallRealMethod().when(mocked).setPlayers(players);
        mocked.setPlayers(players);
        ArrayList<String> e = new ArrayList();
        for(Player p : mocked.getAlphabetical()){
            e.add(p.getName());
        }
        assertEquals(new ArrayList(Arrays.asList("Beni", "Gerti", "Gunther", "Trudi")), e);
    }
    @Test
    public void testGetScoreboard(){
        GameOperator mocked = mock(GameOperator.class);
        String[] names = {"Gerti", "Trudi", "Gunther", "Beni"};
        when(mocked.initializeSinglePlayer()).thenCallRealMethod();
        when(mocked.getPlayers()).thenCallRealMethod();
        when(mocked.getScoreboard()).thenCallRealMethod();
        List<Player> players = new ArrayList();
        for(String name : names){
            when(mocked.getInput()).thenReturn(name);
            players.add(mocked.initializeSinglePlayer());
        }
        for(int x = 0; x < players.size(); x++){
            players.get(x).setScore(x*1000);
        }
        when(mocked.initializeAllPlayers()).thenReturn(players);
        Mockito.doCallRealMethod().when(mocked).setPlayers(players);
        mocked.setPlayers(players);
        ArrayList<Integer> e = new ArrayList();
        for(Player p : mocked.getScoreboard()){
            e.add(p.getScore());
        }
        assertEquals(new ArrayList(Arrays.asList(3000,2000,1000,0)), e);
    }
    @Test
    public void testGetGameLeader(){
        GameOperator mocked = mock(GameOperator.class);
        String[] names = {"Gerti", "Trudi", "Gunther", "Beni"};
        when(mocked.initializeSinglePlayer()).thenCallRealMethod();
        when(mocked.getPlayers()).thenCallRealMethod();
        when(mocked.getGameLeader()).thenCallRealMethod();
        List<Player> players = new ArrayList();
        for(String name : names){
            when(mocked.getInput()).thenReturn(name);
            players.add(mocked.initializeSinglePlayer());
        }
        for(int x = 0; x < players.size(); x++){
            players.get(x).setScore(x*1000);
        }
        when(mocked.initializeAllPlayers()).thenReturn(players);
        Mockito.doCallRealMethod().when(mocked).setPlayers(players);
        mocked.setPlayers(players);
        ArrayList<String> e = new ArrayList();
        for(Player p : mocked.getGameLeader()){
            e.add(p.getName());
        }
        assertEquals(new ArrayList(Arrays.asList("Beni")), e);
    }
    @Test
    public void testSetGoalScore(){
        GameOperator mocked = mock(GameOperator.class);
        when(mocked.getGoalsScore()).thenCallRealMethod();
        Mockito.doCallRealMethod().when(mocked).setGoalScore();
        when(mocked.getScoreInput()).thenReturn(5000);
        mocked.setGoalScore();
        assertEquals(5000, mocked.getGoalsScore());
    }
    @Test
    public void testPlayerTurnDecision(){
        GameOperator mocked = mock(GameOperator.class);
        when(mocked.getInput()).thenReturn("r");
        when(mocked.playerTurnDecision()).thenCallRealMethod();
        assertFalse(mocked.playerTurnDecision());

        when(mocked.getInput()).thenReturn("R");
        assertFalse(mocked.playerTurnDecision());
        when(mocked.getInput()).thenReturn("s");
        assertTrue(mocked.playerTurnDecision());
        when(mocked.getInput()).thenReturn("S");
        assertTrue(mocked.playerTurnDecision());
    }
    @Test
    public void testGameOperatorPlayTurn(){
        GameOperator mocked = mock(GameOperator.class);
        Player playerMock = mock(Player.class);
        //when(mocked.getScoreInput()).thenReturn(4);
        //when(mocked.initializeAllPlayers()).thenReturn(new ArrayList(Arrays.asList(new Player("Beni"), new Player("Gerti"), new Player("Gunther"), new Player("Trudi"))));
        Mockito.doCallRealMethod().when(mocked).playGame();
        Mockito.doCallRealMethod().when(mocked).setGameState(GameState.RUNNING);
        mocked.setGameState(GameState.RUNNING);

        String[] names = {"Gerti", "Trudi", "Gunther", "Beni"};
        when(mocked.initializeSinglePlayer()).thenCallRealMethod();
        when(mocked.getPlayers()).thenCallRealMethod();
        when(mocked.getAlphabetical()).thenCallRealMethod();
        when(mocked.getGameLeader()).thenCallRealMethod();
        when(mocked.getScoreboard()).thenCallRealMethod();
        when(mocked.getGameState()).thenCallRealMethod();
        List<Player> players = new ArrayList();
        for(String name : names){
            when(mocked.getInput()).thenReturn(name);
            players.add(mocked.initializeSinglePlayer());
        }
        when(mocked.initializeAllPlayers()).thenReturn(players);
        Mockito.doCallRealMethod().when(mocked).setPlayers(players);
        mocked.setPlayers(players);

        Mockito.doCallRealMethod().when(mocked).setDice(new ArrayList<>(Arrays.asList(new Dice(), new Dice(), new Dice(), new Dice(), new Dice(), new Dice())));
        mocked.setDice(new ArrayList<>(Arrays.asList(new Dice(), new Dice(), new Dice(), new Dice(), new Dice(), new Dice())));
        Deck d = new Deck(CardType.FIREWORKS);
        Mockito.doCallRealMethod().when(mocked).setDeck(d);
        mocked.setDeck(d);
        //Mockito.doCallRealMethod().when(mocked).setPlayers(Arrays.asList(new Player("Beni"), new Player("Gerti"), new Player("Gunther"), new Player("Trudi")));
        //mocked.setPlayers(new ArrayList(Arrays.asList(new Player("Beni"), new Player("Gerti"), new Player("Gunther"), new Player("Trudi"))));
        mocked.playGame();
        assertEquals(GameState.WIN, mocked.getGameState());
    }
    @Test
    public void testGameOperatorPlayTurnPLUSMINUS(){
        Player p = new Player("Test");
        List<Dice> d =new ArrayList<>(Arrays.asList(new Dice(), new Dice(), new Dice(), new Dice(), new Dice(), new Dice()));
        GameTurn gt = new GameTurn(p,d);
        Deck x = new Deck(CardType.BONUSx200);
        gt.addCard(x.draw());
        gt.tuttoScored();
        assertEquals(GameState.TUTTO, gt.getState());
    }
}
