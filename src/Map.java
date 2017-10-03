import com.google.gson.annotations.Expose;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

/**
 * Created by Markus Tonsaker on 2017-09-14.
 */
public class Map implements Engine{

    public static int gridWidth = 10;
    public static int gridHeight = 10;

    @Expose public HashMap<Point, Tile> tileHashMap = new HashMap<>();
    @Expose public HashMap<Point, Entity> entityHashMap = new HashMap<>();
    int max_x = Display.NUM_GRIDX;
    int max_y = Display.NUM_GRIDY;

    public Map(){
        for(int x = 0; x < max_x; x++){
            for(int y = 0; y < max_y; y++){
                tileHashMap.put(new Point(x,y), new Tile(x,y,Tile.OPEN));
            }
        }
        try {
            BufferedImage[] imgs = {ImageIO.read(new File("res\\PACMAN\\PACMAN_0.png")),
                                    ImageIO.read(new File("res\\PACMAN\\PACMAN_1.png")),
                                    ImageIO.read(new File("res\\PACMAN\\PACMAN_2.png")),
                                    ImageIO.read(new File("res\\PACMAN\\PACMAN_1.png"))};
            Animation pacmanAni = new Animation(0.9F, imgs);
            entityHashMap.put(new Point(10,10), new Entity(pacmanAni, 10,10));
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Tile getTile(int xTile, int yTile){
        return tileHashMap.get(new Point(xTile, yTile));
    }

    public void replaceTile(int xTile, int yTile, int newTile){
        tileHashMap.get(new Point(xTile, yTile)).changeType(newTile);
    }

    @Override
    public void paint(Graphics g) {
        for(int x = 0; x <= max_x; x++){
            for(int y = 0; y <= max_y; y++){
                Tile t = tileHashMap.get(new Point(x, y));
                Entity e = entityHashMap.get(new Point(x, y));
                if(t != null) t.paint(g);
                if(e != null) e.paint(g);
            }
        }
    }

    @Override
    public void update() {
        for(int x = 0; x <= max_x; x++){
            for(int y = 0; y <= max_y; y++){
                Tile t = tileHashMap.get(new Point(x, y));
                Entity e = entityHashMap.get(new Point(x, y));
                if(t != null) t.update();
                if(e != null) e.update();
            }
        }
    }
}