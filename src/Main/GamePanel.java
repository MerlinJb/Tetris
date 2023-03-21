package Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
import Objects.Row;
import Objects.Score;
import Objects.ShapeModel;
import Objects.Square;
import Blocks.*;

public class GamePanel extends JPanel implements ActionListener {

    public static Dimension window = new Dimension(420, 600);
    public static final int UNIT_SIZE = window.width/15;

    private static Random random = new Random();
    static Timer timer;
    private final int DELAY = 100;
    public int moving = 0;

    private Score score;
    private static boolean ready = false;

    public static final Point startPoint = new Point(UNIT_SIZE*2, UNIT_SIZE);
    public static final Point endPoint = new Point(UNIT_SIZE*12, UNIT_SIZE*20);

    public static final int shapeStartX = UNIT_SIZE*5;
    public static final int shapeStartY = 0;

    private static ArrayList<Row> rows = new ArrayList<>();

    private static TShape tShape;
    private static ZShape zShape;
    private static OShape oShape;
    private static IShape iShape;
    private static JShape jShape;
    private static LShape lShape;
    private static SShape sShape;

    private static ArrayList<Integer> nextShape = new ArrayList<>();

    private static ArrayList<ShapeModel> shapes = new ArrayList<>();

    GamePanel() {
        this.setPreferredSize(window);
        this.setFocusable(true);
        this.addKeyListener(new MyKeyAdapter());

        timer = new Timer(DELAY, this);
        score = new Score(new Point(0,0));
        timer.start();

        addShapes();
        addRows();
        addNextShapes();

    }

    public void addShapes() {
        tShape = new TShape(true, shapeStartX, shapeStartY);
        zShape = new ZShape(false, shapeStartX, shapeStartY);
        oShape = new OShape(false, shapeStartX, shapeStartY);
        iShape = new IShape(false, shapeStartX, shapeStartY);
        jShape = new JShape(false, shapeStartX, shapeStartY);
        lShape = new LShape(false, shapeStartX, shapeStartY);
        sShape = new SShape(false, shapeStartX, shapeStartY);
        shapes.add(tShape);
        shapes.add(zShape);
    }
    public void addRows() {
        for (int i = 0; i < 20; i++) {
            rows.add(new Row(new Rectangle(startPoint.x, startPoint.y+(i*UNIT_SIZE), UNIT_SIZE*10, UNIT_SIZE)));
        }
    }
    public void addNextShapes() {
        nextShape.add(4);
        for (int i = 0; i < 5; i++) {
            nextShape.add(nextShape());
            System.out.println(nextShape.get(i));
        }
    }
    public static int nextShape() {
        return random.nextInt(8+1);
    }
    public static void newShape() {
        if (!checkActives()) {
            ready = false;
            switch (nextShape.get(0)) {
                case 0: case 1: case 2: case 3: //TODO ÄNDRA ORDNINGEN PÅ BLOCKEN, SÅ ATT DEM ÄR I DEN RANGORDNINGEN.
                    tShape.resetShape();
                    tShape.setActive(true);
                    break;
                case 4: case 5: case 6: case 7: case 8:
                    zShape.resetShape();
                    zShape.setActive(true);
                    break;
                case 9: case 10: case 11: case 12: case 13:
                    oShape.resetShape();
                    oShape.setActive(true);
                    break;
                case 14: case 15: case 16: case 17: case 18:
                    iShape.resetShape();
                    iShape.setActive(true);
                    break;
                case 19: case 20: case 21: case 22: case 23:
                    jShape.resetShape();
                    jShape.setActive(true);
                    break;
                case 24: case 25: case 26: case 27:
                    lShape.resetShape();
                    lShape.setActive(true);
                    break;
                case 28: case 29: case 30:
                    sShape.resetShape();
                    sShape.setActive(true);
                    break;
            }
            nextShape.remove(0);
            nextShape.add(nextShape());
        }
    }

    public static boolean checkActives() {
        for(ShapeModel shape : shapes) {
            if (shape.getActive()) {
                return true;
            }
        }
        return false;
    }

    //TODO MIRRORING TECHNIC FÖR ATT SE VART BLOCKET KOMMER ATT LANDA NÅGONSTANS.
    //TODO NÄR MAN ROTERAR DEN LÅNGA FYRA BLOCKET NÄR MAN SÄTTER NER DEN SÅ KOMMER ROWEN ATT VARA MER ÄN TIO OCH KOMMERDÅ ATT FYLLA
    //ROWCOMPLETE

    @Override
    public void actionPerformed(ActionEvent e) {
        if (moving%10 == 0){
            if (tShape.getActive()) {
                if (tShape.move(tShape.getSquares(), tShape.getActive(), rows)) {
                    tShape.setActive(false);
                    addToRow(tShape.getSquares());
                }
            }
            else if (zShape.getActive()) {
               if (zShape.move(zShape.getSquares(), zShape.getActive(), rows)) {
                   zShape.setActive(false);
                   addToRow(zShape.getSquares());
               }
            }
            else if (oShape.getActive()) {
                if (oShape.move(oShape.getSquares(), oShape.getActive(), rows)) {
                    oShape.setActive(false);
                    addToRow(oShape.getSquares());
                }
            }
            else if (iShape.getActive()) {
                if (iShape.move(iShape.getSquares(), iShape.getActive(), rows)) {
                    iShape.setActive(false);
                    addToRow(iShape.getSquares());
                }
            }
            else if (jShape.getActive()) {
                if (jShape.move(jShape.getSquares(), jShape.getActive(), rows)) {
                    jShape.setActive(false);
                    addToRow(jShape.getSquares());
                }
            }
            else if (lShape.getActive()) {
                if (lShape.move(lShape.getSquares(), lShape.getActive(), rows)) {
                    lShape.setActive(false);
                    addToRow(lShape.getSquares());
                }
                //todo kan nog göra en funktion så jag inte behöver skriva den här sekvenser gång på gång
            }
            else if (sShape.getActive()) {
                if (sShape.move(sShape.getSquares(), sShape.getActive(), rows)) {
                    sShape.setActive(false);
                    addToRow(sShape.getSquares());
                }
            }

            checkForCompleteRow();

        }
        repaint();
        moving++;
    }

    private Row temp;
    private int numberOfRowsCompleted;
    public void checkForCompleteRow() {
        numberOfRowsCompleted = 0;
        for (int i = 0; i < rows.size(); i++) {
            if (rows.get(i).checkRow()) {
                numberOfRowsCompleted++;
                rows.get(i).clear();
                rows.get(i).setY(startPoint.y);

                for (int j = 0; j < i; j++) {
                    for (Square square : rows.get(j).getSquares()) {
                        square.moveDown();
                    }
                    rows.get(j).addY(UNIT_SIZE);
                }

                temp = rows.get(i);
                rows.remove(i);
                rows.add(0, temp);
            }

        }
        if (numberOfRowsCompleted >= 1) {
            for (int n = 1; n <= numberOfRowsCompleted; n++) {
                score.setScore(score.getScore() + n * 100);
            }
            System.out.println("score: " + score.getScore());
        }
    }
    public static void addToRow(ArrayList<Square> list) {
        for (Row row : rows) {
            for (Square square : list) {
                if (row.intersects(new Rectangle(square.getCoordX(), square.getCoordY(), UNIT_SIZE, UNIT_SIZE))) {
                    row.addToRow(square);
                }
            }
        }
        //ready = true;
        /*
        for (Row row : rows) {
            System.out.println(row.getSquares().size());
        } */

        newShape();

        //TODO här så nästa shape sätta igång.
        //TODO LISTA MED NÄSTA SHAPES.
        //TODO Funktion för att se när spelaren har förlorat.
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.BLACK);
        g.fillRect(startPoint.x, startPoint.y, endPoint.x-startPoint.x, endPoint.y);

        g.setColor(Color.WHITE);
        for(int i = 0; i < window.width/UNIT_SIZE; i++) {
            g.drawLine(i*UNIT_SIZE, 0, i*UNIT_SIZE, window.height);
        }
        for (int i = 0; i < window.height/UNIT_SIZE; i++) {
            g.drawLine(0, i*UNIT_SIZE, window.width, i*UNIT_SIZE);
        }

        for (Row row : rows) {
            row.draw(g);
        }

        if (tShape.getActive()) {
            tShape.draw(g, tShape.getSquares());
        }
        else if (zShape.getActive()){
            zShape.draw(g, zShape.getSquares());
        }
        else if (oShape.getActive()){
            oShape.draw(g, oShape.getSquares());
        }
        else if (iShape.getActive()){
            iShape.draw(g, iShape.getSquares());
        }
        else if (jShape.getActive()){
            jShape.draw(g, jShape.getSquares());
        }
        else if (lShape.getActive()){
            lShape.draw(g, lShape.getSquares());
        }
        else if (sShape.getActive()){
            sShape.draw(g, sShape.getSquares());
        }
    }
    public class MyKeyAdapter extends KeyAdapter {
        public void keyPressed(KeyEvent e) {

            for (ShapeModel shape : shapes) {
                System.out.println(shape.getActive());
                if (shape.getActive()) {
                    shape.keyPressed(e, shape.getSquares(), rows);
                }
            }

/*

            if (tShape.getActive()) {
                tShape.keyPressed(e);
                tShape.keyPressed(e, tShape.getSquares(), rows);
            }
            else if (zShape.getActive()) {
                zShape.keyPressed(e);
                zShape.keyPressed(e, zShape.getSquares(), rows);
            }
            else if (oShape.getActive()) {
                oShape.keyPressed(e);
                oShape.keyPressed(e, oShape.getSquares(), rows);
            }
            else if (iShape.getActive()) {
                iShape.keyPressed(e);
                iShape.keyPressed(e, iShape.getSquares(), rows);
            }
            else if (jShape.getActive()) {
                jShape.keyPressed(e);
                jShape.keyPressed(e, jShape.getSquares(), rows);
            }
            else if (lShape.getActive()) {
                lShape.keyPressed(e);
                lShape.keyPressed(e, lShape.getSquares(), rows);
            }
            else if (sShape.getActive()) {
                sShape.keyPressed(e);
                sShape.keyPressed(e, sShape.getSquares(), rows);
            }
            //rada upp alla olika shapes för att se vilken av dem som är igång. med else if
             */

        }
    }

    public static ArrayList<Row> getRows() {
        return rows;
    }
    public static void setReady(boolean bool) {
        ready = bool;
    }
}
