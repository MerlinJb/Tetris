package Panels;

import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.*;

import Objects.Player;
import Objects.QuickSort;


public class HighScorePanel extends JPanel implements ActionListener{

    private HashMap<String, Integer> scores = new HashMap<>();
    private ArrayList<Player> players = new ArrayList<>();
    private QuickSort quickSort;

    JLabel title = new JLabel("HIGHSCORELIST");
    JButton button1 = new JButton("New Game");

    //JLabel place1, place2, place3, place4, place5, place6, place7, place8, place9, place10;
    ArrayList<JLabel> labels = new ArrayList<>();

    public HighScorePanel() throws IOException {
        //super(false);
        this.add(title);
        //this.add(button1);
        this.setLayout(new GridLayout(11, 1, 0, 10));
        button1.addActionListener(this);

        quickSort = new QuickSort();

        loadingTheHighScoreFile();

        for (Player player : players) {
            labels.add(new JLabel(player.getName() + " " + player.getScore()));
        }

        addingTheLabels();


    }

    public void addingTheLabels() {
        for (int i = 0; i < players.size(); i++) {
            labels.get(i).setText(players.get(i).getName() + " " + players.get(i).getScore());
            this.add(labels.get(i));
        }
        
    }

    private boolean nextLine;
    public void loadingTheHighScoreFile() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("Highscorelist.txt"));
        System.out.println("in readingfile");

        nextLine = true;
        while (nextLine) {
            String fileString = reader.readLine();
            if (fileString != null) {
                //System.out.println(fileString);

                String name = fileString.substring(0, fileString.indexOf(' '));
                int score = Integer.valueOf(fileString.substring(fileString.lastIndexOf(' ')+1));

                //System.out.println(name + " " + num);

                players.add(new Player(name, score));
                //scores.put(name, num);
                System.out.println(players);
            }   
            else {
                nextLine = false;
                System.out.println("Stopped reading");
            }
        }
        System.out.println(scores);
        reader.close();
    }

    public void changingHighScoreList(String newName, int newScore) {
        players.add(new Player(newName, newScore));

        //quicksort algorithm do get the order of the array right in regards to the score of the players
        System.out.println(players);
        QuickSort.sort(players, 0, players.size()-1);
        System.out.println(players);

    }

    public void rewritingTheHighScoreFile() {

    }


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);



    }

    public void update() {
        //kommer väl inte att användas om actionperformed redan finns.? 
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == button1) {
            System.out.println("button1 clicked");
        }

    }

}
