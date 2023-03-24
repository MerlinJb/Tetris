package Panels;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


public class HighScorePanel extends JPanel implements ActionListener{

    JLabel title = new JLabel("HIGHSCORELIST");
    JButton button1 = new JButton("New Game");

    public HighScorePanel() {
        //super(false);
        this.add(title);
        this.add(button1);
        button1.addActionListener(this);
    }

    @Override
    public void paintComponent(Graphics g) {
        //super.paintComponent(g);

    }

    public void update() {
        //kommer väl inte att användas om actionperformed redan finns.? 
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == button1) {
            System.out.println("button1 clicked");
        }

    }

}
