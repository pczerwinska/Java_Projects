package fishing;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Cloud {


    private int iPosX;
    private String cloudPath;
    private int posX;
    private int posY;
    private BufferedImage cloudImage;
    private boolean cloudRight;
    private int sign;

    public Cloud(int X, int Y) {
        posX = X;
        iPosX = X;
        posY = Y;
        cloudPath = "obrazki/cloud.png";
        loadImage(cloudPath);
    }

    private void loadImage(String cloudPath) {
        try {
            cloudImage = ImageIO.read(getClass().getResource(cloudPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void moveCloud() {
        if (posX >= iPosX + 50) {
            cloudRight = false;
        }
        if (posX <= iPosX) {
            cloudRight = true;
        }
        sign = cloudRight ? 1 : -1;
        posX += 1 * sign;
    }

    public void draw(Graphics2D g2d) {
        g2d.drawImage(cloudImage, posX, posY, null);
    }
}
