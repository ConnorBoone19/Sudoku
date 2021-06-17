package Game;

import java.lang.*;

public class Repeat {
    public static void checkIfBoardValid(int[][] matrix){
        checkIfValidRow(0,matrix);
        checkIfValidColumn(0,matrix);

    }
    public static boolean checkIfValidRow(int row, int[][] matrix) {
        if ((row >= matrix.length) || (row >= matrix[0].length)){
            System.out.println("I reached the end of row");
            return true;
        }

        String rowNumList = "";
        for (int i = 0; i <= (matrix[0].length - 1); i++){
            String thing = String.valueOf(matrix[row][i]);
            if (rowNumList.contains(thing)) {
                System.out.println("Duplicate found in row " + row);
                return false;
            }
            rowNumList = rowNumList + matrix[row][i];
        }
        return checkIfValidRow((row + 1), matrix);

    }
    public static boolean checkIfValidColumn(int column, int[][] matrix){
        if ((column >= matrix.length) || (column >= matrix[0].length)){
            System.out.println("I reached the end of column");
            return true;
        }

        String columnNumList = "";
        for (int i = 0; i <= (matrix.length - 1); i++){
            String thing = String.valueOf(matrix[i][column]);
            if (columnNumList.contains(thing)) {
                System.out.println("Duplicate found in column " + column);
                return false;
            }
            columnNumList = columnNumList + matrix[i][column];
        }
        return checkIfValidColumn((column + 1), matrix);
    }

    public static void checkIfRulesFollowed(int[][] matrix, int row, int column){
        int ROW = row - row % 3, COL = column - column % 3;
        for (int k = 0; k < 3; k++){
            for (int c = 0; c<3; c++){
                // TODO create a function to find if values are in a certain area
            }
        }
    }

    public static void main(String[] args) {
        // Sudoku.setBoard();
        int[][] thing = new int[][]{
                { 3, 1, 6, 5, 7, 8, 4, 9, 2 },
                { 5, 2, 9, 1, 3, 4, 7, 6, 8 },
                { 4, 8, 7, 6, 2, 9, 5, 3, 1 },
                { 2, 6, 3, 4, 1, 5, 9, 8, 7 },
                { 9, 7, 4, 8, 6, 3, 1, 2, 5 },
                { 8, 5, 1, 7, 9, 2, 6, 4, 3 },
                { 1, 3, 8, 9, 4, 7, 2, 5, 6 },
                { 6, 9, 2, 3, 5, 1, 8, 7, 4 },
                { 7, 4, 5, 2, 8, 6, 3, 1, 9 } };
        checkIfBoardValid(thing);
    }
}
