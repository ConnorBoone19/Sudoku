package GUI;

import Game.Sudoku;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class newBoard {
    public static JFrame frame;
    public static JPanel panel;
    public static JPanel panel2;
    public static JPanel mainPanel;
    public static JTextField textField;
    public static JPanel[][] subGrid ;
    public static JTextArea[][] text;
    public static JButton check;
    public static int dimensions;
    public static int[][] board2;


// TODO implement feature for user to add their own board

    public static void setUpGui(){
        board2 = new int[][]{
                { 3, 0, 6, 5, 0, 8, 4, 0, 0 },
                { 5, 2, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 8, 7, 0, 0, 0, 0, 3, 1 },
                { 0, 0, 3, 0, 1, 0, 0, 8, 0 },
                { 9, 0, 0, 8, 6, 3, 0, 0, 5 },
                { 0, 5, 0, 0, 9, 0, 6, 0, 0 },
                { 1, 3, 0, 0, 0, 0, 2, 5, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 7, 4 },
                { 0, 0, 5, 2, 0, 6, 3, 0, 0 }};

                dimensions = board2.length;
                System.out.println(dimensions);
    }

    public static void createNewBoardGui(){

        frame = new JFrame("New GUI");
        frame.setSize(400,500);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        mainPanel = new JPanel();
        mainPanel.setLayout(null);

        panel = new JPanel(new GridLayout(dimensions,dimensions));
        panel.setSize(400,400);

        panel2 = new JPanel();
        panel2.setBounds(0,400,400,50);
        panel2.setLayout(new BorderLayout());

        textField = new JTextField("");
        textField.setEditable(false);

        check = new JButton("Check");
        check.addActionListener(e -> {
            if (Sudoku.solveBoard(board2,0,0,false)){
                System.out.println("valid");
            }else{
                System.out.println("Not valid");
            }
        });

        panel2.add(check);

        mainPanel.add(panel);
        mainPanel.add(panel2);

        frame.add(mainPanel);


        text = new JTextArea[dimensions][dimensions];
        subGrid = new JPanel[dimensions][dimensions];



        for (int i = 0; i<=(dimensions-1); i++){
            for (int j = 0; j<=(dimensions-1); j++) {

                subGrid[i][j] = new JPanel();
                text[i][j] = new JTextArea();

                text[i][j].setText(String.valueOf(board2[(i)][(j)]));

                // if the text box is a 0 then the user can edit it,
                // otherwise it was a pre-existing number and cannot be edited

                text[i][j].setLayout(new BorderLayout());
                subGrid[i][j].add(text[i][j],BorderLayout.CENTER);

                subGrid[i][j].setSize(50, 50);
                subGrid[i][j].setBorder(BorderFactory.createLineBorder(Color.BLACK));

                // gets the number at the location of the board matrix and places it in the textarea
                panel.add(subGrid[i][j]);
                int finalRow = i;
                int finalColumn = j;

                text[i][j].addKeyListener(new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent event) {
                        try {
                            int intValue = Integer.parseInt(String.valueOf(event.getKeyChar()));
                            if (intValue <= 9 && intValue >= 1) {
                                board2[finalRow][finalColumn] = intValue;
                                System.out.println("Added " + intValue + " to board");
                            }
                        }catch (NumberFormatException e){
                            System.out.println("Not a number");
                        }
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {

                    }

                    @Override
                    public void keyReleased(KeyEvent e) {

                    }

                });
            }
        }

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        setUpGui();
        createNewBoardGui();
    }
}
