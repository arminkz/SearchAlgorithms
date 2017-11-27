package algorithm;

import algorithm.Action;
import algorithm.State;

import java.util.ArrayList;

public interface Problem {

    public State initialState();

    public ArrayList<Action> actions(State s);

    public State result(State s,Action a);

    public boolean goalTest(State s);

    public int utility(Action a);

}
