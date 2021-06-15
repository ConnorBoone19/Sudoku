package BoardCreater;

import static BoardCreater.FileToString.fileToString;

public class FileToBoard {
    public static int[][] defaultBoard = {
            { 3, 0, 6, 5, 0, 8, 4, 0, 0 },
            { 5, 2, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 8, 7, 0, 0, 0, 0, 3, 1 },
            { 0, 0, 3, 0, 1, 0, 0, 8, 0 },
            { 9, 0, 0, 8, 6, 3, 0, 0, 5 },
            { 0, 5, 0, 0, 9, 0, 6, 0, 0 },
            { 1, 3, 0, 0, 0, 0, 2, 5, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 7, 4 },
            { 0, 0, 5, 2, 0, 6, 3, 0, 0 } };;

    public static int[][] loadedBoard;

    public static void stringToBoard(String file) {
        loadedBoard = defaultBoard;
        String boardString = fileToString(file);
        int location = 0;
        if (boardString == null) {
            System.out.println("Board string is null, exiting");
            System.exit(77);
        } else {
            for (int row = 0; row < defaultBoard.length; row++) {
                for (int column = 0; column < defaultBoard[0].length; column++) {
                    char ch = boardString.charAt(location);
                    loadedBoard[row][column] = ch;
                }
            }

            if (defaultBoard!=loadedBoard){
                System.out.println("Error, boards do not match");
                System.exit(22);
            }else{
                System.out.println("Boards match");
            }
        }
    }

    public static void main(String[] args) {
        stringToBoard("/Users/Connor/Desktop/Coding/JavaProjects/Sudoku/src/9x9Boards/234.txt");
    }


}
