package BoardCreater;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class PopulateFile {

    public static int lengthCheck;

    public static void writeToFile(File location, int[][] board){
        try {
            lengthCheck = 0;
            FileWriter fileToWrite = new FileWriter(location);

            for (int row = 0; row < board.length; row++){
                for (int column = 0; column < board[0].length; column++){
                    fileToWrite.write(String.valueOf(board[row][column]));
                    lengthCheck++;
                }
            }
            if (lengthCheck != (board.length * board[0].length)){
                System.out.println("Exiting, Board length wrong");
                System.exit(22);
            }


            fileToWrite.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
