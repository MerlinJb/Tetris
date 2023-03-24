package Objects;

import java.awt.*;

import Panels.GamePanel;

public class Square {

    private Point coord;
    private Color color;
    private int id;
    private final int UNIT_SIZE = Panels.GamePanel.UNIT_SIZE;

    public Square(int id, int startX, int startY) {
        coord = new Point(startX, startY);
        this.id = id;
    }

    public void draw(Graphics g) {

        g.setColor(Color.BLACK);
        g.drawRect(coord.x, coord.y, UNIT_SIZE, UNIT_SIZE);

        switch(id) {
            case 1:
                color = Color.CYAN;
                break;
            case 2:
                color = Color.YELLOW;
                break;
            case 3:
                color = Color.MAGENTA;
                break;
            case 4:
                color = Color.GREEN;
                break;
            case 5:
                color = Color.RED;
                break;
            case 6:
                color = Color.BLUE;
                break;
            case 7:
                color = Color.ORANGE;
                break;
        }
        g.setColor(color);
        g.fillRect(coord.x, coord.y, UNIT_SIZE, UNIT_SIZE);
    }

    public boolean checkGroundCollision() {
        if (coord.y >= (GamePanel.endPoint.y)) {
            return true;
        }
        return false;
    }
    public boolean checkGroundCollision(int newY) {
        if (newY >= (GamePanel.endPoint.y)) {
            return true;
        }
        return false;
    }
    public boolean checkRowCollision(int x, int newY, Rectangle rect) {
        if (new Rectangle(x, newY, UNIT_SIZE, UNIT_SIZE).intersects(rect)) {
            return true;
        }
        return false;
    }

    public int checkWallCollision() {
        if (coord.x >= (GamePanel.endPoint.x-UNIT_SIZE)) {
            return 1;
        }
        else if (coord.x <= GamePanel.startPoint.x) {
            return 0;
        }
        return -1;
    }

    //TODO HIGHSCORE LIST WITH FILEREADER
    //TODO LÄGGA TILL SCORE
    //TODO PAUSE KNAPP SOM DAVID GJORDE MED THREADS, kanske??


    public void move(int y) {
        coord.move(coord.x, y);
    }
    public void move(int x, int y) {
        coord.x = x;
        coord.y = y;
    }

    public void moveDown() {
        coord.move(coord.x, coord.y + UNIT_SIZE);
    }
    public void moveUp() {
        coord.move(coord.x, coord.y-UNIT_SIZE);
    }
    public void moveRight() {
        coord.move(coord.x+UNIT_SIZE, coord.y);
    }
    public void moveLeft() {
        coord.move(coord.x-UNIT_SIZE, coord.y);
    }

    public int getCoordX() {
        return coord.x;
    }
    public int getCoordY() {
        return coord.y;
    }
    public int getId() {
        return id;
    }

    public void setId(int newId) {
        id = newId;
    }

}
