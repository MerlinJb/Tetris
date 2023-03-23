package Blocks;

import Objects.ShapeModel;
import Objects.Square;
import static Main.GamePanel.UNIT_SIZE;

public class OShape extends ShapeModel {

    public OShape(boolean active, int startX, int startY) {
        super(active);
        this.startX = startX;
        this.startY = startY;
        addSquares();
    }
    public void addSquares() {
        squares.add(new Square(2, startX, startY));
        squares.add(new Square(2, startX+UNIT_SIZE, startY));
        squares.add(new Square(2, startX, startY+UNIT_SIZE));
        squares.add(new Square( 2, startX+UNIT_SIZE, startY+UNIT_SIZE));
    }

    @Override
    public void rotate() {
        System.out.println("rotate");
    }

    @Override
    public void resetShape() {
        rotatePosition = 0;
        squares.get(0).move(startX, startY);
        squares.get(1).move(startX+UNIT_SIZE, startY);
        squares.get(2).move(startX, startY+UNIT_SIZE);
        squares.get(3).move(startX+UNIT_SIZE, startY+UNIT_SIZE);
    }
}
