package fishing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;

public class ColorSettings extends JFrame implements ActionListener {

    private JPanel buttons;
    public Fishing fishunia;
    public JLabel label;
    public ResourceBundle bundle;
    public JButton blue, red, green, yellow, pirate, exit;
    private Font font;
    private GridBagConstraints c;

    public ColorSettings(Fishing fishing) {
        super("Choose color");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(600, 400);
        setLayout(new BorderLayout());
        font = new Font("Comic Sans MS", Font.BOLD, 25);
        this.setFont(font);
        buttons = new JPanel();
        buttons.setLayout(new GridBagLayout());
        this.add(buttons, BorderLayout.CENTER);

        c = new GridBagConstraints();
        c.gridx = 2;
        c.gridy = 0;
        fishunia = fishing;
        bundle = ResourceBundle.getBundle("fishing/myProp");

        label = new JLabel(bundle.getString("label"), SwingConstants.CENTER);
        label.setFont(font);
        this.add(label,BorderLayout.PAGE_START);

        c.insets = new Insets(5, 5, 5, 5);
        c.gridy = 1;
        c.gridx = 0;
        blue = new JButton(bundle.getString("blue"));
        blue.setActionCommand("blue");
        blue.addActionListener(this);
        blue.setFont(font);
        buttons.add(blue, c);

        c.gridx = 1;
        red = new JButton(bundle.getString("red"));
        red.setActionCommand("red");
        red.addActionListener(this);
        red.setFont(font);
        buttons.add(red, c);

        c.gridx = 2;
        green = new JButton(bundle.getString("green"));
        green.setActionCommand("green");
        green.addActionListener(this);
        green.setFont(font);
        buttons.add(green, c);

        c.gridx = 3;
        yellow = new JButton(bundle.getString("yellow"));
        yellow.setActionCommand("yellow");
        yellow.addActionListener(this);
        yellow.setFont(font);
        buttons.add(yellow, c);

        c.gridx = 4;
        pirate = new JButton(bundle.getString("pirate"));
        pirate.setActionCommand("pirate");
        pirate.addActionListener(this);
        pirate.setFont(font);
        buttons.add(pirate, c);

        c.gridx = 2;
        c.gridy = 2;
        exit = new JButton(bundle.getString("exit"));
        exit.setActionCommand("exit");
        exit.addActionListener(this);
        exit.setFont(font);
        buttons.add(exit, c);


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand() == "blue") {
            fishunia.boatPath = "obrazki/blueBoat.png";

        } else if (e.getActionCommand() == "red") {
            fishunia.boatPath = "obrazki/redBoat.png";

        } else if (e.getActionCommand() == "green") {
            fishunia.boatPath = "obrazki/greenBoat.png";

        } else if (e.getActionCommand() == "yellow") {
            fishunia.boatPath = "obrazki/yellowBoat.png";

        } else if (e.getActionCommand() == "pirate") {
            fishunia.boatPath = "obrazki/pirateBoat.png";

        } else if (e.getActionCommand() == "exit") {
            this.dispose();
        }
    }
}