package Panels;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JPanel;

import Templates.ShapeModel;

public class MainPanel extends JPanel implements ActionListener{

    private CardLayout cardLayout;

    private JPanel mainPanel;
    private GamePanel gamePanel;
    private MenuPanel menuPanel;
    public static HighScorePanel highScorePanel;

    public static Dimension window = new Dimension(420, 600);
    public static final int UNIT_SIZE = window.width/15;

    public MainPanel() throws IOException {
        this.setPreferredSize(window);
        this.setFocusable(true);
        this.addKeyListener(new MyKeyAdapter());
        
        cardLayout = new CardLayout();
        mainPanel = new JPanel();
        gamePanel = new GamePanel();
        menuPanel = new MenuPanel();
        highScorePanel = new HighScorePanel();
        
        this.setLayout(cardLayout);
        settingUpMainPanel();
        this.add("mainPanel", mainPanel);
        this.add("gamePanel", gamePanel);
        this.add("menuPanel", menuPanel);
        this.add("highScorePanel", highScorePanel);
        
        cardLayout.show(this, "mainPanel");          
    }


    JButton playButton, highScoreButton;
    public void settingUpMainPanel() {
        playButton = new JButton("Play");
        highScoreButton = new JButton("HighScore");

        mainPanel.add(playButton);
        mainPanel.add(highScoreButton);

        playButton.addActionListener(this);
        highScoreButton.addActionListener(this);
    }

    public void showMenuPanel() {
        cardLayout.show(this, "menuPanel");
    }
    public void showGamePanel() {
        gamePanel.setPlayerAlive(true);
        cardLayout.show(this, "gamePanel");
    }
    public void showHighScorePanel() {
        cardLayout.show(this, "highScorePanel");
    }

    public class MyKeyAdapter extends KeyAdapter {
        public void keyPressed(KeyEvent e) {
            System.out.println("pressed");
            if (gamePanel.getPlayerAlive() && !GamePanel.pauseGame) {
                for (ShapeModel shape : GamePanel.shapes) {
                    if (shape.getActive()) {
                        shape.keyPressed(e, GamePanel.getRows()); 
                        break;
                    }
                }
            }
            if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                GamePanel.pauseGame = !GamePanel.pauseGame;
                if (!GamePanel.pauseGame) {
                    gamePanel.resumeGame();
                    showGamePanel();
                }
                else {
                    System.out.println("paused");
                    //setVisible(false);
                    //pausePanel.setVisible(true);
                    showMenuPanel();
                }
            }
        }
        public void keyReleased(KeyEvent e) {
            System.out.println("released");
            if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                GamePanel.setReady(true);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        System.out.println(e.getSource());

        if (e.getSource() == playButton) {
            showGamePanel();
            /*
            try {
                highScorePanel.changingHighScoreList("walter", 14);
            } catch (IOException ex) {
                ex.printStackTrace();
            }

             */

        }
        else if (e.getSource() == highScoreButton) {
            showHighScorePanel();
        }
        

    }

}
