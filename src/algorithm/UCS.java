package algorithm;
//Uniform Cost Search Algorithm
//Created By Armin

import java.util.ArrayList;
import java.util.PriorityQueue;

public class UCS {

    public static ArrayList<Action> search(Problem p){
        return search(p,true);
    }

    public static ArrayList<Action> search(Problem p,boolean isGraphSearch){

        PriorityQueue<Expandable> UCSQueue = new PriorityQueue<>(Expandable.costComparator);
        ArrayList<State> closed = new ArrayList<>();

        Expandable initE = new Expandable();
        initE.state = p.initialState();
        initE.actionSequence = new ArrayList<>();

        UCSQueue.add(initE);

        while(!UCSQueue.isEmpty()){
            Expandable s = UCSQueue.remove();
            if(p.goalTest(s.state)){
                //Goal Reached
                System.out.println("[UCS] Goal Reached !");
                return s.actionSequence;
            }else{
                //Close Current State
                closed.add(s.state);
                //Expand Childs
                for(Action a : p.actions(s.state)){
                    for(State targetState : p.result(s.state,a)) {
                        boolean mustAdd = true;
                        if(isGraphSearch){
                            for (State closedState : closed) {
                                if (closedState.isEquals(targetState)) {
                                    mustAdd = false;
                                    break;
                                }
                            }
                        }
                        for (Expandable openState : UCSQueue) {
                            if (openState.state.isEquals(targetState)) {
                                //must update is less cost reached
                                if (s.cost + p.utility(a) < openState.getCost()) {
                                    openState.setCost(s.cost + p.utility(a));
                                }
                                //no need to add it again
                                mustAdd = false;
                                break;
                            }
                        }
                        if (mustAdd) {
                            Expandable E = new Expandable();
                            E.state = targetState;
                            //Clone Parent Action Sequence
                            ArrayList<Action> asClone = new ArrayList<>();
                            for (Action sa : s.actionSequence) {
                                asClone.add(sa);
                            }
                            asClone.add(a);
                            E.actionSequence = asClone;
                            //Set Cost (Parent Cost + Action Cost)
                            E.setCost(s.cost + p.utility(a));
                            UCSQueue.add(E);
                        }
                    }
                }
            }
        }
        //There is no answer to Problem
        System.err.println("[UCS] No Answer !");
        return null;
    }

}