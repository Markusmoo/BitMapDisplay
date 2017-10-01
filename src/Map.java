import java.awt.*;
import java.util.HashMap;

/**
 * Created by Markus Tonsaker on 2017-09-14.
 */
public class Map implements Engine{

    HashMap<Point, Tile> tileHashMap = new HashMap<>();
    int max_x = Display.NUM_GRIDX;
    int max_y = Display.NUM_GRIDY;

    public Map(){
        for(int x = 0; x < max_x; x++){
            for(int y = 0; y < max_y; y++){
                tileHashMap.put(new Point(x,y), new Tile(x,y,Tile.OPEN));
            }
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
                if(t != null) t.paint(g);
            }
        }
    }

    @Override
    public void update() {
        for(int x = 0; x <= max_x; x++){
            for(int y = 0; y <= max_y; y++){
                Tile t = tileHashMap.get(new Point(x, y));
                if(t != null) t.update();
            }
        }
    }
}