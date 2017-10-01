import java.awt.*;

/**
 * Created by Markus Tonsaker on 2017-09-14.
 */
public class Tile implements Engine{

    public static final int OPEN                                = 0;
    public static final int BLOCK                               = 1;

    public static int width = 0;
    public static int height = 0;

    public int type = -1;
    public int x, y;

    public Tile(int x, int y, int type){
        this.x = x;
        this.y = y;
        this.changeType(type);
    }

    public void changeType(int newType){
        this.type = newType;
    }

    @Override
    public void paint(Graphics g) {
        if(type == BLOCK) {
            Color orgColor = g.getColor();
            g.setColor(Color.blue);
            g.fillRect(x * width, y * height, width, height);
            g.setColor(orgColor);
        }
    }

    @Override
    public void update() {

    }
}
