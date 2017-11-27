package algorithm;
//Bidirectional Search Algorithm
//Created By Armin

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Bidirectional {

    public static ArrayList<Action> search(BidirectionalProblem p){
        return search(p,true);
    }

    public static ArrayList<Action> search(BidirectionalProblem p,boolean isGraphSearch){

        Queue<Expandable> QueueA = new LinkedList<>();
        Queue<Expandable> QueueB = new LinkedList<>();
        ArrayList<Expandable> closedA = new ArrayList<>();
        ArrayList<Expandable> closedB = new ArrayList<>();

        Expandable initA = new Expandable();
        initA.state = p.initialState();
        initA.actionSequence = new ArrayList<>();

        Expandable initB = new Expandable();
        initB.state = p.goalState();
        initB.actionSequence = new ArrayList<>();

        QueueA.add(initA);
        QueueB.add(initB);

        while(!QueueA.isEmpty() && !QueueB.isEmpty()){
            ArrayList<Action> intersectionResult = intersect(p,closedA,closedB);
            if(intersectionResult != null){
                //Goal Reached
                System.out.println("[Bidirectional] Goal Reached !");
                return intersectionResult;
            }else{
                Expandable a = QueueA.remove();
                Expandable b = QueueB.remove();
                //Close Current State
                closedA.add(a);
                closedB.add(b);
                //Expand Childs of A
                for(Action act : p.actions(a.state)){
                    for(State targetState : p.result(a.state,act)) {
                        boolean mustAdd = true;
                        if(isGraphSearch){
                            for (Expandable closedE : closedA) {
                                if (closedE.state.isEquals(targetState)) {
                                    mustAdd = false;
                                    break;
                                }
                            }
                        }
                        for (Expandable openState : QueueA) {
                            if (openState.state.isEquals(targetState)) {
                                mustAdd = false;
                                break;
                            }
                        }
                        if (mustAdd) {
                            Expandable E = new Expandable();
                            E.state = targetState;
                            //Clone Parent Action Sequence
                            ArrayList<Action> asClone = new ArrayList<>();
                            for (Action sa : a.actionSequence) {
                                asClone.add(sa);
                            }
                            asClone.add(act);
                            E.actionSequence = asClone;
                            QueueA.add(E);
                        }
                    }
                }
                //Expand Childs of B
                for(Action act : p.actionsBd(b.state)){
                    for(State targetState : p.resultBd(b.state,act)) {
                        boolean mustAdd = true;
                        if(isGraphSearch){
                            for (Expandable closedE : closedB) {
                                if (closedE.state.isEquals(targetState)) {
                                    mustAdd = false;
                                    break;
                                }
                            }
                        }
                        for (Expandable openState : QueueB) {
                            if (openState.state.isEquals(targetState)) {
                                mustAdd = false;
                                break;
                            }
                        }
                        if (mustAdd) {
                            Expandable E = new Expandable();
                            E.state = targetState;
                            //Clone Parent Action Sequence
                            ArrayList<Action> asClone = new ArrayList<>();
                            for (Action sa : b.actionSequence) {
                                asClone.add(sa);
                            }
                            asClone.add(act);
                            E.actionSequence = asClone;
                            QueueB.add(E);
                        }
                    }
                }
            }
        }
        //There is no answer to algorithm.Problem
        System.err.println("[Bidirectional] No Answer !");
        return null;
    }

    private static ArrayList<Action> intersect(BidirectionalProblem p , ArrayList<Expandable> qOrigin , ArrayList<Expandable> qGoal){
        for(Expandable eOrigin : qOrigin){
            for(Expandable eGoal : qGoal){
                if(eOrigin.state.isEquals(eGoal.state)){
                    ArrayList<Action> finalActions = new ArrayList<>();
                    //Add Actions from Origin Normally
                    for (int i = 0; i < eOrigin.actionSequence.size(); i++) {
                        finalActions.add(eOrigin.actionSequence.get(i));
                    }
                    //Add Actions from Goal Reversed
                    for (int i = eGoal.actionSequence.size() - 1; i >=0 ; i--) {
                        finalActions.add(eGoal.actionSequence.get(i));
                    }

                    return finalActions;
                }
            }
        }
        return null;
    }

}