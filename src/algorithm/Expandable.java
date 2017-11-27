package algorithm;

import java.util.ArrayList;
import java.util.Comparator;

public class Expandable {
    State state;
    ArrayList<Action> actionSequence;

    int cost = 0;

    public int getCost(){ return cost; }
    public void setCost(int c) { cost = c; }

    public static Comparator<Expandable> costComparator = new Comparator<Expandable>(){
        @Override
        public int compare(Expandable c1, Expandable c2) {
            return (int) (c1.getCost() - c2.getCost());
        }
    };
}



