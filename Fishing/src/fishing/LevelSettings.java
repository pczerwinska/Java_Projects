package fishing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;

public class LevelSettings extends JFrame implements ActionListener {

    public ResourceBundle bundle;
    public JButton easy, normal, hard;
    public JLabel label;
    private Font font;
    private Fishing fishunia;

    public LevelSettings(Fishing fishing) {
        super("Choose lvl");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(600, 400);
        setLayout(new GridBagLayout());
        font = new Font("Comic Sans MS", Font.BOLD, 25);
        this.setFont(font);
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipadx = 1;
        c.insets = new Insets(15, 0, 0, 0);
        c.gridx = 1;
        c.gridy = 0;
        bundle = ResourceBundle.getBundle("fishing/myProp");
        fishunia = fishing;
        label = new JLabel(bundle.getString("lvlLabel"));
        this.add(label, c);

        c.gridy = 1;
        c.gridx = 0;
        easy = new JButton(bundle.getString("easy"));
        easy.setActionCommand("Easy");
        easy.addActionListener(this);
        easy.setFont(font);
        this.add(easy, c);

        c.gridx = 1;
        normal = new JButton(bundle.getString("normal"));
        normal.setActionCommand("Normal");
        normal.addActionListener(this);
        normal.setFont(font);
        this.add(normal, c);

        c.gridx = 2;
        hard = new JButton(bundle.getString("hard"));
        hard.setActionCommand("Hard");
        hard.addActionListener(this);
        hard.setFont(font);
        this.add(hard, c);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand() == "Easy") {
            fishunia.FISHING_ROD_DOWN_SPEED = 40;
            fishunia.FISHING_ROD_UP_SPEED = 55;
            fishunia.BOAT_SPEED = 15;
        } else if(e.getActionCommand() == "Normal") {
            fishunia.FISHING_ROD_DOWN_SPEED = 20;
            fishunia.FISHING_ROD_UP_SPEED = 30;
            fishunia.BOAT_SPEED = 10;
        } else if(e.getActionCommand() == "Hard") {
            fishunia.FISHING_ROD_DOWN_SPEED = 15;
            fishunia.FISHING_ROD_UP_SPEED = 25;
            fishunia.BOAT_SPEED = 5;
        }
    }
}