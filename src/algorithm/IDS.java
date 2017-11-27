package algorithm;
//Iterative Deepning Search
//Created By Armin

import java.util.ArrayList;

public class IDS {

    public static ArrayList<Action> search(Problem p){
        int l = 1;
        while(true) {
            ArrayList<Action> result = DFS.search(p, l);

            if (result != null) return result;
            else l++;
        }
    }
}
