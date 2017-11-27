package algorithm;

import java.util.ArrayList;

public interface BidirectionalProblem extends Problem {

    //Functions for Bi-directional Search
    State goalState();

    ArrayList<Action> actionsBd(State s);

    ArrayList<State> resultBd(State s,Action a);


}
