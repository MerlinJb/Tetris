package Objects;

import java.awt.*;

public class Score {

    private int score;
    private Point p;

    public Score(Point p) {
        this.p = p;
        score = 0;
    }

    public int getScore() {
        return score;
    }
    public void setScore(int newScore) {
        score = newScore;
    }

    public void draw(Graphics g) {
        g.setColor(Color.BLACK);
        g.setFont(new Font("Ink Font", Font.PLAIN, 20));
        g.drawString("Score: " + score, p.x, p.y);
    }

}
