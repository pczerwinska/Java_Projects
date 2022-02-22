package fishing;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Treasure {

    private BufferedImage image;
    public int posX;
    public int posY=680;
    private boolean wasCollected;


    public Treasure(String path, int posX) {
        loadImage(path);
        this.posX = posX;

    }

    private void loadImage(String path) {
        try {
            image = ImageIO.read(getClass().getResource("obrazki/" + path));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void draw(Graphics2D g2d) {
        if(!wasCollected){
            g2d.drawImage(image, posX, posY, null);
        }

    }

    public boolean collect(FishingRod rod) {
        int startX = posX;
        int endX = posX + image.getWidth();
        if (rod.x > startX && rod.x < endX && rod.yEnd > posY) {
            wasCollected = true;
            return true;
        }
        return false;
    }

    public boolean tryCollect(FishingRod rod) {
        if (wasCollected) {
            return false;
        }
        return collect(rod);
    }
}
