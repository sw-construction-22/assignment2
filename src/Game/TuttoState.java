package Game;

public class TuttoState implements GameState{
    private static TuttoState state;
    public static TuttoState state() {
        if(state == null){
            state = new TuttoState();
        }
        return state;
    }
    @Override
    public void updateState(Game context)
    {
        context.setState(TuttoState.state());
    }
}
