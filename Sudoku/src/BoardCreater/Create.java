package BoardCreater;

import java.io.File;

public class Create {
    public static void createAndPopulate(String name){
        File location = CreateBoard.newBoard(name);
        if (location != null) {
            PopulateFile.writeToFile(location);
        }else{
            System.out.println("File name is null");
        }
    }

}
