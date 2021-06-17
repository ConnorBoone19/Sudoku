package Game;

import java.lang.*;

public class Repeat {
    public static int count = 0;
    public static boolean rowsValid = false;
    public static boolean columnsValid = false;
    public static boolean gridsValid = false;


    public static boolean checkIfBoardValid(int[][] matrix){
        rowsValid = checkIfValidRow(0,matrix);
        System.out.println("Rows returns: " + rowsValid);
        if (!rowsValid) return false;

        columnsValid = checkIfValidColumn(0,matrix);
        System.out.println("Columns returns: " + columnsValid);
        if (!columnsValid) return false;

        gridsValid = checkIfValidGrid(matrix,0,0);
        System.out.println("Grids returns: " + gridsValid);
        return columnsValid;

    }
    public static boolean checkIfValidRow(int row, int[][] matrix) {
        // If function has reached the end of all the rows,
        // and has not found a duplicate, it returns true

        if (row >= matrix.length){
            return true;
        }

        // Checks each row for if there are any duplicate numbers

        StringBuilder rowNumList = new StringBuilder();
        for (int i = 0; i <= (matrix[0].length - 1); i++){
            String num = String.valueOf(matrix[row][i]);
            // Checks to see if the number at matrix[row][i],
            // is found in the values already known to be in that row
            if (!num.equals("0")){
                // 0 is the placeholder value for a blank space,
                // if the function sees a 0 it skips over it
                if (rowNumList.toString().contains(num)) {
                    return false;
                }
                // If that number at matrix[row][i],
                // is not a duplicate the for loop adds it to the list
                rowNumList.append(matrix[row][i]);
            }

        }
        // Reruns the function for the next row
        return checkIfValidRow((row + 1), matrix);

    }
    public static boolean checkIfValidColumn(int column, int[][] matrix){
        // If function has reached the end of all the columns,
        // and has not found a duplicate, it returns true

        if ((column >= matrix.length) || (column >= matrix[0].length)){
            return true;
        }

        // Checks each column for if there are any duplicate numbers

        StringBuilder columnNumList = new StringBuilder();
        for (int i = 0; i <= (matrix.length - 1); i++){
            String num = String.valueOf(matrix[i][column]);
            // Checks to see if the number at matrix[i][column],
            // is found in the values already known to be in that column
            if (!num.equals("0")){
                // 0 is the placeholder value for a blank space,
                // if the function sees a 0 it skips over it
                if (columnNumList.toString().contains(num)) {
                    return false;
                }
                // If that number at matrix[i][column],
                // is not a duplicate the for loop adds it to the list
                columnNumList.append(matrix[i][column]);
            }

        }
        // Reruns the function for the next column
        return checkIfValidColumn((column + 1), matrix);
    }

    public static boolean checkIfValidGrid(int[][] matrix, int row, int column){
        // If column is outside of the bounds of the array, the function moves over 3 rows,
        // to a new 3x3 grid
        if (column >= matrix.length){
            row = row +3;
            column = 0;
        }

        if (row >= matrix.length) {
            // checks to see if the function has reached the final row,
            // if it has there are no duplicates in any 3x3 grids and hence returns true
            return true;
        }

        // Checks each 3x3 grid for if there are any duplicate numbers
        int ROW = row - row % 3, COL = column - column % 3;
        StringBuilder gridNumList = new StringBuilder();
        for (int k = 0; k < 3; k++){
            for (int c = 0; c<3; c++){

                String num = String.valueOf(matrix[ROW+k][COL+c]);
                // Checks to see if the number at matrix[ROW+k][COL+c],
                // is found in the values already known to be in that 3x3 grid area
                if (!num.equals("0")){
                    // 0 is the placeholder value for a blank space,
                    // if the function sees a 0 it skips over it
                    if (gridNumList.toString().contains(num)) {
                        // If a duplicate is found in that 3x3 grid the board is not valid,
                        // false is returned
                        return false;
                    }
                    // If that number at matrix[ROW+k][COL+c],
                    // is not a duplicate the for loop adds it to the list
                    gridNumList.append(matrix[ROW + k][COL + c]);
                }
            }
        }

        count++;

        // Reruns program for next 3x3 grid
        return checkIfValidGrid(matrix,row,column+3);
    }

    public static void main(String[] args) {
        int[][] board = new int[][]{
                { 3, 1, 6, 5, 7, 8, 4, 9, 2 },
                { 5, 2, 9, 1, 3, 4, 7, 6, 8 },
                { 4, 8, 7, 6, 2, 9, 5, 3, 1 },
                { 2, 6, 3, 4, 1, 5, 9, 8, 7 },
                { 9, 7, 4, 8, 6, 3, 1, 2, 5 },
                { 8, 5, 1, 7, 9, 2, 6, 4, 3 },
                { 1, 3, 8, 9, 4, 7, 2, 5, 6 },
                { 6, 9, 2, 3, 5, 1, 8, 7, 4 },
                { 7, 4, 5, 2, 8, 6, 3, 1, 9 } };
        System.out.println(checkIfBoardValid(board));


    }
}
