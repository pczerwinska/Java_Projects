package fishing;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Game extends JFrame implements KeyListener {
    private String boatPath;
    private ArrayList<Treasure> treasures;
    private ArrayList<Treasure> secondTreasures;
    private Font font;
    private BufferedImage background;
    private BufferedImage boat;
    private BufferStrategy bstrategy;
    private boolean[] keys;
    private int boat_pos_x;
    private int boat_pos_y;
    private TimerTask task;
    private java.util.Timer timer;
    private Fish shark_fish;
    private Fish shark_fish2;
    private Fish yellfish;
    private Fish redfish;
    private FishingRod rod;
    private Cloud cloud1;
    private Cloud cloud2;
    private Waves waves;
    private Player playerPat;
    public int fishingRodDownSpeed;
    public int fishingRodUpSpeed;
    public int boatSpeed;

    public Game(Fishing fishing) {
        this.setSize(840, 800);
        this.setFont(new Font("Comic Sans MS", Font.BOLD, 35));
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setFocusable(true);
        this.requestFocusInWindow();
        this.setLayout(new BorderLayout());
        this.setResizable(false);
        bstrategy = null;
        keys = new boolean[]{false, false, false, false, false};
        boat_pos_y = 60;
        boat_pos_x = 440;
        rod = new FishingRod();
        Random rand = new Random();
        font = new Font("Comic Sans MS", Font.BOLD, 25);
        shark_fish = new Fish("shark.gif", 250);
        shark_fish2 = new Fish("shark.gif", 500);
        yellfish = new Fish("fishYELLOW.png", 310);
        redfish = new Fish("fishRED.png", 400);
        cloud1 = new Cloud(100, 60);
        cloud2 = new Cloud(550, 60);
        waves = new Waves(-40, 130);
        playerPat = new Player();
        fishingRodDownSpeed = fishing.FISHING_ROD_DOWN_SPEED;
        fishingRodUpSpeed = fishing.FISHING_ROD_UP_SPEED;
        boatSpeed = fishing.BOAT_SPEED;

        treasures = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            int x_pos = rand.nextInt(680);
            Treasure singleTreasure = new Treasure("treasure.png", x_pos);
            treasures.add(singleTreasure);
        }
        secondTreasures = new ArrayList<>();
        for (int j = 0; j < 11; j++) {
            int x_pos = rand.nextInt(680);
            Treasure anotherTreasure = new Treasure("treasure.png", x_pos);
            secondTreasures.add(anotherTreasure);

        }

        
        String backgoundPath = "obrazki/background.png";
        try {
            background = ImageIO.read(getClass().getResource(backgoundPath));
        } catch (IOException e) {
            e.printStackTrace();
        }

        
        boatPath = fishing.boatPath;
        try {
            boat = ImageIO.read(getClass().getResource(boatPath));
        } catch (IOException e) {
            e.printStackTrace();
        }

        addKeyListener(this);
        timer = new Timer();
        task = new TimerTask() {
            @Override
            public void run() {
                if (shark_fish.isCuttingFishingRod(rod)
                        || shark_fish2.isCuttingFishingRod(rod)
                        || yellfish.isCuttingFishingRod(rod)
                        || redfish.isCuttingFishingRod(rod)) {
                    gameOver();
                }
                for (Treasure singleTreasure : treasures) {
                    if (singleTreasure.tryCollect(rod)) {
                        tresureCollected(playerPat);
                        repaint();
                    }
                }
                for (Treasure anotherTreasure : secondTreasures) {
                    if (anotherTreasure.tryCollect(rod)) {
                        tresureCollected(playerPat);
                        repaint();
                    }
                }
                shark_fish.moveFish();
                shark_fish2.moveFish();
                yellfish.moveFish();
                redfish.moveFish();
                cloud1.moveCloud();
                cloud2.moveCloud();
                waves.moveWaves();
                move();
                repaint();
            }
        };
        timer.scheduleAtFixedRate(task, 0, 100);
    }

    private void tresureCollected(Player player) {
        player.addPoints(10);
    }

    private void gameOver() {
        task.cancel();
        GameOver g = new GameOver();
        g.setVisible(true);
    }

    private void move() {
        moveBoat();
        moveFishingRod();
        repaint();
    }

    private void moveFishingRod() {
        if (keys[4]) {
            rod.yEnd += fishingRodDownSpeed;
        }
        if (!keys[4] && rod.yEnd > rod.yStart) {      
            rod.yEnd -= fishingRodUpSpeed;                       
        }
        if (rod.yEnd <= rod.yStart && rod.go)        
        {
            rod.go = false;
            rod.yEnd = 0;
            rod.yStart = 0;
        }
    }

    private void moveBoat() {
        int x = boat_pos_x;
        if (keys[0] && x > 10) {
            x -= boatSpeed;
        }
        if (keys[1] && x < 680) {
            x += boatSpeed;
        }
        if (rod.yEnd <= rod.yStart) {
            boat_pos_x = x;                   
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            keys[0] = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            keys[1] = true;
        }

        if (e.getKeyCode() == KeyEvent.VK_SPACE && !keys[4]) {
            keys[4] = true;
            rod.yStart = boat_pos_y + 50;
            if (rod.go) {
                rod.yEnd = rod.yEnd;

            } else {
                rod.yEnd = boat_pos_y + 60;
                rod.go = true;
            }
            rod.x = boat_pos_x;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            keys[0] = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            keys[1] = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            keys[4] = false;
        }
    }

    @Override
    public void paint(Graphics g) {
        this.createBufferStrategy(2);
        bstrategy = this.getBufferStrategy();
        Graphics2D g2d = (Graphics2D) bstrategy.getDrawGraphics();
        g2d.setColor(new Color(96, 96, 96));
        g2d.setStroke(new BasicStroke(2));
        g2d.drawImage(background, 0, 0, null);
        cloud1.draw(g2d);
        cloud2.draw(g2d);
        shark_fish.draw(g2d);
        redfish.draw(g2d);
        yellfish.draw(g2d);
        shark_fish2.draw(g2d);
        g2d.drawImage(boat, boat_pos_x, boat_pos_y, null);
        waves.draw(g2d);
        for (Treasure singleTreasure : treasures) {
            singleTreasure.draw(g2d);
        }
        if (playerPat.playerPoints >= 70) {
            for (Treasure anotherTreasure : secondTreasures) {
                anotherTreasure.draw(g2d);
            }
        }
        g2d.drawLine(rod.x, rod.yStart, rod.x, rod.yEnd);
        g2d.drawString(playerPat.writePoints(playerPat), 10, 75);
        if(playerPat.playerPoints>=180){
            g2d.drawString("YOU WON! CONGRATULATIONS", 250, 75);
        }

        g2d.dispose();
        bstrategy.show();
    }


}
