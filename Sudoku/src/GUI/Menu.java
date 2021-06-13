package GUI;

import Game.Sudoku;
import WelcomeScreen.MainMenu;

import javax.swing.*;

public class Menu {
    public Menu(JFrame frame){
        JMenuBar menuBar = new JMenuBar();
        JMenu menu1 = new JMenu("Quit");
        JMenu menu2 = new JMenu("Options");
        JMenu menu3 = new JMenu("Solve");
        JMenu changeDifficultyItem = new JMenu("Change Difficulty");
        JMenuItem resetItem = new JMenuItem("Reset");
        JMenuItem menuItemQuit = new JMenuItem("Quit");
        JMenuItem returnToMainMenu = new JMenuItem("Main Menu");
        JMenuItem changeToHard = new JMenuItem("Hard");
        JMenuItem changeToMedium = new JMenuItem("Medium");
        JMenuItem changeToEasy = new JMenuItem("Easy");
        JMenuItem solveItem = new JMenuItem("Solve");


        // Quits the game
        menuItemQuit.addActionListener(e -> System.exit(2));
        menu1.add(menuItemQuit);

        //  Returns to menu
        returnToMainMenu.addActionListener(e -> {
            frame.setVisible(false);
            MainMenu.mainMenuGUI();
        });
        menu1.add(returnToMainMenu);

        // Solves the board
        solveItem.addActionListener(e -> Sudoku.solveBoard(Gui.board,0,0,true));
        menu3.add(solveItem);

        // resets the game
        resetItem.addActionListener(e -> {
            frame.setVisible(false);
            Gui.setUp(MainMenu.difficultySelection);
            Gui.createGui();
        });
        menu2.add(resetItem);

        // Changes difficulty

        changeToEasy.addActionListener(e -> Gui.startGame("Easy"));
        changeDifficultyItem.add(changeToEasy);


        changeToMedium.addActionListener(e -> Gui.startGame("Medium"));
        changeDifficultyItem.add(changeToMedium);

        changeToHard.addActionListener(e -> Gui.startGame("Hard"));
        changeDifficultyItem.add(changeToHard);

        menu2.add(changeDifficultyItem);


        // Adds other menu's to the menu bar

        menuBar.add(menu1);
        menuBar.add(menu2);
        menuBar.add(menu3);
        frame.setJMenuBar(menuBar);

    }

}
