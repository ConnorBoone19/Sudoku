package GUI;

import Game.Sudoku;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class Gui {

    public static JFrame frame;
    public static JPanel panel;
    public static JPanel subGrid;
    public static JPanel panel2;
    public static JPanel mainPanel;
    public static JTextArea text;
    public static JTextField textField;
    public static int[][] board;
    public static int[][] correctBoard;
    public static boolean[][] boolBoard;
    public static int dimensions;



    public static void setUp(){
        correctBoard = Sudoku.getCorrectBoard();
        System.out.println("Correct Board");
        Sudoku.showBoard(correctBoard);
        System.out.println();
        getBoard();
        createBoolBoard();
        dimensions = board.length;
    }

    public static void createBoolBoard(){
        // Bool Board is a function where if the value entered is correct, it becomes true

        boolBoard = new boolean[][]{
                { false, false, false, false, false, false, false, false, false },
                { false, false, false, false, false, false, false, false, false },
                { false, false, false, false, false, false, false, false, false },
                { false, false, false, false, false, false, false, false, false },
                { false, false, false, false, false, false, false, false, false },
                { false, false, false, false, false, false, false, false, false },
                { false, false, false, false, false, false, false, false, false },
                { false, false, false, false, false, false, false, false, false },
                { false, false, false, false, false, false, false, false, false }};

        // makes the value true for pre-positioned numbers
        for (int a = 0; a < (boolBoard.length); a++)
            for (int k = 0; k < (boolBoard[1].length); k++){
                if (board[a][k] != 0) {
                    boolBoard[a][k] = true;
                }
            }
        Sudoku.showBoolBoard(boolBoard);
    }

    public static void updateBool(int row, int column, boolean[][] matrix, boolean correct){

        // function for updating the bool board for each user input

        matrix[row][column] = correct;
    }

    public static boolean gameOver(int row, boolean[][] matrix){

        // checks to see if it has checked all of the rows for a false value, and then returns true that the game is over
        if(row >= matrix.length){
            return true;
        }
        for (int q = 0; q<dimensions;q++){
            if (!matrix[row][q]){
                // if it finds a false value then the game is not over
                return false;
            }
        }
        // Checks to see if there is a false value in the next row
        return gameOver((row+1),matrix);
    }


    public static void createGui(){
        frame = new JFrame("Hi");
        mainPanel = new JPanel(new GridLayout(0,1));
        panel = new JPanel(new GridLayout(dimensions,dimensions));
        panel2 = new JPanel();
        panel2.setMaximumSize(new Dimension(50,50));
        textField = new JTextField("My Name");
        textField.setSize(25,50);
        mainPanel.add(panel);
        mainPanel.add(textField);
        frame.setSize(500,500);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.add(mainPanel);

        new Menu(frame);




        // builds the grid
        for (int i = 0; i<=(dimensions-1); i++){
            for (int j = 0; j<=(dimensions-1); j++) {
                subGrid = new JPanel();
                subGrid.setSize(50, 50);
                subGrid.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                panel.add(subGrid);


                // gets the number at the location of the board matrix and places it in the textarea
                text = new JTextArea(String.valueOf(board[(i)][(j)]));


                // if the text box is a 0 then the user can edit it,
                // otherwise it was a pre-existing number and cannot be edited
                text.setEditable(canEdit((i),(j)));
                subGrid.add(text);

                int finalRow = i;
                int finalColumn = j;


                // Adds a key listener event
                text.addKeyListener(new KeyListener() {
                    public void keyPressed(KeyEvent event) {
                    }

                    public void keyReleased(KeyEvent event) {
                    }
                    @Override
                    public void keyTyped(KeyEvent event) {

                        try {
                            int intValue = Integer.parseInt(String.valueOf(event.getKeyChar()));
                            if (intValue <= 9 && intValue >= 1) {
                                System.out.println(event.getKeyChar());
                                System.out.println("Column clicked: " + finalRow);
                                System.out.println("Row clicked: " + finalColumn);

                                // Checks against the already solved board to see if its correct
                                if (correctBoard[(finalRow)][(finalColumn)] == intValue){

                                    System.out.println("Correct");
                                    System.out.println("The Correct value is: " + correctBoard[finalRow][finalColumn]);

                                    // updates location in bool board to be true
                                    updateBool(finalRow, finalColumn, boolBoard, true);
                                    if (gameOver(0,boolBoard)){
                                        text.setText("Game Over");
                                        System.out.println("Exit code: Game is over");
                                        System.exit(100);
                                    }

                                } else{

                                    System.out.println("Wrong");
                                    System.out.println("The Correct value is: " + correctBoard[finalRow][finalColumn]);

                                    // updates location in bool board to be false
                                    updateBool(finalRow, finalColumn, boolBoard, false);
                                }
                            }else {
                                System.out.println("Not working");
                            }

                        }catch (NumberFormatException e){
                            System.out.println("Not a number");
                        }


                    }
                });

            }
        }


        frame.setVisible(true);
    }

    public static boolean canEdit(int row, int column){
        
        // If the value in the board is 0 (the default value) the user can edit the box
        return board[row][column] == 0;
    }


    public static void getBoard(){

        // Gets board from the get board function
        Sudoku.createBoard();
        board = Sudoku.board;
    }

    public static void main(String[] args) {
        setUp();
        createGui();

    }
}
