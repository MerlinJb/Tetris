package Panels;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;

public class MenuPanel extends JPanel implements ActionListener{

    private JButton resumeButton = new JButton("RESUME");
    private JButton highScoreButton = new JButton("HIGHSCORE LIST");

    public MenuPanel() {
        this.add(resumeButton);
        this.add(highScoreButton);
        resumeButton.addActionListener(this);
        highScoreButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == resumeButton) {
            System.out.println("resume");
        }
        if (e.getSource() == highScoreButton) {
            System.out.println("highscoreButton");
        }



    }
}
