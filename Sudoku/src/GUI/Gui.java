package GUI;

import Game.Difficulty;
import Game.Sudoku;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;


public class Gui {

    public static JFrame frame;
    public static JPanel panel;
    public static JPanel panel2;
    public static JPanel mainPanel;
    public static JTextField textField;
    public static JButton hintButton;
    public static JPanel[][] subGrid ;
    public static JTextArea[][] text;
    public static int[][] board;
    public static int[][] correctBoard;
    public static boolean[][] boolBoard;
    public static boolean[][] boolHints;
    public static int dimensions;
    public static int level = -1;
    public static int hintAmount = 0;
    public static String difficultyLevel = "Easy";





    public static void setUp(String difficultyLevel){
        new Difficulty(difficultyLevel);
        System.out.println(level);
        if (!((level >=0) && (level<=3))){
            System.out.println("Error with difficulty setter, level is supposed to be 1,2 or 3 but has a value of: " + level);
            System.exit(99);
        }
        correctBoard = Sudoku.getCorrectBoard();
        System.out.println();
        getBoard();
        createBoolBoards();
        dimensions = board.length;
    }

    public static void createBoolBoards(){
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

        // for hint board

        boolHints = new boolean[][]{
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

                // checks to see if the value each position on the board is a non-zero,
                // this allows the game to know that they are correct values

                if (board[a][k] != 0) {
                    boolBoard[a][k] = true;
                }
            }
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
                // and then returns false instead of continuing searching for more false values
                // in order to run more efficiently
                return false;
            }
        }

        // If it has finished checking the whole row, it then returns the same function but for the next row
        return gameOver((row+1),matrix);
    }



    public static void createGui(){

        // Adding frame
        frame = new JFrame("Sudoku");
        frame.setSize(400,500);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // Main panel where lower panel (panel2) and the board panel (panel) will be added
        mainPanel = new JPanel();
        mainPanel.setLayout(null);

        // Adding the upper, board panel
        panel = new JPanel(new GridLayout(dimensions,dimensions));
        panel.setSize(400,400);

        // For the lower panel
        panel2 = new JPanel();
        panel2.setBounds(0,400,400,50);
        panel2.setLayout(new BorderLayout());

        // Adds the hint ability

        textField = new JTextField("");
        textField.setEditable(false);
        hintButton = new JButton("Hint");
        hintButton.addActionListener(e -> {
            if (hintAmount > 0) {
                // If user has hints remaining it will show the user a correct number along with placement
                int[] location = hint();
                int row = location[0];
                int column = location[1] ;
                int number = location[2];
                subGrid[row][column].setBackground(Color.PINK);
                textField.setText("The value in row " + (row + 1) + " and column " + (column + 1) + " is " + number);
                hintAmount = hintAmount - 1;
            }else if (hintAmount == 0){
                textField.setText("You have no more hints remaining!");
            } else {
                System.out.println("Exiting with error");
                System.out.println("Error in hintButton action listener if statements, hintAmount = "+ hintAmount);
                System.exit(77);
            }
        });


        hintButton.setSize(50, 50);
        textField.setSize(100, 50);
        panel2.add(textField, BorderLayout.CENTER);
        panel2.add(hintButton, BorderLayout.EAST);

        // Adding both panels to the main panel
        mainPanel.add(panel);
        mainPanel.add(panel2);

        // adding the main panel to the frame
        frame.add(mainPanel);



        // Menu bar
        new Menu(frame);

        // builds the grid
        text = new JTextArea[dimensions][dimensions];
        subGrid = new JPanel[dimensions][dimensions];

        for (int i = 0; i<=(dimensions-1); i++){
            for (int j = 0; j<=(dimensions-1); j++) {

                subGrid[i][j] = new JPanel();
                text[i][j] = new JTextArea();

                text[i][j].setText(String.valueOf(board[(i)][(j)]));

                // if the text box is a 0 then the user can edit it,
                // otherwise it was a pre-existing number and cannot be edited

                text[i][j].setEditable(canEdit((i),(j)));
                text[i][j].setLayout(new BorderLayout());
                subGrid[i][j].add(text[i][j],BorderLayout.CENTER);

                subGrid[i][j].setSize(50, 50);
                subGrid[i][j].setBorder(BorderFactory.createLineBorder(Color.BLACK));



                // gets the number at the location of the board matrix and places it in the textarea
                panel.add(subGrid[i][j]);

                int finalRow = i;
                int finalColumn = j;


                // Adds a key listener event
                text[i][j].addKeyListener(new KeyListener() {
                    public void keyPressed(KeyEvent event) {
                    }

                    public void keyReleased(KeyEvent event) {
                    }
                    @Override
                    public void keyTyped(KeyEvent event) {

                        try {
                            int intValue = Integer.parseInt(String.valueOf(event.getKeyChar()));
                            if (intValue <= 9 && intValue >= 1) {
                                System.out.println("The key clicked is: " + event.getKeyChar());
                                System.out.println("Column clicked: " + finalRow);
                                System.out.println("Row clicked: " + finalColumn);
                                // Checks against the already solved board to see if its correct
                                if (correctBoard[(finalRow)][(finalColumn)] == intValue){
                                    if (level == 0) {
                                        subGrid[finalRow][finalColumn].setBackground(Color.GREEN);
                                    }

                                    System.out.println("The Correct value is: " + correctBoard[finalRow][finalColumn]);

                                    // updates location in bool board to be true
                                    updateBool(finalRow, finalColumn, boolBoard, true);
                                    if (gameOver(0,boolBoard)){

                                        textField.setText("Game over");
                                    }

                                } else{

                                    if (level == 0) {
                                        subGrid[finalRow][finalColumn].setBackground(Color.RED);
                                    }

                                    System.out.println("The Correct value is: " + correctBoard[finalRow][finalColumn]);

                                    // updates location in bool board to be false
                                    updateBool(finalRow, finalColumn, boolBoard, false);
                                }
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
        if (board[row][column] == 0){
            return true;
        }else if (board[row][column] != 0){
            boolBoard[row][column] = true;
            return false;

        } else {
            System.out.println("Exiting with error");
            System.out.println("Error in the can edit function, error in Row: " + row + " Column: " + column);
            System.out.println("The value that was trying to be returned is: " + board[row][column]);
            System.exit(44);
            return false;
        }
    }

    public static void getBoard(){

        // Gets board from the get board function
        Sudoku.createBoard();
        board = Sudoku.board;
    }

    public static int[] hint(){
        int[] location = randGen();
        int row = location[0];
        int column = location[1];
        if ((boolBoard[row][column]) || (boolHints[row][column])){

            // if the location returns true, the user has already inputted a value,
            // also checks for the rare occurrence that it is trying to give the,
            // user a hint from a location that it has already hinted
            // and the code reruns the hint function until it returns false

            return hint();

        }else if (!boolBoard[row][column] && (!boolHints[row][column])){
            // Bool board must be false along with hints as that means the system hasn't hinted that location yet,
            // as well as the user hasn't entered that location correctly either
            int number = correctBoard[row][column];
            location[2] = number;
            boolHints[row][column] = true;
            return location;

        }else{
            // Error checker, gives the values of row, column, and boolBoard
            // at that location before exiting to make debugging easier
            System.out.println("Error with hint");
            System.out.println("Random row: " + row);
            System.out.println("Random column: " + column);
            System.out.println("Value of boolBoard[row][column] is: " + boolBoard[row][column]);
            System.exit(3);
            return null;
        }

    }

    private static int[] randGen() {

        // finds a random row and column to give to the user if they request a hint
        Random randR = new Random();
        Random randC = new Random();

        // Upper bound is the dimensions
        int upperbound = (dimensions - 1);
        int randomRow = randR.nextInt(upperbound);
        int randomColumn = randC.nextInt(upperbound);
        int[] location = new int[3];
        location[0] = randomRow;
        location[1] = randomColumn;
        return location;
    }

    public static void startGame(String difficulty){
        setUp(difficulty);
        createGui();
    }

    public static void main(String[] args) {
        // for testing solely the GUI
        setUp("Easy");
        createGui();

    }
}
