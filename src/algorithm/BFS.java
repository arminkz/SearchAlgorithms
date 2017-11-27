package algorithm;
//Breadth First Search Algorithm
//Complexity is O(b^d)
//Created By Armin

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class BFS {

    public static ArrayList<Action> search(Problem p){

        Queue<Expandable> BFSQueue = new LinkedList<>();
        ArrayList<State> closed = new ArrayList<>();

        Expandable initE = new Expandable();
        initE.state = p.initialState();
        initE.actionSequence = new ArrayList<>();

        BFSQueue.add(initE);
        closed.add(initE.state);

        while(!BFSQueue.isEmpty()){
            Expandable s = BFSQueue.remove();
            if(p.goalTest(s.state)){
                //Goal Reached
                System.out.println("[BFS] Goal Reached !");
                return s.actionSequence;
            }else{
                //Close Current State
                closed.add(s.state);
                //Expand Childs
                for(Action a : p.actions(s.state)){
                    for(State targetState : p.result(s.state,a)) { //undeterministic states (more than 1)
                        boolean mustAdd = true;
                        for (State closedState : closed) {
                            if (closedState.isEquals(targetState)) {
                                mustAdd = false;
                                break;
                            }
                        }
                        for (Expandable openState : BFSQueue) {
                            if (openState.state.isEquals(targetState)) {
                                mustAdd = false;
                                break;
                            }
                        }
                        if (mustAdd) {
                            Expandable SAS = new Expandable();
                            SAS.state = targetState;
                            //Clone Parent Action Sequence
                            ArrayList<Action> asClone = new ArrayList<>();
                            for (Action sa : s.actionSequence) {
                                asClone.add(sa);
                            }
                            asClone.add(a);
                            SAS.actionSequence = asClone;
                            BFSQueue.add(SAS);
                        }
                    }
                }
            }
        }
        //There is no answer to algorithm.Problem
        System.err.println("[BFS] No Answer !");
        return null;
    }

}