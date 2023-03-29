package Panels;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import Templates.PanelModel;

public class PausePanel extends PanelModel implements ActionListener{

    private JButton button1 = new JButton("New Game");

    public PausePanel() {
        super(false);

        this.add(button1);
        button1.addActionListener(this);
    }

    @Override
    public void paintComponent(Graphics g) {

    }

    @Override
    public void update() {

    }

    @Override
    public void actionPerformed(ActionEvent e) {


        if (e.getSource() == button1) {
            System.out.println("button1 clicked");
        }

    }
}
