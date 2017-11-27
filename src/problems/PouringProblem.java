package problems;

import algorithm.*;

import java.util.ArrayList;

public class PouringProblem implements Problem {

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
    public State result(State s, Action a) {
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

        return new PouringState(fa,fb);
    }

    @Override
    public boolean goalTest(State s) {
        PouringState ps = (PouringState)s;
        return (ps.flaskA == 2);
    }

    @Override
    public int utility(Action a) {
        return 0;
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