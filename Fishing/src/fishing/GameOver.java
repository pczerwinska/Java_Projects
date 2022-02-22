package fishing;

import javax.swing.*;
import java.awt.*;

public class GameOver extends JFrame {
    private final Font font;

    GameOver() {
        font = new Font("Comic Sans MS", Font.BOLD, 30);
        setFont(font);
        setLayout(new BorderLayout());
        JLabel gameOverLabel = new JLabel("GAME OVER");
        gameOverLabel.setHorizontalAlignment(JLabel.CENTER);
        gameOverLabel.setFont(font);
        add(gameOverLabel, BorderLayout.CENTER);
        setSize(300, 200);
        setLocation(300, 200);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
}
