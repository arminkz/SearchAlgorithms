import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Maze extends JPanel {


    public static void main(String[] args) {
        JFrame mainFrame = new JFrame();
        mainFrame.setTitle("Maze v0.1");
        mainFrame.setSize(500,500);
        Maze m = new Maze();
        //m.setBackground(new Color(150,150,150));
        mainFrame.add(m);

        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainFrame.setVisible(true);
    }

    public MazeCell[][] mazeButtons;
    int mazeSize = 10;

    public Maze(){

        this.setLayout(new BorderLayout());
        JButton solveButton = new JButton();
        solveButton.setText("Solve Maze !");
        solveButton.addActionListener((ActionEvent e) -> solve());
        this.add(solveButton,BorderLayout.SOUTH);

        JPanel mazeCellHost = new JPanel();
        //init cells
        mazeCellHost.setLayout(new GridLayout(10,10));
        mazeButtons = new MazeCell[mazeSize][mazeSize];
        for (int i = 0; i < mazeSize ; i++) {
            for (int j = 0; j < mazeSize; j++) {
                MazeCell m = new MazeCell(i,j);
                //b.addActionListener(new MazeButtonActionListener(i,j));
                mazeButtons[i][j] = m;
                mazeCellHost.add(m);
            }
        }
        this.add(mazeCellHost);
    }

    public void solve(){
        int sx = -1;
        int sy = -1;
        //get source node
        for (int i = 0; i < mazeSize; i++) {
            for (int j = 0; j < mazeSize; j++) {
                if(mazeButtons[i][j].color == 2){
                    sx = i;
                    sy = j;
                }
            }
        }

        for (int i = 0; i < mazeSize ; i++) {
            for (int j = 0; j < mazeSize; j++) {
                visited[i][j] = false;
            }
        }
    }

    boolean[][] visited;

    public void addAdjNodes(int x,int y,GraphNode n){

    }

    /*class MazeButtonActionListener implements ActionListener {

        private int x;
        private int y;

        public MazeButtonActionListener(int x , int y){
            this.x = x;
            this.y = y;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            mazeButtons[x][y].setText("0");
            System.out.println("Action Recorded : " + x + " , "+ y);
        }
    }*/

}
