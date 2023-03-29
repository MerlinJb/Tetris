package Panels;

import javax.swing.*;
import java.awt.*;
//import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;
//import java.util.Random;
import Objects.PauseThread;
import Objects.Row;
import Objects.Score;
import Objects.Square;
import Templates.ShapeModel;
import Blocks.*;
import java.util.Timer;
import java.util.TimerTask;

public class GamePanel extends JPanel{  

    public static Dimension window = new Dimension(420, 600);
    public static final int UNIT_SIZE = window.width/15;

    
    private static Score score;

    private PauseThread pauseThread;
    private Thread t1;
    private static Timer timer;
    static boolean pauseGame = false;
    private final int DELAY = 100;
    public int moving = 0;

    public static final Point startPoint = new Point(UNIT_SIZE*2, UNIT_SIZE);
    public static final Point endPoint = new Point(UNIT_SIZE*12, UNIT_SIZE*20);
    public static final int shapeStartX = UNIT_SIZE*5;
    public static final int shapeStartY = 0;

    private static ArrayList<Row> rows = new ArrayList<>();
    private static ArrayList<Integer> nextShape = new ArrayList<>();
    static ArrayList<ShapeModel> shapes = new ArrayList<>();
    private static TShape tShape;
    private static ZShape zShape;
    private static OShape oShape;
    private static IShape iShape;
    private static JShape jShape;
    private static LShape lShape;
    private static SShape sShape;    

    private static boolean ready = true;
    private static boolean playerAlive;
    private static boolean playerLost = false;

    public GamePanel() {
        score = new Score(new Point(0,0));
        playerAlive = false;

        addShapes();
        addRows();
        addNextShapes();

        t1 = new Thread(() -> {
            timer = new Timer();
            TimerTask task = new TimerTask() {
                @Override
                public void run() {
                    if (pauseGame) {                       
                        pauseThread = new PauseThread();
                        pauseThread.getThread().start();
                        try {
                            pauseThread.getThread().join();
                        } catch(InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    try {
                        update();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    repaint();

                }
            };
            timer.scheduleAtFixedRate(task, 0, DELAY); 
        });
        t1.start();
    }

    public void update() throws IOException {
        //System.out.println(moving);
        if (moving%10 == 0 && playerAlive)  {
            System.out.println("round");

            for (ShapeModel shape : shapes) {
                if (shape.move(rows)) { 
                    shape.setActive(false);
                    addToRow(shape.getSquares());
                    break;
                }
            }
            checkForCompleteRow();
        }
        if (playerLost) {
            playerLost();
        }
        //changes

        moving++;
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
        shapes.add(oShape);
        shapes.add(iShape);
        shapes.add(jShape);
        shapes.add(lShape);
        shapes.add(sShape);
    }
    public void addRows() {
        for (int i = 0; i < 20; i++) {
            rows.add(new Row(new Rectangle(startPoint.x, startPoint.y+(i*UNIT_SIZE), UNIT_SIZE*10, UNIT_SIZE)));
        }
    }
    public void addNextShapes() {
        //nextShape.add(4);
        for (int i = 0; i < 5; i++) {
            nextShape.add(nextShape());
            System.out.println(nextShape.get(i));
        }
    }
    public static int nextShape() {
        return 28; //random.nextInt(30+1);
    }
    public static void newShape() {
        for (ShapeModel shape : shapes) {
            if (shape.getActive()) {
                shape.setActive(false);
            }
        }
        
        //System.out.println(nextShape.get(0));
        switch (nextShape.get(0)) {
            case 0: case 1: case 2: case 3:
                sShape.resetShape();
                if (sShape.checkIfCanPlace(rows.get(0))) {
                    sShape.setActive(true);
                }else {
                    //playerLost();
                    playerLost = true;
                } 
                break;
            case 4: case 5: case 6: case 7: case 8:
                zShape.resetShape();
                if (zShape.checkIfCanPlace(rows.get(0))) {
                    zShape.setActive(true);
                }else {
                    //playerLost();
                }
                break;
            case 9: case 10: case 11: case 12: case 13:
                lShape.resetShape();
                if (lShape.checkIfCanPlace(rows.get(0))) {
                    lShape.setActive(true);
                }else {
                    //playerLost();
                } 
                break;
            case 14: case 15: case 16: case 17: case 18:
                jShape.resetShape();
                if (jShape.checkIfCanPlace(rows.get(0))) {
                    jShape.setActive(true);
                }else {
                    //playerLost();
                }
                break;
            case 19: case 20: case 21: case 22: case 23:       
                oShape.resetShape();
                if (oShape.checkIfCanPlace(rows.get(0))) {
                    oShape.setActive(true);
                }else {
                    //playerLost();
                }   
                break;
            case 24: case 25: case 26: case 27:
                iShape.resetShape();
                if (iShape.checkIfCanPlace(rows.get(0))) {
                    iShape.setActive(true);
                }else {
                    //playerLost();
                }
                break;
            case 28: case 29: case 30:
                tShape.resetShape();
                if (tShape.checkIfCanPlace(rows.get(0))){
                    tShape.setActive(true);
                }else {
                    //playerLost();
                    playerLost = true;
                    //playerAlive = false;
                }
                break;
        }
        nextShape.remove(0);
        nextShape.add(nextShape());
    }

    public void playerLost() throws IOException {
        playerAlive = false;
        System.out.println("player lost the game");
        System.out.println("the score is " + score.getScore());


        String name = JOptionPane.showInputDialog(this, "Enter username:");

        //System.out.println(name);

        playerLost = false;

        MainPanel.highScorePanel.changingHighScoreList(name, score.getScore());

        //Todo restarting the game or going back to menupanel


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
        newShape();
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

        for (ShapeModel shape : shapes) {
            if (shape.getActive()) {
                shape.draw(g);
                break;
            }
        }
    }

    public void resumeGame() {
        System.out.println("resuming");
        assert pauseThread.isRunning();
        pauseThread.getThread().interrupt();
        moving = 1;
    }

    public static ArrayList<Row> getRows() {
        return rows;
    }
    public static boolean getReady() {
        return ready;
    }
    public static void setReady(boolean bool) {
        ready = bool;
    }
    public void setPlayerAlive(boolean bool) {
        playerAlive = bool;
    }
    public boolean getPlayerAlive() {
        return playerAlive;
    }
}