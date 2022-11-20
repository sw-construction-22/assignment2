package Game;

public class NullState implements GameState{
    private static NullState state;
    public static NullState state() {
        if(state == null){
            state = new NullState();
        }
        return state;
    }
    @Override
    public void updateState(Game context)
    {
        context.setState(NullState.state());
    }
}
