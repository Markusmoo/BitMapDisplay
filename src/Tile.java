import org.imgscalr.Scalr;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.io.File;
import java.io.IOException;

/**
 * Created by Marku on 2017-09-14.
 */
public class Tile implements Engine{

    public static final int NULL                                = -1;
    public static final int OPEN                                = 0;
    public static final int BLOCK                               = 1;
    public static final int NORTH_WALL                          = 2;
    public static final int EAST_WALL                           = 3;
    public static final int SOUTH_WALL                          = 4;
    public static final int WEST_WALL                           = 5;
    public static final int NORTH_EAST_WALL                     = 6;
    public static final int EAST_SOUTH_WALL                     = 7;
    public static final int SOUTH_WEST_WALL                     = 8;
    public static final int WEST_NORTH_WALL                     = 9;
    public static final int NORTH_EAST_SOUTH_WALL               = 10;
    public static final int EAST_SOUTH_WEST_WALL                = 11;
    public static final int SOUTH_WEST_NORTH_WALL               = 12;
    public static final int WEST_NORTH_EAST_WALL                = 13;
    public static final int NORTH_EAST_SOUTH_WEST_WALL          = 14;

    private static BufferedImage IMG_NULL                       = null;
    private static BufferedImage IMG_BLOCK                      = null;
    private static BufferedImage IMG_NORTH_WALL                 = null;
    private static BufferedImage IMG_EAST_WALL                  = null;
    private static BufferedImage IMG_SOUTH_WALL                 = null;
    private static BufferedImage IMG_WEST_WALL                  = null;
    private static BufferedImage IMG_NORTH_EAST_WALL            = null;
    private static BufferedImage IMG_EAST_SOUTH_WALL            = null;
    private static BufferedImage IMG_SOUTH_WEST_WALL            = null;
    private static BufferedImage IMG_WEST_NORTH_WALL            = null;
    private static BufferedImage IMG_NORTH_EAST_SOUTH_WALL      = null;
    private static BufferedImage IMG_EAST_SOUTH_WEST_WALL       = null;
    private static BufferedImage IMG_SOUTH_WEST_NORTH_WALL      = null;
    private static BufferedImage IMG_WEST_NORTH_EAST_WALL       = null;
    private static BufferedImage IMG_NORTH_EAST_SOUTH_WEST_WALL = null;

    public static int width = 0;
    public static int height = 0;

    public int type = -1;
    public int x, y;
    private BufferedImage img;


    public Tile(int x, int y, int type){
        this.x = x;
        this.y = y;
        this.changeType(type);
    }

    public void changeType(int newType){
        this.type = newType;
        switch(type){
            case(NULL): img = IMG_NULL; break;
            case(OPEN): img = null; break;
            case(BLOCK): img = IMG_BLOCK; break;
            case(NORTH_WALL): img = IMG_NORTH_WALL; break;
            case(EAST_WALL): img = IMG_EAST_WALL; break;
            case(SOUTH_WALL): img = IMG_SOUTH_WALL; break;
            case(WEST_WALL): img = IMG_WEST_WALL; break;
            case(NORTH_EAST_WALL): img = IMG_NORTH_EAST_WALL; break;
            case(EAST_SOUTH_WALL): img = IMG_EAST_SOUTH_WALL; break;
            case(SOUTH_WEST_WALL): img = IMG_SOUTH_WEST_WALL; break;
            case(WEST_NORTH_WALL): img = IMG_WEST_NORTH_WALL; break;
            case(NORTH_EAST_SOUTH_WALL): img = IMG_NORTH_EAST_SOUTH_WALL; break;
            case(EAST_SOUTH_WEST_WALL): img = IMG_EAST_SOUTH_WEST_WALL; break;
            case(SOUTH_WEST_NORTH_WALL): img = IMG_SOUTH_WEST_NORTH_WALL; break;
            case(WEST_NORTH_EAST_WALL): img = IMG_WEST_NORTH_EAST_WALL; break;
            case(NORTH_EAST_SOUTH_WEST_WALL): img = IMG_NORTH_EAST_SOUTH_WEST_WALL; break;
            default: img = IMG_NULL; break;
        }
        img = IMG_BLOCK;
    }

    public static void loadTiles(){
        try {
            IMG_NULL = ImageIO.read(new File("res\\NULL.png"));
            IMG_BLOCK = ImageIO.read(new File("res\\BLOCK.png"));
            IMG_NORTH_WALL = ImageIO.read(new File("res\\NORTH_WALL.png"));
            IMG_EAST_WALL = ImageIO.read(new File("res\\EAST_WALL.png"));
            IMG_SOUTH_WALL = ImageIO.read(new File("res\\SOUTH_WALL.png"));
            IMG_WEST_WALL = ImageIO.read(new File("res\\WEST_WALL.png"));
            IMG_NORTH_EAST_WALL = ImageIO.read(new File("res\\NORTH_EAST_WALL.png"));
            IMG_EAST_SOUTH_WALL = ImageIO.read(new File("res\\EAST_SOUTH_WALL.png"));
            IMG_SOUTH_WEST_WALL = ImageIO.read(new File("res\\SOUTH_WEST_WALL.png"));
            IMG_WEST_NORTH_WALL = ImageIO.read(new File("res\\WEST_NORTH_WALL.png"));
            IMG_NORTH_EAST_SOUTH_WALL = ImageIO.read(new File("res\\NORTH_EAST_SOUTH_WALL.png"));
            IMG_EAST_SOUTH_WEST_WALL = ImageIO.read(new File("res\\EAST_SOUTH_WEST_WALL.png"));
            IMG_SOUTH_WEST_NORTH_WALL = ImageIO.read(new File("res\\SOUTH_WEST_NORTH_WALL.png"));
            IMG_WEST_NORTH_EAST_WALL = ImageIO.read(new File("res\\WEST_NORTH_EAST_WALL.png"));
            IMG_NORTH_EAST_SOUTH_WEST_WALL = ImageIO.read(new File("res\\NORTH_EAST_SOUTH_WEST_WALL.png"));
        }catch(IOException e){
            System.err.print("Failed to load file.. " + e.getMessage());
        }
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(img, x*width, y*height, Display.frame);
    }

    @Override
    public void update() {
        if(img != null && (img.getWidth() != width || img.getHeight() != height)){
            System.out.println("RESIZE");
            img = Scalr.resize(img, Scalr.Mode.AUTOMATIC, width, height);
        }
    }
}
