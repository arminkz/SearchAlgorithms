import algorithm.*;
import problems.*;

import java.util.ArrayList;
import java.util.Scanner;

public class MainDriver {

    public static void main(String[] args) {

        //Solve Pouring Problem with BFS
        /*PouringProblem pp = new PouringProblem();
        ArrayList<Action> actions = BFS.search(pp);
        if(actions != null){
            for(Action a : actions) System.out.print(a.actionCode + " ");
        }*/

        //Solve 8 Puzzle with BFS
        Scanner scn = new Scanner(System.in);
        int[][] puzzle = new int[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                puzzle[i][j] = scn.nextInt();
            }
        }
        EightPuzzleProblem epp = new EightPuzzleProblem(puzzle);
        ArrayList<Action> actions = BFS.search(epp);
        if(actions != null){
            for(Action a : actions) System.out.print(a.actionCode + " ");
        }


    }



}
