import java.awt.*;
import java.util.HashMap;

/**
 * Created by Marku on 2017-09-14.
 */
public class Map implements Engine{

    HashMap<Point, Tile> tileHashMap = new HashMap<>();
    int max_x = Display.NUM_GRIDX;
    int max_y = Display.NUM_GRIDY;

    public Map(){
        for(int x = 0; x <= Display.NUM_GRIDX; x++) {
            for(int y = 0; y <= Display.NUM_GRIDY; y++) {
                tileHashMap.put(new Point(x, y), new Tile(x, y, Tile.BLOCK));
            }
        }
    }

    @Override
    public void paint(Graphics g) {
        for(int x = 0; x <= max_x; x++){
            for(int y = 0; y <= max_y; y++){
                tileHashMap.get(new Point(x, y)).paint(g);
            }
        }
    }

    @Override
    public void update() {
        for(int x = 0; x <= max_x; x++){
            for(int y = 0; y <= max_y; y++){
                tileHashMap.get(new Point(x, y)).update();
            }
        }
    }
}
