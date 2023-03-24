package Blocks;

import static Panels.GamePanel.UNIT_SIZE;

import Objects.Square;
import Templates.ShapeModel;

public class IShape extends ShapeModel {

    public IShape(boolean active, int startX, int startY) {
        super(active);
        this.startX = startX;
        this.startY = startY;
        addSquares();
    }
    public void addSquares() {
        squares.add(new Square(1, startX-UNIT_SIZE, startY));
        squares.add(new Square(1, startX, startY));
        squares.add(new Square(1, startX+UNIT_SIZE, startY));
        squares.add(new Square( 1, startX+2*UNIT_SIZE, startY));
    }

    @Override
    public void rotate() {
        //todo effektivisera så att jag kanske kan ha funktionen i shapemodel

        rotatePosition = rotatePosition == 3 ? 0 : rotatePosition + 1;

        switch(rotatePosition) {
            case 0:
                if (checkCollisionWall() == 1) {
                    for (Square square : squares) {
                        square.moveLeft();
                        square.moveLeft();
                    }
                }

                squares.get(0).moveDown();
                squares.get(0).moveLeft();
                squares.get(1).moveUp();
                squares.get(2).moveRight();
                squares.get(3).moveRight();
                squares.get(3).moveRight();
                squares.get(3).moveDown();
                squares.get(3).moveDown();

                break;
            case 1:
                squares.get(0).moveUp();
                squares.get(0).moveRight();
                squares.get(0).moveRight();
                squares.get(1).moveDown();
                squares.get(1).moveRight();
                squares.get(3).moveDown();
                squares.get(3).moveDown();
                squares.get(3).moveLeft();
                break;
            case 2:
                if (checkCollisionWall() == 0) {
                    for (Square square : squares) {
                        square.moveRight();
                        square.moveRight();
                    }
                }

                squares.get(0).moveRight();
                squares.get(0).moveDown();
                squares.get(0).moveDown();
                squares.get(2).moveDown();
                squares.get(2).moveLeft();
                squares.get(3).moveLeft();
                squares.get(3).moveLeft();
                squares.get(3).moveUp();

                break;
            case 3:
                squares.get(0).moveUp();
                squares.get(0).moveLeft();
                squares.get(0).moveLeft();
                squares.get(1).moveDown();
                squares.get(1).moveLeft();
                squares.get(3).moveUp();
                squares.get(3).moveUp();
                squares.get(3).moveRight();

                break;
        }
    }

    @Override
    public void resetShape() {
        rotatePosition = 0;
        squares.get(0).move(startX-UNIT_SIZE, startY);
        squares.get(1).move(startX, startY);
        squares.get(2).move(startX+UNIT_SIZE, startY);
        squares.get(3).move(startX+2*UNIT_SIZE, startY);
    }
}
