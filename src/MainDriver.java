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
        /*Scanner scn = new Scanner(System.in);
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
        }*/

        //Solve 8 Puzzle with DFS (Depth Limit 8)
        /*Scanner scn = new Scanner(System.in);
        int[][] puzzle = new int[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                puzzle[i][j] = scn.nextInt();
            }
        }
        EightPuzzleProblem epp = new EightPuzzleProblem(puzzle);
        ArrayList<Action> actions = DFS.search(epp,9);
        if(actions != null){
            for(Action a : actions) System.out.print(a.actionCode + " ");
        }*/

        //Solve Maze with UCS
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        int m = scn.nextInt();
        int[][] map = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                map[i][j] = scn.nextInt();
            }
        }
        MazeProblem mp = new MazeProblem(n,m,map);
        ArrayList<Action> actions = UCS.search(mp);
        if(actions != null){
            for(Action a : actions) System.out.print(a.actionCode + " ");
        }


    }



}
