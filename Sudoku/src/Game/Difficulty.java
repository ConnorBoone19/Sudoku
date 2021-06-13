package Game;

import GUI.Gui;

public class Difficulty {
    public Difficulty(String level){
        System.out.println(level);

        switch (level) {
            case "Easy" -> Gui.level = 0;
            case "Medium" -> Gui.level = 1;
            case "Hard" -> Gui.level = 2;
            default -> System.out.println("Level not entered");
        }
        switch (level){
            case "Easy" -> Gui.hintAmount = 5;
            case "Medium" -> Gui.hintAmount = 3;
            case "Hard" -> Gui.hintAmount = 1;
        }
    }

}
