package Game;

public class Game {
    // current state of the coordinate
    private GameState currentState;

    public Game(){
        setState(NormalState.state());
    }

    public void setState(GameState state)
    {
        currentState = state;
    }
}
