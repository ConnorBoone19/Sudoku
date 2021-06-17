package NewBoard;

import Game.Sudoku;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class newBoard {
    public static JFrame frame;
    public static JPanel panel;
    public static JPanel lowerPanel;
    public static JPanel mainPanel;
    public static JTextField textField;
    public static JTextField fileName;
    public static JTextArea validOrNot;
    public static JPanel[][] subGrid ;
    public static JTextArea[][] text;
    public static JButton check;
    public static int dimensions;
    public static int[][] board2;
    public static int clicks = 0;
    public static boolean firstClick = true;


// TODO implement feature for user to add their own board

    public static void setUpGui(){
        board2 = new int[][]{
                { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0, 0 },};

                dimensions = board2.length;
    }

    public static void createNewBoardGui(){

        frame = new JFrame("New GUI");
        frame.setSize(400,500);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        mainPanel = new JPanel();
        mainPanel.setLayout(null);

        panel = new JPanel(new GridLayout(dimensions,dimensions));
        panel.setSize(400,400);

        lowerPanel = new JPanel();
        lowerPanel.setBounds(0,400,400,50);
        lowerPanel.setLayout(new FlowLayout());

        textField = new JTextField("");
        textField.setEditable(false);


        fileName = new JTextField(20);
        fileName.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Clears the text when user clicks in the field for the first time

                if (firstClick) {
                    fileName.setText("");
                }
                firstClick = false;
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });


        fileName.setText("Please enter your desired file name");
        lowerPanel.add(fileName);

        validOrNot = new JTextArea("hello");
        validOrNot.setSize(100,50);
        lowerPanel.add(validOrNot);

        check = new JButton("Check");


        check.addActionListener(e -> {
            if (Sudoku.solve(board2,0,0)){
                validOrNot.setText("Valid");
                clicks = clicks + 1;
            }else if (!Sudoku.solve(board2,0,0)){
                validOrNot.setText("Invalid");
                clicks = 0;
                NBMenu.menu2.setVisible(false);
                return;
            }
            if (clicks == 2){
                NBMenu.menu2.setVisible(true);
            }
        });

        lowerPanel.add(check);


        new NBMenu(frame);

        mainPanel.add(panel);
        mainPanel.add(lowerPanel);

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
                            if (intValue <= 9 && intValue >= 0) {
                                board2[finalRow][finalColumn] = intValue;
                                // Sudoku.showBoard(board2);
                                // System.out.println("Added " + intValue + " to board");
                            }
                        }catch (NumberFormatException e){
                            System.out.println("Not a number");
                        }

                        // Hides the user's ability to save the board,
                        // if this was not in place the user could enter a valid board,
                        // and then edit it to be invalid and still save the board
                        boardEdited();

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
    public static void boardEdited(){
        NBMenu.menu2.setVisible(false);
        clicks = 0;
    }
    public static void create(){
        setUpGui();
        createNewBoardGui();
    }
    public static void main(String[] args) {
        create();
    }
}
