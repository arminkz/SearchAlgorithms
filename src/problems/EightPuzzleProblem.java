package problems;

import algorithm.Action;
import algorithm.Problem;
import algorithm.State;

import java.util.ArrayList;

public class EightPuzzleProblem implements Problem {

    private int[][] init;
    public EightPuzzleProblem(int[][] init){
        this.init = init;
    }

    @Override
    public State initialState() {
        return new EightPuzzleState(init);
    }

    @Override
    public ArrayList<Action> actions(State s) {
        EightPuzzleState eps = (EightPuzzleState)s;
        ArrayList<Action> acts = new ArrayList<>();
        //find index of 0
        int i0 = -1 ,j0 = -1;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if(eps.P[i][j] == 0){
                    i0 = i;
                    j0 = j;
                }
            }
        }
        if(i0 == -1 || j0 == -1) return null;

        //0 : Move Left
        //1 : Move Up
        //2 : Move Right
        //3 : Move Down

        //Move Left
        if(j0 > 0) acts.add(new Action(0));

        //Move Up
        if(i0 > 0) acts.add(new Action(1));

        //Move Right
        if(j0 < 2) acts.add(new Action(2));

        //Move Down
        if(i0 < 2) acts.add(new Action(3));

        return acts;
    }

    @Override
    public State result(State s, Action a) {
        EightPuzzleState eps = (EightPuzzleState)s;
        EightPuzzleState newstate = eps.clone();

        //find index of 0
        int i0 = -1 ,j0 = -1;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if(eps.P[i][j] == 0){
                    i0 = i;
                    j0 = j;
                }
            }
        }
        if(i0 == -1 || j0 == -1) return null;



        switch(a.actionCode){
            case 0:
                newstate.P[i0][j0] = eps.P[i0][j0-1];
                newstate.P[i0][j0-1] = 0;
                break;

            case 1:
                newstate.P[i0][j0] = eps.P[i0-1][j0];
                newstate.P[i0-1][j0] = 0;
                break;

            case 2:
                newstate.P[i0][j0] = eps.P[i0][j0+1];
                newstate.P[i0][j0+1] = 0;
                break;

            case 3:
                newstate.P[i0][j0] = eps.P[i0+1][j0];
                newstate.P[i0+1][j0] = 0;
                break;
        }

        return newstate;
    }

    @Override
    public boolean goalTest(State s) {
        EightPuzzleState eps = (EightPuzzleState)s;
        int n = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (eps.P[i][j] != n) return false;
                n++;
            }
        }
        return true;
    }

    @Override
    public int utility(Action a) {
        return 1;
    }

    @Override
    public int heuristic(State s) {
        return 0;
    }

}

class EightPuzzleState implements State{

    public int[][] P;

    public EightPuzzleState(int[][] in){
        P = in;
    }

    public EightPuzzleState clone(){
        int[][] NP = new int[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                NP[i][j] = P[i][j];
            }
        }
        return new EightPuzzleState(NP);
    }

    public boolean isEquals(State s){
        if(s instanceof EightPuzzleState) {
            EightPuzzleState eps = (EightPuzzleState) s;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (P[i][j] != eps.P[i][j]) return false;
                }
            }
            return true;
        }else{
            return false;
        }
    }
}