package algorithm;
//Depth First Search Algorithm
//Complexity is O(b^d)
//Created By Armin

import java.util.ArrayList;
import java.util.Stack;

public class DFS {

    public static ArrayList<Action> search(Problem p,int depthLimit){

        Stack<Expandable> DFSStack = new Stack<>();
        ArrayList<State> closed = new ArrayList<>();

        Expandable initSAS = new Expandable();
        initSAS.state = p.initialState();
        initSAS.actionSequence = new ArrayList<>();

        DFSStack.add(initSAS);
        while(!DFSStack.empty()){
            Expandable s = DFSStack.pop();
            if(p.goalTest(s.state)){
                //Goal Reached
                System.out.println("[DFS] Goal Reached !");
                return s.actionSequence;
            }else{
                //Close Current State
                closed.add(s.state);
                //Dont Continue if Depth Limit Reached
                if(s.actionSequence.size() >= depthLimit) continue;
                //Expand Childs
                for(Action a : p.actions(s.state)){
                    State targetState = p.result(s.state,a);
                    boolean mustAdd = true;
                    for(State closedState : closed){
                        if(closedState.isEquals(targetState)){
                            mustAdd = false;
                            break;
                        }
                    }
                    for(Expandable openState : DFSStack){
                        if(openState.state.isEquals(targetState)){
                            mustAdd = false;
                            break;
                        }
                    }
                    if(mustAdd) {
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
        //There is no answer to Problem
        System.err.println("[DFS] No Answer !");
        return null;
    }

}