package Blocks;

import Objects.ShapeModel;
import Objects.Square;
import static Main.GamePanel.UNIT_SIZE;

public class ZShape extends ShapeModel {

    public ZShape(boolean active, int startX, int startY) {
        super(active);
        this.startX = startX;
        this.startY = startY;
        addSquares();
    }
    public void addSquares() {
        squares.add(new Square(5, startX, startY));
        squares.add(new Square(5, startX+UNIT_SIZE, startY));
        squares.add(new Square(5, startX+UNIT_SIZE, startY+UNIT_SIZE));
        squares.add(new Square( 5, startX+(2*UNIT_SIZE), startY+UNIT_SIZE));
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
                    }
                }
                squares.get(0).moveLeft();
                squares.get(1).moveUp();
                squares.get(1).moveRight();
                squares.get(2).moveRight();
                squares.get(2).moveUp();
                squares.get(3).moveRight();
                break;
            case 1:
                squares.get(0).moveRight();
                squares.get(0).moveRight();
                squares.get(1).moveDown();
                squares.get(2).moveDown();
                break;
            case 2:
                if (checkCollisionWall() == 0) {
                    for (Square square : squares) {
                        square.moveRight();
                    }
                }
                squares.get(0).moveDown();
                squares.get(0).moveLeft();
                squares.get(0).moveLeft();
                squares.get(3).moveDown();
                break;
            case 3:
                squares.get(0).moveRight();
                squares.get(0).moveUp();
                squares.get(1).moveLeft();
                squares.get(2).moveLeft();
                squares.get(3).moveUp();
                squares.get(3).moveLeft();
                break;
        }
    }

    public void resetShape() {
        rotatePosition = 0;
        squares.get(0).move(startX, startY);
        squares.get(1).move(startX+UNIT_SIZE, startY);
        squares.get(2).move(startX+UNIT_SIZE, startY+UNIT_SIZE);
        squares.get(3).move(startX+(UNIT_SIZE*2), startY+UNIT_SIZE);
    }
}
