package NewBoard;

import BoardCreater.Create;
import WelcomeScreen.MainMenu;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NBMenu {

    public static JMenu saveMenu = new JMenu("Save");
    public static int[][] board;

    public NBMenu(JFrame frame){
        JMenuBar menuBar = new JMenuBar();
        JMenu quitMenu = new JMenu("Quit");
        JMenu resetMenu = new JMenu("Reset");
        JMenu homeMenu = new JMenu("Home");
        JMenuItem quitItem = new JMenuItem("Quit");
        JMenuItem resetItem = new JMenuItem("Reset");
        JMenuItem saveItem = new JMenuItem("Save Board");
        JMenuItem homeItem = new JMenuItem("Home");

        // Quits the game
        quitItem.addActionListener(e -> System.exit(2));
        quitMenu.add(quitItem);

        // Resets the board
        resetItem.addActionListener(e -> {
            frame.setVisible(false);
            newBoard.create();
        });
        resetMenu.add(resetItem);

        // Saves the board
        saveItem.addActionListener(e -> {
            board = newBoard.board2;
            String Name = newBoard.fileName.getText();
            Create.createAndPopulate(Name,board);
        });



        // Returns to the home screen
        homeItem.addActionListener(e -> {
            frame.setVisible(false);
            MainMenu.mainMenuGUI();
        });
        homeMenu.add(homeItem);

        saveMenu.add(saveItem);
        saveMenu.setVisible(false);


        menuBar.add(quitMenu);
        menuBar.add(resetMenu);
        menuBar.add(saveMenu);
        menuBar.add(homeMenu);
        frame.setJMenuBar(menuBar);


    }
}
