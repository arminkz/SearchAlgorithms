package problems;

import algorithm.*;

import java.util.ArrayList;

public class MazeProblem implements Problem {

    int[][] Map;
    int MapSizeX;
    int MapSizeY;

    public MazeProblem(int n, int m, int[][] map){
        MapSizeY = n;
        MapSizeX = m;
        Map = map;
    }

    @Override
    public State initialState() {
        return new MazeState(0,0);
    }

    @Override
    public ArrayList<Action> actions(State s) {
        MazeState ms = (MazeState)s;
        ArrayList<Action> acts = new ArrayList<>();

        //0 : Move Left
        //1 : Move Up
        //2 : Move Right
        //3 : Move Down

        if(ms.x > 0 && Map[ms.y][ms.x - 1]!=0) acts.add(new Action(0));
        if(ms.y > 0 && Map[ms.y - 1][ms.x]!=0) acts.add(new Action(1));
        if(ms.x < MapSizeX - 1 && Map[ms.y][ms.x + 1]!=0) acts.add(new Action(2));
        if(ms.y < MapSizeY - 1 && Map[ms.y + 1][ms.x]!=0) acts.add(new Action(3));

        return acts;
    }

    @Override
    public ArrayList<State> result(State s, Action a) {
        MazeState ms = (MazeState)s;

        ArrayList<State> singleState = new ArrayList<>();
        switch(a.actionCode){
            case 0:
                singleState.add(new MazeState(ms.x-1,ms.y));
                break;
            case 1:
                singleState.add(new MazeState(ms.x,ms.y-1));
                break;
            case 2:
                singleState.add(new MazeState(ms.x+1,ms.y));
                break;
            case 3:
                singleState.add(new MazeState(ms.x,ms.y+1));
                break;
        }
        return singleState;
    }

    @Override
    public boolean goalTest(State s) {
        MazeState ms = (MazeState)s;
        return (ms.x == MapSizeX-1 && ms.y == MapSizeY-1);
    }

    @Override
    public int utility(Action a) {
        return 1;
    }

    @Override
    public int heuristic(State s) {
        MazeState ms = (MazeState)s;
        //Using Manhattan Heuristic
        return (MapSizeX - 1 - ms.x) + (MapSizeY - 1 - ms.y);
    }
}

class MazeState implements State {

    public int x;
    public int y;

    public MazeState(int x,int y){
        this.x = x;
        this.y = y;
    }

    public boolean isEquals(State s){
        MazeState ms = (MazeState)s;
        return (ms.x == x && ms.y == y);
    }

}
