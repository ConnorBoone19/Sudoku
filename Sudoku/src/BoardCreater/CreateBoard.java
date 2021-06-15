package BoardCreater;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class CreateBoard {
    public static File FileName;
    public static String fileFolder;


    public static void newBoard(String name){
        fileFolder = "/Users/Connor/Desktop/Coding/JavaProjects/Sudoku/src/9x9Boards/";
        try {
            FileName = new File(fileFolder + name + ".txt");
            if (FileName.createNewFile()){
                System.out.println(FileName.getPath());
            }else{
                System.out.println("Error");
            }
        }catch (IOException e){
            System.out.println("Hello");
        }


    }


    public static void main(String[] args) {
        // Add input features for asking how big array is and how difficult it is
        newBoard("Connor");
    }
}
