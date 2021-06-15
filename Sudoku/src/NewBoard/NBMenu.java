package NewBoard;

import WelcomeScreen.MainMenu;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NBMenu {

    public static JMenu menu2 = new JMenu("Save");
    public static int[][] board;

    public NBMenu(JFrame frame){
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Quit");
        JMenu menu1 = new JMenu("Reset");
        JMenu menu3 = new JMenu("Home");
        JMenuItem quitItem = new JMenuItem("Quit");
        JMenuItem resetItem = new JMenuItem("Reset");
        JMenuItem saveItem = new JMenuItem("Save Board");
        JMenuItem homeItem = new JMenuItem("Home");

        // Quits the game
        quitItem.addActionListener(e -> System.exit(2));
        menu.add(quitItem);

        // Resets the board
        resetItem.addActionListener(e -> {
            frame.setVisible(false);
            newBoard.create();
        });
        menu1.add(resetItem);

        // Saves the board
        //TODO
        saveItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                board = newBoard.board2;
            // TODO add a new java class to run all classes to make a file from users board

            }
        });
        menu2.add(saveItem);
        menu2.setVisible(false);


        // Returns to the home screen
        homeItem.addActionListener(e -> {
            frame.setVisible(false);
            MainMenu.mainMenuGUI();
        });
        menu3.add(homeItem);

        menuBar.add(menu);
        menuBar.add(menu1);
        menuBar.add(menu2);
        menuBar.add(menu3);
        frame.setJMenuBar(menuBar);


    }
}
