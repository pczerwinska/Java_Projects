package fishing;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

public class Player {

    protected int playerPoints;
    public Game theGame;

    public Player() {

        playerPoints = 0;
    }


    public void addPoints(int points) {
        playerPoints += points;
    }

    public String writePoints(Player player) {
        return String.valueOf(player.playerPoints);
    }

    public void saveGame() throws FileNotFoundException, UnsupportedEncodingException {
        File dirPath = new File(System.getProperty("user.dir"));
        JFileChooser jchooser = new JFileChooser(dirPath);
        int returnVal = jchooser.showSaveDialog(null);
        if(returnVal==JFileChooser.APPROVE_OPTION){
            System.out.println("Zapisz do:"+jchooser.getSelectedFile());
        } else {System.out.println("nie wybrano pliku do zapisu");}
        File outputFile = jchooser.getSelectedFile();
        OutputStreamWriter osw = new OutputStreamWriter(
                new FileOutputStream(outputFile),
                Charset.forName("UTF-8").newEncoder()
        );

        try{
            osw.write(playerPoints);
            osw.close();
            System.out.println("Zapisano punkty");
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

}
