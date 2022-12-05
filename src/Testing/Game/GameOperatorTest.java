package Testing.Game;

import Game.GameOperator;
import Materials.Combinations.DicePattern;
import Materials.Dice.Dice;
import Player.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
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
}
