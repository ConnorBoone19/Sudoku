package BoardCreater;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class CreateBoard {
    public static File FileName;
    public static String fileFolder;


    public static File newBoard(String name){
        fileFolder = "/Users/Connor/Desktop/Coding/JavaProjects/Sudoku/src/9x9Boards/";
        try {
            FileName = new File(fileFolder + name + ".txt");
            if (FileName.createNewFile()){
                System.out.println(FileName.getPath());
                return FileName;
            }else{
                System.out.println("Error with file creator");
                return null;
            }
        }catch (IOException e){
            System.out.println("Hello");
            return null;
        }


    }


    public static void main(String[] args) {
        // Add input features for asking how big array is and how difficult it is
        newBoard("Connor");
    }
}
