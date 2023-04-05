package Blocks;

import static Panels.GamePanel.UNIT_SIZE;

import java.util.ArrayList;

import Objects.Row;
import Objects.Square;
import Templates.ShapeModel;

public class SShape extends ShapeModel {

    public SShape(boolean active, int startX, int startY) {
        super(active);
        this.startX = startX;
        this.startY = startY;
        addSquares();
    }
    public void addSquares() {
        squares.add(new Square(4, startX, startY));
        squares.add(new Square(4, startX+UNIT_SIZE, startY));
        squares.add(new Square(4, startX-UNIT_SIZE, startY+UNIT_SIZE));
        squares.add(new Square( 4, startX, startY+UNIT_SIZE));
    }

    @Override
    public void rotate(ArrayList<Row> rows) {
        //todo effektivisera så att jag kanske kan ha funktionen i shapemodel

        rotatePosition = rotatePosition == 3 ? 0 : rotatePosition + 1;

        switch(rotatePosition) {
            case 0:
                if (checkCollisionWall() == 1) {
                    for (Square square : squares) {
                        square.moveLeft();
                    }
                }
                squares.get(0).moveUp();
                squares.get(0).moveRight();
                squares.get(1).moveRight();
                squares.get(1).moveRight();
                squares.get(2).moveUp();
                squares.get(2).moveLeft();
                while (!canRotate(rows)) {
                    for (Square square : squares) {
                        square.moveUp();
                    }
                }
                break;
            case 1:
                squares.get(1).moveDown();
                squares.get(2).moveRight();
                squares.get(2).moveRight();
                squares.get(2).moveDown();
                while (!canRotate(rows)) {
                    for (Square square : squares) {
                        square.moveUp();
                    }
                }
                break;
            case 2:
                if (checkCollisionWall() == 0) {
                    for (Square square : squares) {
                        square.moveRight();
                    }
                }
                squares.get(0).moveDown();
                squares.get(0).moveDown();
                squares.get(0).moveLeft();
                squares.get(2).moveLeft();
                while (!canRotate(rows)) {
                    for (Square square : squares) {
                        square.moveUp();
                    }
                }
                break;
            case 3:
                squares.get(0).moveUp();
                squares.get(1).moveUp();
                squares.get(1).moveLeft();
                squares.get(1).moveLeft();
                while (!canRotate(rows)) {
                    for (Square square : squares) {
                        square.moveUp();
                    }
                }
                break;
        }
    }

    @Override
    public void resetShape() {
        rotatePosition = 0;
        squares.get(0).move(startX, startY);
        squares.get(1).move(startX+UNIT_SIZE, startY);
        squares.get(2).move(startX-UNIT_SIZE, startY+UNIT_SIZE);
        squares.get(3).move(startX, startY+UNIT_SIZE);
    }
}
