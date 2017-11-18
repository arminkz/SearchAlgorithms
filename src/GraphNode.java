import java.util.ArrayList;

public class GraphNode {

    public ArrayList<GraphNode> children;
    static int GID = 1;

    public int ID;

    public GraphNode(){
        children = new ArrayList<>();
        ID = GID;
        GID++;
    }

}
