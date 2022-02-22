package fishing;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ResourceBundle;
public class Fishing extends JFrame implements ActionListener {
    public JButton newGame, sailColor, exitGame, changeLanguage, saveProgress, changeLvl;
    private Font font;
    private JLabel background;
    public BufferedImage bgImage;
    public ResourceBundle bundle;
    public Player player;
    public ColorSettings colorSettings;
    public LevelSettings levelSettings;
    public String boatPath;
    public Game newgame;
    public int FISHING_ROD_DOWN_SPEED = 20;
    public int FISHING_ROD_UP_SPEED = 30;
    public int BOAT_SPEED = 10;


    public Fishing() throws HeadlessException {
        super("Gra - Lowienie ryb");
        this.setSize(1200, 800);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setFocusable(true);
        this.requestFocusInWindow();
        this.setLayout(new BorderLayout());
        this.setResizable(false);
        font = new Font("Comic Sans MS", Font.BOLD, 30);
        bundle = ResourceBundle.getBundle("fishing/myProp");
        boatPath = "obrazki/boatRed.png";
        player = new Player();
        levelSettings = new LevelSettings(this);
        colorSettings = new ColorSettings(this);
        try {
            bgImage = ImageIO.read(getClass().getResource("obrazki/menu.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        ImageIcon image = new ImageIcon(bgImage);

        background = new JLabel(image);

        add(background);
        background.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipadx = 1;
        c.insets = new Insets(10, 0, 0, 0);

        c.gridx = 5;
        c.gridy = 0;
        newGame = new JButton(bundle.getString("newgame"));
        newGame.setFont(font);
        newGame.setActionCommand("NewGame");
        newGame.addActionListener(this);
        background.add(newGame, c);

        c.gridy = 1;
        sailColor = new JButton(bundle.getString("sailColor"));
        sailColor.setFont(font);
        sailColor.setActionCommand("sailColor");
        sailColor.addActionListener(this);
        background.add(sailColor, c);

        c.gridy = 2;
        saveProgress = new JButton(bundle.getString("save"));
        saveProgress.setFont(font);
        saveProgress.setActionCommand("save");
        saveProgress.addActionListener(this);
        background.add(saveProgress, c);

        c.gridy = 3;
        changeLanguage = new JButton(bundle.getString("change"));
        changeLanguage.setFont(font);
        changeLanguage.setActionCommand("ChangeLanguage");
        changeLanguage.addActionListener(this);
        background.add(changeLanguage, c);

        c.gridy = 4;
        changeLvl = new JButton(bundle.getString("lvlLabel"));
        changeLvl.setFont(font);
        changeLvl.setActionCommand("changeLvl");
        changeLvl.addActionListener(this);
        background.add(changeLvl, c);

        c.gridy = 5;
        exitGame = new JButton(bundle.getString("exit"));
        exitGame.setFont(font);
        exitGame.setActionCommand("Exit");
        exitGame.addActionListener(this);
        background.add(exitGame, c);



    }



    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Fishing frame = new Fishing();
                frame.setVisible(true);
            }
        });

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand() == "sailColor") {
            colorSettings.setVisible(true);

        } else if (e.getActionCommand() == "Exit") {
            System.exit(0);

        } else if (e.getActionCommand() == "NewGame") {
            newgame = new Game(this);
            newgame.setVisible(true);

        } else if (e.getActionCommand() =="changeLvl" ) {
            levelSettings.setVisible(true);
        } else if (e.getActionCommand() == "save") {
            try {
                player.saveGame();
            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
            } catch (UnsupportedEncodingException e1) {
                e1.printStackTrace();
            }
        } else if (e.getActionCommand() == "ChangeLanguage") {
            LanguageSettings langSettings = new LanguageSettings(this,colorSettings ,levelSettings);
            langSettings.setVisible(true);
        }

    }

}
