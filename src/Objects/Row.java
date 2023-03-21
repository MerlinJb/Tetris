package Objects;

import java.awt.*;
import java.util.ArrayList;

public class Row extends Rectangle{

    ArrayList<Square> squares;
    private Rectangle rect;

    public Row(Rectangle rect) {
        squares = new ArrayList<>();
        this.rect = rect;
    }

    public void draw(Graphics g) {
        for (Square square : squares) {
            square.draw(g);
        }
    }

    public boolean checkRow() {
        if (squares.size() >= 10) {
            return true;
        }
        return false;
    }
    public void clear() {
        squares.clear();
    }

    public boolean intersects(Rectangle rectangle) {
        if (rect.intersects(rectangle)) {
            return true;
        }
        return false;
    }

    public void addToRow(Square square) {
        squares.add(new Square(square.getId(), square.getCoordX(), square.getCoordY()));
        //4
    }

    public ArrayList<Square> getSquares() {
        return squares;
    }
    public void setY(int newY) {
        rect.y = newY;
    }

    public void addY(int newY) {
        rect.y += newY;
    }


}
