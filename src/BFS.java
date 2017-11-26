//Breadth First Search Algorithm
//Complexity is O(b^d)
//Created By Armin

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class BFS {

    public ArrayList<Action> search(Problem p){
        Queue<StateActionSequence> BFSQueue = new LinkedList<>();
        StateActionSequence initSAS = new StateActionSequence();
        initSAS.state = p.initialState();
        initSAS.actionSequence = new ArrayList<>();
        BFSQueue.add(initSAS);
        while(!BFSQueue.isEmpty()){
            StateActionSequence s = BFSQueue.remove();
            if(p.goalTest(s.state)){
                //Goal Reached
                return s.actionSequence;
            }else{
                //Expand Childs
                for(Action a : p.actions(s.state)){
                    StateActionSequence SAS = new StateActionSequence();
                    SAS.state = p.result(s.state,a);
                    //Clone Parent Action Sequence
                    ArrayList<Action> asClone = new ArrayList<>();
                    for(Action sa : s.actionSequence){
                        asClone.add(sa);
                    }
                    asClone.add(a);
                    SAS.actionSequence = asClone;
                    BFSQueue.add(SAS);
                }
            }
        }
        //There is no answer to Problem
        System.err.println("No Answer !");
        return null;
    }

}