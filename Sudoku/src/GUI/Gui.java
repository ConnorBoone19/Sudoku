package GUI;

import Game.Sudoku;

import javax.swing.*;
import java.awt.*;

public class Gui {

    public static JFrame frame;
    public static JPanel panel;
    public static JPanel subGrid;
    public static JTextArea text;
    public static int[][] board;
    public static boolean[][] boolBoard;
    public static int dimensions;


    public static void setUpGUI(){
        getBoard();
        createBoolBoard();
        dimensions = board.length;
    }

    public static void createBoolBoard(){
        // board for if the text box can be edited
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
    }

    public static void createGui(){
        frame = new JFrame("Hi");
        panel = new JPanel(new GridLayout(9,9));
        panel.setSize(200,200);
        frame.setSize(300,300);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.add(panel);
        panel.setBackground(Color.BLUE);

        for (int i = 1; i<9; i++){
            for (int j = 1; j<9; j++) {
                subGrid = new JPanel();
                subGrid.setSize(20, 20);
                subGrid.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                subGrid.setName("Grid"+i+j);
                panel.add(subGrid);


                // gets the number at the location of the board matrix and places it in the textarea
                text = new JTextArea(String.valueOf(board[(i-1)][(j-1)]));


                // if the text box is a 0 then the user can edit it,
                // otherwise it was a pre-existing number and cannot be edited
                text.setEditable(canEdit((i-1),(j-1)));

                subGrid.add(text);
            }
        }
        frame.setVisible(true);
    }

    public static boolean canEdit(int row, int column){
        if (board[row][column] == 0){
            return true;
        }else {
            return false;
        }
    }

    public static void showBool(boolean[][] matrix){
        if (matrix.length == matrix[1].length) {
            for (boolean[] ints : matrix) {
                for (boolean anInt : ints) {
                    System.out.print(anInt + "\t");
                }
                // new line
                System.out.println();
            }
            System.out.println("Number of Rows: " + matrix.length);
            System.out.println("Number of Columns: " + matrix[1].length);
        }
    }

    public static void getBoard(){
        // Gets board from the get board function
        Sudoku.createBoard();
        board = Sudoku.board;
    }

    public static void main(String[] args) {
        setUpGUI();
        createGui();
    }
}
