import algorithm.*;
import problems.*;

import java.util.ArrayList;

public class MainDriver {

    public static void main(String[] args) {

        //Solve Pouring Problem with BFS
        PouringProblem pp = new PouringProblem();
        ArrayList<Action> actions = BFS.search(pp);
        if(actions != null){
            for(Action a : actions) System.out.print(a.actionCode + " ");
        }

    }

}
