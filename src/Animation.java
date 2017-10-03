import org.imgscalr.Scalr;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by Markus Tonsaker on 2017-10-02.
 */
public class Animation implements Engine {

    protected BufferedImage[] images;
    protected BufferedImage currentImage;
    protected int imageCounter = 0;
    protected boolean playReverse = false;
    protected float speed = 1.0F;

    protected int maxCount;
    protected int counter = 0;

    public Animation(float speed, BufferedImage[] images){
        this.setSpeed(speed);
        this.images = images;
    }

    public Animation(BufferedImage[] images){
        this(1.0F,images);
    }

    public void resize(int width, int height){
        BufferedImage[] imgs = new BufferedImage[images.length];
        for(int i = 0; i < images.length; i++){
            imgs[i] = Scalr.resize(images[i], Scalr.Method.ULTRA_QUALITY, width, height);
        }
        images = imgs;
    }

    public BufferedImage currentImage(){
        return currentImage;
    }

    public void setPlayReverse(boolean playReverse){
        this.playReverse = playReverse;
    }

    public boolean getPlayReverse(){
        return this.playReverse;
    }

    public void setSpeed(float speed){ //0.0F to 1.0F
        this.speed = speed;
        maxCount = (int) (Engine.fps - speed*Engine.fps);
    }

    public double getSpeed(){
        return speed;
    }

    @Override
    public void paint(Graphics g) {

    }

    @Override
    public void update() {
        if(counter >= maxCount){
            counter = 0;
            if(playReverse){
                currentImage = images[imageCounter--];
            }else{
                currentImage = images[imageCounter++];
            }
            if(imageCounter >= images.length) imageCounter = 0;
            else if(imageCounter < 0) imageCounter = images.length-1;
        }
        counter++;
    }
}
