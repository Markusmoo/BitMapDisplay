import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;

/**TODO Change from EVEN number of columns to ODD number
 * TODO Design more efficient game engine
 *
 * Created by Markus Tonsaker on 2017-05-25.
 */
public class Display extends JPanel implements Engine, MouseListener{

    public int paintMode = 0;

    public static final int NUM_GRIDX = 40;
    public static final int NUM_GRIDY = 20;

    private boolean ready = false;

    public ArrayList<Point> points = new ArrayList<>();
    public Map map = new Map();

    public static JFrame frame;

    public static void main(String[] args){
        Display disp = new Display();
        disp.frame.setVisible(true);
    }

    public Display(){
        frame = new JFrame("Grid");
        frame.setResizable(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(100,100,516,514);
        frame.setContentPane(this);
        Map.gridWidth = this.getWidth()/NUM_GRIDX;
        Map.gridHeight = this.getWidth()/NUM_GRIDY;
        this.setDoubleBuffered(true);
        this.addMouseListener(this);

        ready = true;
        Timer t = new Timer(1000/Engine.fps, e -> repaint());
        t.start();
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
        Map.gridWidth = this.getWidth()/NUM_GRIDX;
        Map.gridHeight = this.getHeight()/NUM_GRIDY;
        update();
        map.paint(g);

        Color orgColor = g.getColor();

        int x,y;
        g.setColor(Color.red);
        for(x = 0; x+this.getWidth()/NUM_GRIDX <= this.getWidth(); x += this.getWidth()/NUM_GRIDX){
            g.drawLine(x, 0, x, this.getHeight());
        }
        for(y = 0; y+this.getHeight()/NUM_GRIDY <= this.getHeight(); y += this.getHeight()/NUM_GRIDY){
            g.drawLine(0, y, this.getWidth(), y);
        }
        g.setColor(orgColor);
        g.fillRect(x+1, 0, this.getWidth()-x, this.getHeight());
        g.fillRect(0, y+1, this.getWidth(), this.getHeight()-y);
        g.setColor(Color.green);
        for(Point p : points){
            g.drawLine(p.x, p.y, p.x, p.y);
        }
        g.setColor(orgColor);
    }

    public void saveMap(){
        new File(System.getProperty("%APPDATA%")+"\\BitMapDisplay\\").mkdirs();
        int numOf = 0;
        for(String s : new File(System.getProperty("%APPDATA%")+"\\BitMapDisplay\\").list()){
            if(s.contains("level")) numOf++;
        }
        try {
            Writer writer = new OutputStreamWriter(new FileOutputStream(System.getProperty("%APPDATA%") + "\\BitMapDisplay\\level_" + numOf));
            Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
            writer.write(gson.toJson(map));
            writer.flush();
            writer.close();
        }catch (IOException e){
            e.printStackTrace();
        }
        System.out.println("save");
    }

    @Override
    public void update() {
        if(paintMode > 0){
            int selectedTileX = getMousePosition().x/Map.gridWidth;
            int selectedTileY = getMousePosition().y/Map.gridHeight;
            switch (paintMode){
                case(1): map.replaceTile(selectedTileX, selectedTileY, Tile.BLOCK); break;
                case(3): map.replaceTile(selectedTileX, selectedTileY, Tile.OPEN); break;
            }
        }
        map.update();
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        this.paintMode = e.getButton();
        if(e.getButton() == 4) saveMap();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        this.paintMode = 0;
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
