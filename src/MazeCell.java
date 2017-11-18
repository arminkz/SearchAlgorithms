import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

public class MazeCell extends JLabel implements MouseListener {

    int color = 0;

    public MazeCell(int x , int y){

        addMouseListener(this);
        //Random rnd = new Random();
        //color = rnd.nextInt(3);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        if(color==0)g.setColor(Color.WHITE);
        if(color==1)g.setColor(Color.RED);
        if(color==2)g.setColor(Color.GREEN);
        if(color==3)g.setColor(Color.BLUE);

        g.fillRect(0,0,this.getWidth(),this.getHeight());

        g.setColor(Color.BLACK);
        g.drawRect(0,0,this.getWidth(),this.getHeight());
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        color = (color + 1) % 4;
        repaint();
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
