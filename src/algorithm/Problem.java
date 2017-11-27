package algorithm;

import java.util.ArrayList;

public interface Problem {

    State initialState();

    ArrayList<Action> actions(State s);

    ArrayList<State> result(State s,Action a);

    boolean goalTest(State s);

    int utility(Action a);

    int heuristic(State s);

}
