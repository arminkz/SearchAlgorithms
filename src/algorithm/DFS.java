package algorithm;
//Depth First Search Algorithm
//Complexity is O(b^d)
//Created By Armin

import java.util.ArrayList;
import java.util.Stack;

public class DFS {

    //Search without Depth Limit
    public static ArrayList<Action> search(Problem p){
        return search(p,-1);
    }

    //Search with Depth Limit (DLS)
    public static ArrayList<Action> search(Problem p,int depthLimit){

        Stack<Expandable> DFSStack = new Stack<>();
        ArrayList<State> closed = new ArrayList<>();

        Expandable initE = new Expandable();
        initE.state = p.initialState();
        initE.actionSequence = new ArrayList<>();

        DFSStack.add(initE);
        closed.add(initE.state);

        while(!DFSStack.empty()){
            Expandable s = DFSStack.pop();
            if(p.goalTest(s.state)){
                //Goal Reached
                System.out.println("[DFS] Goal Reached !");
                return s.actionSequence;
            }else{
                //Close Current State
                closed.add(s.state);
                //Dont Expand if Depth Limit Reached
                if(depthLimit != -1 && s.actionSequence.size() >= depthLimit) continue;
                //Expand Childs
                for(Action a : p.actions(s.state)){
                    for(State targetState : p.result(s.state,a)) {
                        boolean mustAdd = true;
                        for (State closedState : closed) {
                            if (closedState.isEquals(targetState)) {
                                mustAdd = false;
                                break;
                            }
                        }
                        for (Expandable openState : DFSStack) {
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
                            DFSStack.push(SAS);
                        }
                    }
                }
            }
        }
        //There is no answer to Problem
        System.err.println("[DFS] No Answer !");
        return null;
    }

}