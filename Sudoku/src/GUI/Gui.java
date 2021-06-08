package GUI;

import Game.Sudoku;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Arrays;
import java.util.List;

public class Gui {

    public static JFrame frame;
    public static JPanel panel;
    public static JPanel subGrid;
    public static JTextArea text;
    public static int[][] board;
    public static int[][] correctBoard;
    public static boolean[][] boolBoard;
    public static int dimensions;
    public static boolean isCorrect = true;



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

        if (correct){
            matrix[row][column] = true;
        }else if (!correct){
            matrix[row][column] = false;
        } else{
            System.out.println("Error with update bool");
        }
    }

    public static boolean gameOver(int row, boolean[][] matrix){

        if(row >= matrix.length){
            // TODO add a end game gui, to replace the return true part below
            return true;
        }
        List<boolean[]> rowValues = Arrays.asList(Arrays.asList(matrix).get(row));
        if(rowValues.contains(false)){
            return false;
        }else{
            return gameOver((row+1),matrix);
        }
    }


    public static void createGui(){
        frame = new JFrame("Hi");
        panel = new JPanel(new GridLayout(9,9));
        panel.setSize(200,200);
        frame.setSize(300,300);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.add(panel);
        panel.setBackground(Color.BLUE);



        // builds the grid
        for (int i = 0; i<=(dimensions-1); i++){
            for (int j = 0; j<=(dimensions-1); j++) {
                subGrid = new JPanel();
                subGrid.setSize(20, 20);
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

                                    System.out.println("The Correct value is " + correctBoard[finalRow][finalColumn]);
                                    System.out.println("Correct");

                                    // updates location in bool board to be true
                                    updateBool(finalRow,finalColumn,boolBoard,true);
                                    gameOver(0, boolBoard);

                                } else{

                                    System.out.println("The Correct value is " + correctBoard[finalRow][finalColumn]);
                                    System.out.println("Wrong");

                                    // updates location in bool board to be false
                                    updateBool(finalRow,finalColumn,boolBoard,false);
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
        return board[row][column] == 0;
    }


    public static void getBoard(){

        // Gets board from the get board function
        Sudoku.createBoard();
        board = Sudoku.board;
    }

    public static void main(String[] args) {
        setUp();
        //createGui();

    }
}
