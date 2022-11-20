package Game;

public class NormalState implements GameState{
    private static NormalState state;
    public static NormalState state() {
        if(state == null){
            state = new NormalState();
        }
        return state;
    }
    @Override
    public void updateState(Game context)
    {
        context.setState(NormalState.state());
    }
}
