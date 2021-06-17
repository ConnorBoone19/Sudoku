package BoardCreater;


import java.io.File;
import java.io.IOException;

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



}
