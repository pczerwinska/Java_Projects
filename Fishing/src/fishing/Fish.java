package fishing;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class Fish {
    private BufferedImage fishImage;
    private int fish_pos_x;
    private int fish_pos_y;
    private String fish_path;
    private int sign;
    private boolean fish_go_right;

    public Fish(String path, int fish_pos_y) {
        fish_path = path;
        fish_go_right = false;
        loadImage(fish_path);
        Random rand = new Random();
        fish_pos_x = rand.nextInt(740);
        this.fish_pos_y = fish_pos_y;
    }

    private void loadImage(String image_path) {
        String path = "obrazki/" + image_path;
        try {
            fishImage = ImageIO.read(getClass().getResource(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private BufferedImage FishFlip(BufferedImage image) {
        AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
        tx.translate(-image.getWidth(null), 0);
        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
        image = op.filter(image, null);
        return image;
    }


    public void moveFish() {
        if (fish_pos_x >= 740) {
            fish_go_right = false;
            fishImage = FishFlip(fishImage);
        }
        if (fish_pos_x <= 1) {
            fish_go_right = true;
            fishImage = FishFlip(fishImage);
        }
        sign = fish_go_right ? 1 : -1;
        fish_pos_x += 10 * sign;
    }

    private static boolean isLeftSideOfFishingRod(int x, FishingRod fishingRod) {
        return x < fishingRod.x;
    }

    public void draw(Graphics2D g2d) {
        g2d.drawImage(fishImage, fish_pos_x, fish_pos_y, null);
    }

    public boolean isCuttingFishingRod(FishingRod fishingRod) {
        int startX, endX;
        if (fish_go_right) {
            startX = fish_pos_x;
            endX = fish_pos_x + fishImage.getWidth();
        } else {
            startX = fish_pos_x + fishImage.getWidth();
            endX = fish_pos_x;
        }
        if (isLowerThan(fishingRod)) {
            return false;
        }
        boolean wasOnLeft = isLeftSideOfFishingRod(startX, fishingRod);
        boolean isOnLeft = isLeftSideOfFishingRod(countNewX(endX), fishingRod);
        return wasOnLeft != isOnLeft;
    }

    private int countNewX(int x) {
        boolean goRight = fish_go_right;
        if (x >= 740) {
            goRight = false;
        }
        if (x <= 1) {
            goRight = true;
        }
        int sign = goRight ? 1 : -1;
        return x + 10 * sign;
    }

    private boolean isLowerThan(FishingRod fishingRod) {
        return fish_pos_y > fishingRod.yEnd;
    }

}
