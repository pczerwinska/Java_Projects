package fishing;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Waves {

    private String wavesPath;
    private int posY;
    private int iPosX;
    private int posX;
    private BufferedImage wavesImage;
    private boolean wavesRight;
    private int sign;

    public Waves(int X, int Y) {
        posX = X;
        iPosX = X;
        posY = Y;
        wavesPath = "obrazki/waves.png";
        loadImage(wavesPath);
    }

    private void loadImage(String wavesPath) {
        try {
            wavesImage = ImageIO.read(getClass().getResource(wavesPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void moveWaves() {
        if (posX >= iPosX + 20) {
            wavesRight = false;
        }
        if (posX <= iPosX) {
            wavesRight = true;
        }
        sign = wavesRight ? 1 : -1;
        posX += 2 * sign;
    }

    public void draw(Graphics2D g2d) {
        g2d.drawImage(wavesImage, posX, posY, null);
    }

}
