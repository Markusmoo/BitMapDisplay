import org.imgscalr.Scalr;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by Markus Tonsaker on 2017-10-02.
 */
public class Entity implements Engine {

    public int x;
    public int y;

    protected int lastWidth;
    protected int lastHeight;

    public BufferedImage image;
    public Animation animation;

    public Entity(BufferedImage image, int x, int y){
        this.image = Scalr.resize(image, Scalr.Mode.AUTOMATIC, Map.gridWidth, Map.gridHeight);
        this.x = x;
        this.y = y;
    }

    public Entity(Animation animation, int x, int y){
        this.animation = animation;
        this.x = x;
        this.y = y;
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(image, x * Map.gridWidth, y * Map.gridHeight, null);
    }

    @Override
    public void update() {
        if(animation != null) {
            animation.update();
            image = animation.currentImage();
        }
        if(lastHeight != Map.gridHeight || lastWidth != Map.gridWidth){
            lastWidth = Map.gridWidth;
            lastHeight = Map.gridHeight;
            animation.resize(Map.gridWidth, Map.gridHeight);
        }
    }
}
