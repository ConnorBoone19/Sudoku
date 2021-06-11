package Game;

public class Sudoku {

    public static int[][] board;
    private static int[][] correctBoard;
    public static int dimensions;
    public static double familyDBL;
    public static int family;
    private static boolean showSteps = false;
    private static boolean testing = false;
    private static int turns = 0;

    // TODO CODE
        // TODO add a feature to change size of board
        // TODO add a feature to create a random board
        // TODO using algorithm, make multiple DIFFERENT boards
        // TODO add multiple difficulties
        // TODO add a variable size board (16x16, 4x4)

    // TODO GUI
        // TODO add feature to show steps of algorithm in GUI

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
    public static void solve(int[][] matrix, int row, int column){
        if (setBoard()) {
            //showBoard(matrix);
            System.out.println("\n");
            if (solveBoard(matrix, row, column,0)) {
                board = matrix;
                showBoard(board);
            } else {
                System.out.println("Error, board cannot be solved");
            }
        } else {
            System.out.print("Error the board size entered is invalid. Error 2: Board is not a perfect square ");
        }
    }

    public static boolean solveBoard(int[][] board, int row, int column, int steps){
        if (steps == 0) {
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

            if (board[row][column] != 0) {
                if (showSteps) {
                    showBoard(board);
                }
                turns++;
                return solveBoard(board, row, (column + 1), 0);

            }

            for (int number = 1; number <= dimensions; number++) {
                if (isValid(board, row, column, number)) {
                    board[row][column] = number;

                    if (solveBoard(board, row, (column + 1), 0)) {
                        return true;
                    }
                }
            }
            board[row][column] = 0;
            return false;

        }else if (steps == 1) {

            // This is for the hint part of the GUI (See GUI.MENU.menu menuItem1)
            if (column == dimensions) {
                row++;
                column = 0;
            }

            // if the grid space contains a non 0 the algorithm re-runs itself for the next column

            if (board[row][column] != 0) {
                if (showSteps) {
                    showBoard(board);
                }
                turns++;
                return solveBoard(board, row, (column + 1), 0);

            }
            return false;

        }else{
            System.out.println(steps);
            System.out.println("Error in solve function, steps does not equal 0 or 1");
            System.exit(1);
            return false;
        }
    }


    public static boolean isValid(int[][] board, int row, int column, int number){

        for (int i = 0; i <= (dimensions-1); i++){

            // checks to see if the number the algorithm wants to place down is already in that column
            if (board[row][i] == number){
                return false;
            }
        }

        // checks to see if the number the algorithm wants to place down is already in that row
        for (int j = 0; j <= (dimensions-1); j++){
            if (board[j][column] == number){
                return false;
            }
        }

        // checks to see if the number the algorithm wants to place down is in the same 3x3 area
        int ROW = row - row % family, COL = column - column % family;

        for (int k = 0; k < family; k++){
            for (int c = 0; c < family; c++){
                if (board[k+ROW][c+COL] == number){
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
            System.out.println("Number of Rows: " + matrix.length);
            System.out.println("Number of Columns: " + matrix[1].length);
        }
    }

    public static void showBoolBoard(boolean[][] matrix){
        if (matrix.length == matrix[1].length) {
            for (boolean[] booleans : matrix) {
                for (int j = 0; j < matrix[1].length; j++) {
                    testing("Show Board Inner");
                    System.out.print(booleans[j] + "\t");
                }
                // new line
                System.out.println();
            }
            System.out.println("Number of Rows: " + matrix.length);
            System.out.println("Number of Columns: " + matrix[1].length);
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
        showSteps = true;
        setBoard();
        solveBoard(board,0,0,0);


    }

}