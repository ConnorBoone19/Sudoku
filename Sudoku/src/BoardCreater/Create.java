package BoardCreater;

import java.io.File;

public class Create {
    public static void createAndPopulate(String name, int [][] matrix){
        File location = CreateBoard.newBoard(name);
        if (location != null) {
            PopulateFile.writeToFile(location, matrix);
        }else{
            System.out.println("File name is null");
        }
    }

}
