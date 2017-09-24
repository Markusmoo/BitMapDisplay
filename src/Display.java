import com.sun.corba.se.impl.orbutil.graph.Graph;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

/**
 * Created by Marku on 2017-05-25.
 */
public class Display extends JPanel implements Engine, MouseListener{

    public static final int NUM_GRIDX = 40;
    public static final int NUM_GRIDY = 20;

    private boolean ready = false;

    public ArrayList<Point> points = new ArrayList<>();
    public Map map = new Map();

    public static JFrame frame;

    public static void main(String[] args){
        Tile.loadTiles();
        Display disp = new Display();
        disp.frame.setVisible(true);
    }

    public Display(){
        frame = new JFrame("Grid");
        frame.setResizable(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(100,100,516,514);
        frame.setContentPane(this);
        Tile.width = this.getWidth()/NUM_GRIDX;
        Tile.height = this.getWidth()/NUM_GRIDY;
        this.setDoubleBuffered(true);
        this.addMouseListener(this);

        ready = true;
        this.repaint();
    }

    public void delay(int time){
        try{
            Thread.sleep(time);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public void paint(Graphics g){
        super.paint(g);
        if(ready){
            paintGraphics(g);
        }
    }

    public void paintGraphics(Graphics g){
        Tile.width = this.getWidth()/NUM_GRIDX;
        Tile.height = this.getWidth()/NUM_GRIDY;
        System.out.println(Tile.width);
        map.update();
        map.paint(g);
        int x,y;
        for(x = 0; x+this.getWidth()/NUM_GRIDX <= this.getWidth(); x += this.getWidth()/NUM_GRIDX){
            g.drawLine(x, 0, x, this.getHeight());
        }
        for(y = 0; y+this.getHeight()/NUM_GRIDY <= this.getHeight(); y += this.getHeight()/NUM_GRIDY){
            g.drawLine(0, y, this.getWidth(), y);
        }
        g.fillRect(x+1, 0, this.getWidth()-x, this.getHeight());
        g.fillRect(0, y+1, this.getWidth(), this.getHeight()-y);
        Color orgColor = g.getColor();
        g.setColor(Color.red);
        for(Point p : points){
            g.drawLine(p.x, p.y, p.x, p.y);
        }
        g.setColor(orgColor);
    }

    @Override
    public void update() {
        map.update();
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        System.out.println(e.getPoint());
        points.add(e.getPoint());
        this.repaint();
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
