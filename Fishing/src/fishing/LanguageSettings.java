package fishing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;
import java.util.ResourceBundle;

public class LanguageSettings extends JFrame implements ActionListener {
    public LevelSettings levelSettings;
    public ResourceBundle bundle;
    public Locale enLocale = new Locale("en", "EN");
    public Locale plLocale = new Locale("pl", "PL");
    public Locale frLocale = new Locale("fr", "FR");
    public Locale itLocale = new Locale("it", "IT");
    public Fishing fishunia;
    public ColorSettings colorSettings;
    GridBagConstraints c;
    private Font font;
    private JButton polish;
    private JButton english;
    private JButton french;
    private JButton italian;


    public LanguageSettings(Fishing fishka, ColorSettings color, LevelSettings level) {
        super("");
        this.setSize(600, 400);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLayout(new GridLayout(3, 1));
        font = new Font("Comic Sans MS", Font.BOLD, 25);
        fishunia = fishka;
        colorSettings = color;
        levelSettings = level;
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipadx = 1;
        c.insets = new Insets(15, 0, 0, 0);
        c.gridx = 4;
        c.gridy = 0;
        bundle = ResourceBundle.getBundle("fishing/myProp");
        c.gridy = 1;
        polish = new JButton(bundle.getString("pol"));
        polish.setActionCommand("polski");
        polish.addActionListener(this);
        polish.setFont(font);
        this.add(polish);
        english = new JButton(bundle.getString("eng"));
        english.setActionCommand("angielski");
        english.addActionListener(this);
        english.setFont(font);
        this.add(english);
        french = new JButton(bundle.getString("fren"));
        french.setActionCommand("francuski");
        french.addActionListener(this);
        french.setFont(font);
        this.add(french);
        italian = new JButton(bundle.getString("ital"));
        italian.setActionCommand("wloski");
        italian.addActionListener(this);
        italian.setFont(font);
        this.add(italian);

    }

    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "polski": {
                changingLanguageOnButton(plLocale);
                changingLanguageOnFishingClass(plLocale);
                changingLanguageOnColorSettings(plLocale);
                changingLanguageOnLvlSettings(plLocale);
                break;
            }
            case "angielski": {
                changingLanguageOnButton(enLocale);
                changingLanguageOnFishingClass(enLocale);
                changingLanguageOnColorSettings(enLocale);
                changingLanguageOnLvlSettings(enLocale);
                break;
            }
            case "francuski": {
                changingLanguageOnButton(frLocale);
                changingLanguageOnFishingClass(frLocale);
                changingLanguageOnColorSettings(frLocale);
                changingLanguageOnLvlSettings(frLocale);
                break;
            }
            case "wloski": {
                changingLanguageOnButton(itLocale);
                changingLanguageOnFishingClass(itLocale);
                changingLanguageOnColorSettings(itLocale);
                changingLanguageOnLvlSettings(itLocale);
                break;
            }
        }


    }

    public void changingLanguageOnButton(Locale newLocale) {
        ResourceBundle newbundle = ResourceBundle.getBundle("fishing/myProp", newLocale);
        polish.setText(newbundle.getString("pol"));
        english.setText(newbundle.getString("eng"));
        french.setText(newbundle.getString("fren"));
        italian.setText(newbundle.getString("ital"));

    }

    public void changingLanguageOnFishingClass(Locale newLocale) {
        ResourceBundle newbundle = ResourceBundle.getBundle("fishing/myProp", newLocale);

        fishunia.newGame.setText(newbundle.getString("newgame"));
        fishunia.exitGame.setText(newbundle.getString("exit"));
        fishunia.sailColor.setText(newbundle.getString("sailColor"));
        fishunia.changeLanguage.setText(newbundle.getString("change"));
        fishunia.saveProgress.setText(newbundle.getString("save"));
        fishunia.changeLvl.setText(newbundle.getString("lvlLabel"));

    }

    public void changingLanguageOnColorSettings(Locale newLocale) {
        ResourceBundle newbundle = ResourceBundle.getBundle("fishing/myProp", newLocale);

        colorSettings.label.setText(newbundle.getString("label"));
        colorSettings.blue.setText(newbundle.getString("blue"));
        colorSettings.red.setText(newbundle.getString("red"));
        colorSettings.green.setText(newbundle.getString("green"));
        colorSettings.yellow.setText(newbundle.getString("yellow"));
        colorSettings.pirate.setText(newbundle.getString("pirate"));
        colorSettings.exit.setText(newbundle.getString("exit"));
    }
    public void changingLanguageOnLvlSettings(Locale newLocale) {
        ResourceBundle newbundle = ResourceBundle.getBundle("fishing/myProp", newLocale);

        levelSettings.label.setText(newbundle.getString("lvlLabel"));
        levelSettings.easy.setText(newbundle.getString("easy"));
        levelSettings.normal.setText(newbundle.getString("normal"));
        levelSettings.hard.setText(newbundle.getString("hard"));
    }


}