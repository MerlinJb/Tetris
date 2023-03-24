package Templates;

import java.awt.Dimension;
import java.awt.Graphics;
//import java.awt.event.KeyAdapter;

import javax.swing.JPanel;

public abstract class PanelModel extends JPanel {

    private boolean active;
    public static Dimension window = new Dimension(420, 600);
    public static final int UNIT_SIZE = window.width/15;

    public PanelModel(boolean active) {
        this.active = active;
        this.setPreferredSize(window);
        this.setFocusable(true);
        //this.addKeyListener(new MyKeyAdapter());
        //this.setVisible(active);
    }

    public abstract void paintComponent(Graphics g);
    public abstract void update();
    //public abstract class MyKeyAdapter extends KeyAdapter();

    public boolean getActive() {
        return active;
    }
    public void setActive(boolean bool) {
        active = bool;
    }

    public void setVisible(boolean bool) {
        this.setVisible(bool);
    }

}
