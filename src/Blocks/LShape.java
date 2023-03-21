package Blocks;

import Objects.ShapeModel;
import Objects.Square;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import static Main.GamePanel.UNIT_SIZE;

public class LShape extends ShapeModel {

    private ArrayList<Square> squares;
    private boolean active;
    private int rotatePosition;
    private int startX, startY;

    public LShape(int startX, int startY) {
        squares = new ArrayList<>();
        this.startX = startX;
        this.startY = startY;
        addSquares();
        active = false;
        rotatePosition = 0;

    }
    public void addSquares() {
        squares.add(new Square(7, startX+UNIT_SIZE, startY));
        squares.add(new Square(7, startX-UNIT_SIZE, startY+UNIT_SIZE));
        squares.add(new Square(7, startX, startY+UNIT_SIZE));
        squares.add(new Square( 7, startX+UNIT_SIZE, startY+UNIT_SIZE));
    }

    public void rotate() {
        //todo effektivisera så att jag kanske kan ha funktionen i shapemodel

        rotatePosition = rotatePosition == 3 ? 0 : rotatePosition + 1;

        switch(rotatePosition) {
            case 0:
                if (checkCollisionWall(squares) == 1) {
                    for (Square square : squares) {
                        square.moveLeft();
                    }
                }
                squares.get(0).moveRight();
                squares.get(0).moveRight();
                squares.get(1).moveUp();
                squares.get(1).moveLeft();
                squares.get(3).moveRight();
                squares.get(3).moveDown();
                break;
            case 1:
                squares.get(0).moveDown();
                squares.get(0).moveDown();
                squares.get(1).moveUp();
                squares.get(1).moveRight();
                squares.get(3).moveLeft();
                squares.get(3).moveDown();
                break;
            case 2:
                if (checkCollisionWall(squares) == 0) {
                    for (Square square : squares) {
                        square.moveRight();
                    }
                }
                squares.get(0).moveLeft();
                squares.get(0).moveLeft();
                squares.get(1).moveDown();
                squares.get(1).moveRight();
                squares.get(3).moveLeft();
                squares.get(3).moveUp();
                break;
            case 3:
                squares.get(0).moveUp();
                squares.get(0).moveUp();
                squares.get(1).moveDown();
                squares.get(1).moveLeft();
                squares.get(3).moveUp();
                squares.get(3).moveRight();
                break;
        }
    }

    public void keyPressed(KeyEvent e) {
        switch(e.getKeyCode()) {
            case KeyEvent.VK_UP: case KeyEvent.VK_R:
                rotate();
                break;
            case KeyEvent.VK_SPACE:
                active = false;
                break;
        }
    }

    public void resetShape() {
        rotatePosition = 0;
        squares.get(0).move(startX+UNIT_SIZE, startY);
        squares.get(1).move(startX-UNIT_SIZE, startY+UNIT_SIZE);
        squares.get(2).move(startX, startY+UNIT_SIZE);
        squares.get(3).move(startX+UNIT_SIZE, startY+UNIT_SIZE);
    }

    public boolean getActive() {
        return active;
    }
    public void setActive(boolean value) {
        active = value;
    }

    public ArrayList<Square> getSquares() {
        return squares;
    }
}
