package Blocks;

import static Panels.GamePanel.UNIT_SIZE;

import Objects.Square;
import Templates.ShapeModel;

public class TShape extends ShapeModel {

    public TShape(boolean active, int startX, int startY) {
        super(active);
        this.startX = startX;
        this.startY = startY;
        addSquares();         
    }
    public void addSquares() {
        squares.add(new Square(3, startX, startY));
        squares.add(new Square(3, startX-UNIT_SIZE, startY+UNIT_SIZE));
        squares.add(new Square(3, startX, startY+UNIT_SIZE));
        squares.add(new Square( 3, startX+UNIT_SIZE, startY+UNIT_SIZE));
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
                /*
                squares.get(0).moveUp();
                squares.get(0).moveRight();
                squares.get(3).moveDown();
                squares.get(3).moveRight();
                squares.get(1).moveUp();
                squares.get(1).moveLeft();
                 */
                break;
            case 1:
                for (Square square : squares) {
                    System.out.println(square.getCoordX() + " " + square.getCoordY());
                    double x = square.getCoordX() * Math.cos(Math.PI/2) - square.getCoordY() * Math.sin(Math.PI/2);
                    double y = square.getCoordX() * Math.sin(Math.PI/2) + square.getCoordY() * Math.cos(Math.PI/2);
                    x *= -1;
                    System.out.println(x + " " + y);
                    square.move((int)x, (int)y);
                }
            /*
                squares.get(1).moveDown();
                squares.get(1).moveRight();
                 */
                break;
            case 2:
                if (checkCollisionWall() == 0) {
                    for (Square square : squares) {
                        square.moveRight();
                    }
                }
                /*
                squares.get(0).moveDown();
                squares.get(0).moveLeft();
                 */
                break;
            case 3:
            /*
                squares.get(3).moveUp();
                squares.get(3).moveLeft();
                 */
                break;
        }
    }

    @Override
    public void resetShape() {
        rotatePosition = 0;
        squares.get(0).move(startX, startY);
        squares.get(1).move(startX-UNIT_SIZE, startY+UNIT_SIZE);
        squares.get(2).move(startX, startY+UNIT_SIZE);
        squares.get(3).move(startX+UNIT_SIZE, startY+UNIT_SIZE);
    }
}
