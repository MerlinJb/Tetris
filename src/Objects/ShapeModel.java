package Objects;

import Main.GamePanel;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import static Main.GamePanel.UNIT_SIZE;
import static Main.GamePanel.getRows;

public abstract class ShapeModel {

    protected ArrayList<Square> squares;
    protected boolean active;
    protected int rotatePosition;
    protected int startX, startY;

    public ShapeModel(boolean hexagon){
        active = hexagon;
        squares = new ArrayList<>();
        rotatePosition = 0;
    }

    public void moveDown(ArrayList<Square> squares) {
        //System.out.println("moved down");
        while (!checkCollisionGround(squares, GamePanel.getRows())) {
            for (Square square : squares) {
                square.moveDown();
            }
        }
        //active = false;
        GamePanel.addToRow(squares);
    }

    public int checkCollisionWall(ArrayList<Square> squares) {
        for (Square square : squares) {
            if (square.checkWallCollision() == 0) {
                return 0;
            }
            else if (square.checkWallCollision() == 1) {
                return 1;
            }
        }
        return -1;
    }

    public boolean checkCollisionGround(ArrayList<Square> squares, ArrayList<Row> rows) {
        for (Square square : squares) {
            if (square.checkGroundCollision(square.getCoordY())) {
                return true;
            }
            for (Row row : rows) {
                for (Square square2 : row.getSquares()) {
                    if (square.checkRowCollision(square.getCoordX(), square.getCoordY() + UNIT_SIZE,
                        new Rectangle(square2.getCoordX(), square2.getCoordY(), UNIT_SIZE, UNIT_SIZE))) {
                            return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean checkCollisionRow(ArrayList<Square> squares, ArrayList<Row> rows, int direction) {
        for (Square square : squares) {
            for (Row row : rows) {
                for (Square square2 : row.getSquares()) {
                    if (square.checkRowCollision((square.getCoordX() + UNIT_SIZE*direction), square.getCoordY(),
                            new Rectangle(square2.getCoordX(), square2.getCoordY(), UNIT_SIZE, UNIT_SIZE))) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public void draw(Graphics g, ArrayList<Square> squares) {
        for (Square square : squares) {
            square.draw(g);
        }
    }

    public boolean move(ArrayList<Square> squares, boolean bool, ArrayList<Row> rows) {
        if (bool) {
            for (Square square : squares) {
                if (square.checkGroundCollision()) {
                    return true;
                }
                for (Row row : rows) {
                    for (Square square2 : row.getSquares()) {
                        if (new Rectangle(square.getCoordX(), (square.getCoordY()+UNIT_SIZE), UNIT_SIZE, UNIT_SIZE)
                                .intersects(square2.getCoordX(), square2.getCoordY(), UNIT_SIZE, UNIT_SIZE)) {
                            return true;
                        }
                    }
                }
            }
        }

        if (active){
            for (Square square : squares) {
                square.moveDown();
            }
        }
        return false;
    }

    public void keyPressed(KeyEvent e, ArrayList<Square> squares, ArrayList<Row> rows) {
        switch(e.getKeyCode()) {
            case KeyEvent.VK_R: case KeyEvent.VK_UP:
                rotate();
                break;
            case KeyEvent.VK_DOWN:
                if (!checkCollisionGround(squares, getRows())) {
                    for (Square square : squares) {
                        square.moveDown();
                    }
                }   break;
            case KeyEvent.VK_LEFT:
                if (checkCollisionWall(squares) != 0 && checkCollisionRow(squares, rows, -1) == false) {
                    for (Square square : squares) {
                        square.moveLeft();
                    }
                }  break;
            case KeyEvent.VK_RIGHT:
                if (checkCollisionWall(squares) != 1 && !checkCollisionRow(squares, rows, 1)) {
                    for (Square square : squares) {
                        square.moveRight();
                    }
                }   break;
            case KeyEvent.VK_H:
                //TODO HOLDING FEATURE.
                break;
            case KeyEvent.VK_SPACE:
                //System.out.println("pressed space");
                if (GamePanel.getReady()) {
                    GamePanel.setReady(false);
                    //System.out.println("accepted typed space");
                    moveDown(squares);

                }
                break;
        }
    }




    public abstract void rotate();
    
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
