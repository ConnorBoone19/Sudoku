package WelcomeScreen;

import NewBoard.newBoard;

import javax.swing.*;

public class MMmenu {
    public MMmenu(JFrame frame){
        JMenuBar menuBar = new JMenuBar();
        JMenu helpMenu = new JMenu("Help");
        JMenu createMenu = new JMenu("Create");
        JMenuItem gameHelp = new JMenuItem("Game Help");
        JMenuItem createHelp = new JMenuItem("Create Help");
        JMenuItem create = new JMenuItem("Create");

        create.addActionListener(e -> {
            newBoard.create();
            frame.setVisible(false);
        });
        createMenu.add(create);

        helpMenu.add(gameHelp);

        helpMenu.add(createHelp);



        menuBar.add(helpMenu);
        menuBar.add(createMenu);

        frame.setJMenuBar(menuBar);
    }
}
