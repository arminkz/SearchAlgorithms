package problems;

import algorithm.*;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;

public class PouringProblem implements BidirectionalProblem {

    public static final int CapacityA = 4;
    public static final int CapacityB = 3;

    @Override
    public State initialState() {
        return new PouringState(0,0);
    }

    @Override
    public ArrayList<Action> actions(State s) {
        // 1 : Fill Flask A
        // 2 : Fill Flask B
        // 3 : Empty Flask A
        // 4 : Empty Flask B
        // 5 : Pour A -> B
        // 6 : Pour B -> A

        PouringState ps = (PouringState)s;
        ArrayList<Action> acts = new ArrayList<>();
        if(ps.flaskA < CapacityA) acts.add(new Action(1));
        if(ps.flaskB < CapacityB) acts.add(new Action(2));
        if(ps.flaskA > 0) acts.add(new Action(3));
        if(ps.flaskB > 0) acts.add(new Action(4));
        if(ps.flaskA > 0 && ps.flaskB < CapacityB) acts.add(new Action(5));
        if(ps.flaskB > 0 && ps.flaskA < CapacityA) acts.add(new Action(6));
        return acts;
    }

    @Override
    public ArrayList<State> result(State s, Action a) {
        PouringState cur = (PouringState)s;
        int fa = cur.flaskA;
        int fb = cur.flaskB;

        switch(a.actionCode){
            case 1:
                fa = CapacityA;
                break;
            case 2:
                fb = CapacityB;
                break;
            case 3:
                fa = 0;
                break;
            case 4:
                fb = 0;
                break;
            case 5:
                int m1 = Math.min(CapacityB - fb , fa);
                fa -= m1;
                fb += m1;
                break;
            case 6:
                int m2 = Math.min(CapacityA - fa , fb);
                fa += m2;
                fb -= m2;
                break;
        }

        ArrayList<State> singleState = new ArrayList<>();
        singleState.add(new PouringState(fa,fb));
        return singleState;
    }

    @Override
    public boolean goalTest(State s) {
        PouringState ps = (PouringState)s;
        return (ps.flaskA == 2);
    }

    @Override
    public int utility(Action a) {
        return 1;
    }

    @Override
    public int heuristic(State s) {
        System.err.println("Heuristic is Not Supported in this Problem !");
        throw new NotImplementedException();
    }


    @Override
    public State goalState() {
        return new PouringState(2,3);
    }

    @Override
    public ArrayList<Action> actionsBd(State s) {
        PouringState ps = (PouringState)s;
        // 1 : Fill Flask A
        // 2 : Fill Flask B
        // 3 : Empty Flask A
        // 4 : Empty Flask B
        // 5 : Pour A -> B
        // 6 : Pour B -> A

        ArrayList<Action> acts = new ArrayList<>();
        if(ps.flaskA == CapacityA) acts.add(new Action(1));
        if(ps.flaskB == CapacityB) acts.add(new Action(2));
        if(ps.flaskA == 0) acts.add(new Action(3));
        if(ps.flaskB == 0) acts.add(new Action(4));
        if(ps.flaskB != 0 && ps.flaskA != CapacityA) acts.add(new Action(5));
        if(ps.flaskA != 0 && ps.flaskB != CapacityB) acts.add(new Action(6));
        return acts;
    }

    @Override
    public ArrayList<State> resultBd(State s, Action a) {
        PouringState cur = (PouringState)s;
        int fa = cur.flaskA;
        int fb = cur.flaskB;

        ArrayList<State> possibleStates = new ArrayList<>();

        switch(a.actionCode){
            case 1:
                for (int i = 0; i < CapacityA ; i++) {
                    possibleStates.add(new PouringState(i,fb));
                }
                break;
            case 2:
                for (int i = 0; i < CapacityB ; i++) {
                    possibleStates.add(new PouringState(fa,i));
                }
                break;
            case 3:
                for (int i = 1 ; i <= CapacityA ; i++) {
                    possibleStates.add(new PouringState(i,fb));
                }
                break;
            case 4:
                for (int i = 1 ; i <= CapacityB ; i++) {
                    possibleStates.add(new PouringState(fa,i));
                }
                break;
            case 5:
                while(fa < CapacityA - 1 && fb>0){
                    fa++;
                    fb--;
                    possibleStates.add(new PouringState(fa,fb));
                }
                break;
            case 6:
                while(fb < CapacityB - 1 && fa>0){
                    fa--;
                    fb++;
                    possibleStates.add(new PouringState(fa,fb));
                }
                break;
        }

        return possibleStates;
    }
}

class PouringState implements State{
    public int flaskA;
    public int flaskB;

    public PouringState(int A,int B){
        flaskA = A;
        flaskB = B;
    }

    public boolean isEquals(State s){
        PouringState ps = (PouringState)s;
        return (ps.flaskA == flaskA && ps.flaskB == flaskB);
    }
}