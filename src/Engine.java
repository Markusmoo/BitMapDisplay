import java.awt.*;

/**
 * Created by Markus Tonsaker on 2017-09-14.
 */
public interface Engine {

    int fps = 30;

    void paint(Graphics g);
    void update();
}
