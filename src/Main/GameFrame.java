package Main;

import javax.swing.*;

import Panels.MainPanel;

public class GameFrame extends JFrame {
    GameFrame() {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setTitle("Tetris");
        this.add(new MainPanel());
        this.setVisible(true);
        this.pack();
    }
}
