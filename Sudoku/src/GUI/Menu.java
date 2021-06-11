package GUI;

import Game.Sudoku;

import javax.swing.*;
import javax.swing.event.MenuDragMouseEvent;
import javax.swing.event.MenuDragMouseListener;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu {
    public Menu(JFrame frame){

        JMenu menu1 = new JMenu("Quit");
        JMenu menu2 = new JMenu("Options");
        JMenuBar menuBar = new JMenuBar();
        JMenuItem menuItem1 = new JMenuItem("Hint");
        JMenuItem menuItem2 = new JMenuItem("Reset");
        JMenuItem menuItemQuit = new JMenuItem("Quit");
        menu2.add(menuItem1);
        menu2.add(menuItem2);
        menu1.add(menuItemQuit);
        menuItemQuit.addActionListener(e -> System.exit(2));
        menuItem2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        menuBar.add(menu1);
        menuBar.add(menu2);
        frame.setJMenuBar(menuBar);

    }

}
