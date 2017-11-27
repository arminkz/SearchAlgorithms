import algorithm.*;
import problems.*;

import java.util.ArrayList;
import java.util.Scanner;

public class MainDriver {

    public static void main(String[] args) {

        Scanner scn = new Scanner(System.in);

        //Solve Pouring Problem with DFS
        PouringProblem pp = new PouringProblem();

        ArrayList<Action> actions = DFS.search(pp);
        if(actions != null){
            for(Action a : actions) System.out.print(a.actionCode + " ");
        }
        System.out.println("");

        //Solve Pouring Problem with Bidirectional
        ArrayList<Action> actions2 = Bidirectional.search(pp);
        if(actions2 != null){
            for(Action a : actions2) System.out.print(a.actionCode + " ");
        }
        System.out.println("");

        //Solve 8 Puzzle with BFS (Tree Search)
        System.out.println("Enter 8 Puzzle : ");
        int[][] puzzle = new int[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                puzzle[i][j] = scn.nextInt();
            }
        }
        EightPuzzleProblem epp = new EightPuzzleProblem(puzzle);

        ArrayList<Action> actions3 = BFS.search(epp,false);
        if(actions3 != null){
            for(Action a : actions3) System.out.print(a.actionCode + " ");
        }
        System.out.println("");

        //Solve 8 Puzzle with DFS (Depth Limit 8)
        ArrayList<Action> actions4 = DFS.search(epp,8);
        if(actions4 != null){
            for(Action a : actions4) System.out.print(a.actionCode + " ");
        }
        System.out.println("");

        //Solve 8 Puzzle with AStar
        ArrayList<Action> actions5 = AStar.search(epp);
        if(actions5 != null){
            for(Action a : actions5) System.out.print(a.actionCode + " ");
        }
        System.out.println("");

        //Solve Maze with UCS
        System.out.println("Please enter n , m , map[n][m] :");
        int n = scn.nextInt();
        int m = scn.nextInt();
        int[][] map = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                map[i][j] = scn.nextInt();
            }
        }
        MazeProblem mp = new MazeProblem(n,m,map);

        ArrayList<Action> actions6 = UCS.search(mp);
        if(actions6 != null){
            for(Action a : actions6) System.out.print(a.actionCode + " ");
        }
        System.out.println("");

        //Solve Maze with A*
        ArrayList<Action> actions7 = AStar.search(mp);
        if(actions7 != null){
            for(Action a : actions7) System.out.print(a.actionCode + " ");
        }
        System.out.println("");

        //Solve Maze with BD
        ArrayList<Action> actions8 = Bidirectional.search(mp);
        if(actions8 != null){
            for(Action a : actions8) System.out.print(a.actionCode + " ");
        }
        System.out.println("");


    }



}
