package BoardCreater;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileToString {


    public static String fileToString(String file){
        try {
            File myFile = new File(file);
            Scanner fileToRead = new Scanner(myFile);
            return fileToRead.nextLine();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("Error in fileToString");
        System.exit(55);
        return null;
    }


}
