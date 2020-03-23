package bin.View;

import bin.Const;
import bin.Control.MainController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class MainView extends JFrame {
    public BoardView _boardView;
    private MainController _mainController;
    private MainMenuListener _menuListener;

    public MainView (MainController controller) {
        _mainController = controller;
        _boardView = new BoardView(_mainController.boardController);
        _menuListener = new MainMenuListener();

        setTitle(Const.Main.TITLE);
        setSize(Const.Main.WIDTH, Const.Main.HEIGHT);

        setResizable(true);
        _addMenuBar();
        setContentPane(_boardView);
        setVisible(true);
    }

    public BoardView getBoardView(){
        return _boardView;
    }

    private void _addMenuBar() {
        //create a menu bar
        final MenuBar menuBar = new MenuBar();

        //create menus
        Menu fileMenu = new Menu("File");
        Menu editMenu = new Menu("Edit");
        Menu drawMenu = new Menu("Draw");

        //create fileMenu items
        MenuItem newMenuItem = new MenuItem("New", new MenuShortcut(KeyEvent.VK_N));
        newMenuItem.setActionCommand("File-New");

        MenuItem openMenuItem = new MenuItem("Open");
        openMenuItem.setActionCommand("File-Open");

        MenuItem saveMenuItem = new MenuItem("Save");
        saveMenuItem.setActionCommand("File-Save");

        MenuItem exitMenuItem = new MenuItem("Exit");
        exitMenuItem.setActionCommand("File-Exit");

        //create editMenu items
        MenuItem cutMenuItem = new MenuItem("Cut");
        cutMenuItem.setActionCommand("Edit-Cut");

        MenuItem copyMenuItem = new MenuItem("Copy");
        copyMenuItem.setActionCommand("Edit-Copy");

        MenuItem pasteMenuItem = new MenuItem("Paste");
        pasteMenuItem.setActionCommand("Edit-Paste");

        //create drawMenu items
        MenuItem drawFreeHandItem = new MenuItem("FreeHand");
        drawFreeHandItem.setActionCommand("Draw-Freehand");

        MenuItem drawLineItem = new MenuItem("Line");
        drawLineItem.setActionCommand("Draw-Line");

        MenuItem drawRectangleItem = new MenuItem("Rectangle");
        drawRectangleItem.setActionCommand("Draw-Rectangle");

        MenuItem drawSquareItem = new MenuItem("Square");
        drawSquareItem.setActionCommand("Draw-Square");

        MenuItem drawEllipseItem = new MenuItem("Ellipse");
        drawEllipseItem.setActionCommand("Draw-Ellipse");

        MenuItem drawCircleItem = new MenuItem("Circle");
        drawCircleItem.setActionCommand("Draw-Circle");

        MenuItem drawPolygon = new MenuItem("Polygon");
        drawPolygon.setActionCommand("Draw-Polygon");

        //add menu listener
        newMenuItem.addActionListener(_menuListener);
        openMenuItem.addActionListener(_menuListener);
        saveMenuItem.addActionListener(_menuListener);
        exitMenuItem.addActionListener(_menuListener);

        cutMenuItem.addActionListener(_menuListener);
        copyMenuItem.addActionListener(_menuListener);
        pasteMenuItem.addActionListener(_menuListener);

        drawFreeHandItem.addActionListener(_menuListener);
        drawLineItem.addActionListener(_menuListener);
        drawRectangleItem.addActionListener(_menuListener);
        drawSquareItem.addActionListener(_menuListener);
        drawCircleItem.addActionListener(_menuListener);
        drawEllipseItem.addActionListener(_menuListener);
        drawPolygon.addActionListener(_menuListener);

        //add menu items to menus
        fileMenu.add(newMenuItem);
        fileMenu.add(openMenuItem);
        fileMenu.add(saveMenuItem);
        fileMenu.add(exitMenuItem);

        editMenu.add(cutMenuItem);
        editMenu.add(copyMenuItem);
        editMenu.add(pasteMenuItem);

        drawMenu.add(drawFreeHandItem);
        drawMenu.add(drawLineItem);
        drawMenu.add(drawRectangleItem);
        drawMenu.add(drawSquareItem);
        drawMenu.add(drawEllipseItem);
        drawMenu.add(drawCircleItem);
        drawMenu.add(drawPolygon);

        //add menu to menubar
        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        menuBar.add(drawMenu);

        //add menubar to the frame
        setMenuBar(menuBar);
    }

    class MainMenuListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String[] actCommand = e.getActionCommand().split("-");
            String type = actCommand[0];
            String argument = actCommand[1];

            switch (type){
                case "File":
                    break;

                case "Edit":
                    break;

                case "Draw":
                    _mainController.setDrawMode(argument);
                    break;

                default:
                    break;
            }
        }
    }
}
