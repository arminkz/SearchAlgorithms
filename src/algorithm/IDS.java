package algorithm;
//Iterative Deepening Search
//Created By Armin

import java.util.ArrayList;

public class IDS {

    public static ArrayList<Action> search(Problem p){
        return search(p,true);
    }

    public static ArrayList<Action> search(Problem p,boolean isGraphSearch){
        int l = 1;
        while(true) {
            ArrayList<Action> result = DFS.search(p, l,isGraphSearch);

            if (result != null) return result;
            else l++;
        }
    }
}
