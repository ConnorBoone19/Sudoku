package WelcomeScreen;

import GUI.Gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.TimeUnit;

public class MainMenu {
    public static JFrame frame;
    public static JPanel panel;
    public static JPanel buttonPanel;
    public static JPanel startPanel;
    public static JTextArea text1;
    public static JTextArea textConfirmButton;
    public static JButton help;
    public static JButton easy;
    public static JButton medium;
    public static JButton hard;
    public static JButton start;
    public static String difficultySelection;
    public static int accepted = 0;

    public static void mainMenuGUI(){

        frame = new JFrame("Welcome");
        frame.setSize(400,300);
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        panel = new JPanel();
        text1 = new JTextArea("""
                Welcome to my Sukoku game! \s
                Please select your difficult below,\s
                your options are \s
                Easy: 5 hints & correct placement verification \s
                Medium: 3 hints\s
                Hard: 1 hint,""",1,7);
        text1.setEditable(false);
        text1.setBackground(null);
        panel.add(text1,Component.CENTER_ALIGNMENT);


        frame.add(panel,BorderLayout.NORTH);

        // Buttons for difficulty

        buttonPanel = new JPanel();
        buttonPanel.setSize(400,50);
        buttonPanel.setLayout(null);


        // Easy difficulty button
        easy = new JButton("Easy");
        easy.setBounds(5,100,75,25);
        easy.addActionListener(e -> {
            difficultySelection = "Easy";
            textConfirmButton.setText("Easy Selected!");
            accepted = 1;
        });
        buttonPanel.add(easy);


        // Medium difficulty button
        medium = new JButton("Medium");
        medium.setBounds(85,100,75,25);
        medium.addActionListener(e -> {
            difficultySelection = "Medium";
            textConfirmButton.setText("Medium Selected!");
            accepted = 1;
        });
        buttonPanel.add(medium);


        // Hard difficulty button
        hard = new JButton("Hard");
        hard.setBounds(165,100,75,25);
        hard.addActionListener(e -> {
            difficultySelection = "Hard";
            textConfirmButton.setText("Hard Selected!");
            accepted = 1;
        });
        buttonPanel.add(hard);


        // Text area to let user confirm that they selected the correct difficulty
        textConfirmButton = new JTextArea("Select a difficulty");
        textConfirmButton.setBounds(245,100,150,25);
        textConfirmButton.setEditable(false);
        buttonPanel.add(textConfirmButton);

        frame.add(buttonPanel,BorderLayout.CENTER);


        // Start button
        startPanel = new JPanel();
        startPanel.setSize(400,100);
        start = new JButton("Start");
        startPanel.add(start);
        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (accepted == 1){
                    Gui.startGame(difficultySelection);
                }else{
                    textConfirmButton.setText("Please click a button");
                    textConfirmButton.setBackground(Color.red);

                }
            }
        });

        frame.add(startPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        mainMenuGUI();
    }
}
