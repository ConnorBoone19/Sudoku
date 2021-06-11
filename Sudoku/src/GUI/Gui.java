package GUI;

import Game.Sudoku;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;


public class Gui {

    public static JFrame frame;
    public static JPanel panel;
    public static JPanel subGrid;
    public static JPanel panel2;
    public static JPanel mainPanel;
    public static JTextArea text;
    public static JTextField textField;
    public static JButton hintButton;
    public static JButton solve;
    public static JTextArea correctOrWrong;
    public static int[][] board;
    public static int[][] correctBoard;
    public static boolean[][] boolBoard;
    public static int dimensions;
    public static int turns;
    public static boolean hint;
    public static boolean verification;


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
                // checks to see if the value each position on the board is a non-zero,
                // this allows the game to know that they are correct values

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
        frame = new JFrame("Hi");
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

        if (hint) {
            textField = new JTextField("");
            textField.setEditable(false);
            hintButton = new JButton("Hint");

            hintButton.addActionListener(e -> {
                int[] location = hint();
                int row = location[0] + 1;
                int column = location[1] + 1;
                int number = location[2];
                textField.setText("The value in row " + row + " and column " + column + " is " + number);
            });

            hintButton.setSize(50, 50);
            textField.setSize(100, 50);
            panel2.add(textField, BorderLayout.CENTER);
            panel2.add(hintButton, BorderLayout.EAST);
        }

        // Adds a correct or wrong text box for each time the user enters a value
        if (verification) {
            correctOrWrong = new JTextArea("");
            correctOrWrong.setAlignmentY(Component.CENTER_ALIGNMENT);
            correctOrWrong.setSize(50, 25);

            correctOrWrong.setEditable(false);
            panel2.add(correctOrWrong, BorderLayout.WEST);
        }

        // Adds a nice blue background if verification and hint are false
        if ((!verification) && (!hint)){
            panel2.setBackground(Color.BLUE);
        }
        // Adding both panels to the main panel
        mainPanel.add(panel);
        mainPanel.add(panel2);

        // adding the main panel to the frame
        frame.add(mainPanel);


        // Menu bar
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
                text.setLayout(new BorderLayout());
                subGrid.add(text,BorderLayout.CENTER);

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

                                    if (verification) {
                                        correctOrWrong.setText("Correct");
                                    }

                                    System.out.println("The Correct value is: " + correctBoard[finalRow][finalColumn]);

                                    // updates location in bool board to be true
                                    updateBool(finalRow, finalColumn, boolBoard, true);
                                    if (gameOver(0,boolBoard)){

                                        if (verification) {
                                            correctOrWrong.setText("Game Over");
                                        }

                                        System.out.println("Exit code: Game is over");
                                        System.exit(100);
                                    }

                                } else{

                                    if (verification) {
                                        correctOrWrong.setText("Wrong");
                                    }

                                    System.out.println("The Correct value is: " + correctBoard[finalRow][finalColumn]);

                                    // updates location in bool board to be false
                                    updateBool(finalRow, finalColumn, boolBoard, false);
                                }
                            }else {
                                System.out.println("Exiting with error");
                                System.out.println("gui.java in text.addKeyListener at Key Typed, " +
                                        "\n for some reason the try/catch function did not identify the value entered" +
                                        "\nas not a number, and then in the if statement did not pass through"+
                                        "\nThe value that was inputted was: " + intValue);
                                System.exit(6);
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

    public static int[] hint(){
        // TODO maybe add a counter to where the user only gets a certain amount of hints
        int[] location = randGen();
        int row = location[0];
        int column = location[1];
        if (boolBoard[row][column]){
            // if the location returns true, the user has already inputted a value,
            // and the code reruns the hint function until it returns false

            System.out.println("bool true, rerun");
            return hint();

        }else if (!boolBoard[row][column]){
            int number = correctBoard[row][column];
            location[2] = number;
            System.out.println("bool false");
            System.out.println(location[2]);
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
        System.out.println(randomRow);
        System.out.println(randomColumn);
        int[] location = new int[3];
        location[0] = randomRow;
        location[1] = randomColumn;
        return location;
    }

    public static int getTurns(){
        return turns;
    }
    public static void setTurns(int value){
        turns = value;
    }

    public static void main(String[] args) {
        hint = true;
        verification = true;
        setUp();
        createGui();
        hint();

    }
}
