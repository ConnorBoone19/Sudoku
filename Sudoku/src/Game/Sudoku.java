package Game;

import GUI.Gui;

public class Sudoku {

    public static int[][] board;
    public static int[][] correctBoard;
    public static int dimensions;
    public static double familyDBL;
    public static int family;
    private static boolean testing = false;

    public static void createBoard(){
        board = new int[][]{
                { 3, 0, 6, 5, 0, 8, 4, 0, 0 },
                { 5, 2, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 8, 7, 0, 0, 0, 0, 3, 1 },
                { 0, 0, 3, 0, 1, 0, 0, 8, 0 },
                { 9, 0, 0, 8, 6, 3, 0, 0, 5 },
                { 0, 5, 0, 0, 9, 0, 6, 0, 0 },
                { 1, 3, 0, 0, 0, 0, 2, 5, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 7, 4 },
                { 0, 0, 5, 2, 0, 6, 3, 0, 0 } };
    }

    public static int[][] getCorrectBoard(){
        createBoard();
        solve(board,0,0);
        correctBoard = board;
        return correctBoard;
    }

    public static boolean setBoard(){
        createBoard();
        dimensions = board.length;

        // the 2x2,3x3,4x4 etc grouping of squares
        familyDBL = Math.sqrt(dimensions);
        family = (int) Math.sqrt(dimensions);
        testing("Set board");
        return family == familyDBL;
    }
    public static boolean solve(int[][] matrix, int row, int column){
        if (setBoard()) {
            System.out.println(solveBoard(matrix, row, column,false));

            if (solveBoard(matrix, row, column,false)) {
                board = matrix;
                showBoard(board);
                System.out.println("Solvable");
                return true;
            } else {
                System.out.println("Error, board cannot be solved");
                return false;
            }
        } else {
            System.out.print("Error the board size entered is invalid. Error 2: Board is not a perfect square ");
            return false;
        }
    }

    public static boolean solveBoard(int[][] boardToSolve, int row, int column, boolean steps){

            if (column == dimensions && row == (dimensions - 1)) {
                return true;
            }

            // if the algorithm reaches the end of the row it goes down to the next column
            // ie. if on a 5x5 board if the algorithm reaches row 1 of column 4 it will then go to row 2 column 0
            if (column == dimensions) {
                row++;
                column = 0;
            }

            // if the grid space contains a non 0 the algorithm re-runs itself for the next column

            if (boardToSolve[row][column] != 0) {
                return solveBoard(boardToSolve, row, (column + 1),steps);

            }

            for (int number = 1; number <= dimensions; number++) {
                if (isValid(boardToSolve, row, column, number)) {
                    boardToSolve[row][column] = number;

                    if (steps){
                        Gui.text[row][column].setText(String.valueOf(number));
                    }
                    if (solveBoard(boardToSolve, row, (column + 1),steps)) {
                        return true;
                    }
                }
                boardToSolve[row][column] = 0;

            }

            return false;


    }




    public static boolean isValid(int[][] matrix, int row, int column, int number){

        for (int i = 0; i <= (dimensions-1); i++){

            // checks to see if the number the algorithm wants to place down is already in that column
            if (matrix[row][i] == number){
                return false;
            }
        }

        // checks to see if the number the algorithm wants to place down is already in that row
        for (int j = 0; j <= (dimensions-1); j++){
            if (matrix[j][column] == number){
                return false;
            }
        }

        // checks to see if the number the algorithm wants to place down is in the same 3x3 area
        int ROW = row - row % family, COL = column - column % family;

        for (int k = 0; k < family; k++){
            for (int c = 0; c < family; c++){
                if (matrix[k+ROW][c+COL] == number){
                    return false;
                }
            }
        }
        return true;
    }



    public static void showBoard(int[][] matrix){
        if (matrix.length == matrix[1].length) {
            for (int[] ints : matrix) {
                for (int anInt : ints) {
                    testing("Show Board Inner");
                    System.out.print(anInt + "\t");
                }
                // new line
                System.out.println();
            }
        }
    }


    private static void testing(String area){
        // testing function to make sure code is passing through certain bug areas
        if (testing){
            System.out.println("Passed through " + area);
        }
    }
    public static void main(String[] args) {
        // Set to true for debugging
        testing = false;

        // Set to true if wanting to see how well algorithm worked and where it needs improvement

        // Algorithm Start

        setBoard();
        solveBoard(board,0,0,false);
        System.out.println("The completed board is: \n");
        showBoard(board);



    }

}