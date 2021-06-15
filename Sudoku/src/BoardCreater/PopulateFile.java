package BoardCreater;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class PopulateFile {
    public static int[][] defaultBoard = {
            { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 0, 0 },};;
    public static int lengthCheck;

    public static void writeToFile(File location,String name){
        try {
            lengthCheck = 0;
            FileWriter fileToWrite = new FileWriter(location);

            for (int row = 0; row < defaultBoard.length; row++){
                for (int column = 0; column < defaultBoard[0].length; column++){
                    fileToWrite.write(String.valueOf(defaultBoard[row][column]));
                    lengthCheck++;
                }
            }
            if (lengthCheck != (defaultBoard.length * defaultBoard[0].length)){
                System.out.println("board length wrong");
            } else{
                System.out.println("Correct");
            }

            fileToWrite.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        String name = "234";
        CreateBoard.newBoard(name);
        writeToFile(new File(("/Users/Connor/Desktop/Coding/JavaProjects/Sudoku/src/9x9Boards/" + name+ ".txt")));
    }
}
