package Main;

import javax.swing.*;

public class GameFrame extends JFrame {
    GameFrame() {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setTitle("Tetris");


        this.add(new GamePanel());
        this.setVisible(true);
        this.pack();
    }
}
