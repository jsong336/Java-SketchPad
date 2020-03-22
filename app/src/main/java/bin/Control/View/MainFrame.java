package bin.Control.View;

import bin.Control.Const;
import bin.Control.MenuController;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class MainFrame extends JFrame{
    public final DrawingPanel drawingPanel = new DrawingPanel();
    private final MenuController menuController = new MenuController(this);

    private void addMenuBar() {
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
        MenuItem drawItem = new MenuItem("Draw");
        drawItem.setActionCommand("Draw-Freehand");

        MenuItem drawLineItem = new MenuItem("Line");
        drawLineItem.setActionCommand("Draw-Line");

        MenuItem drawRectangleItem = new MenuItem("Rectangle");
        drawRectangleItem.setActionCommand("Draw-Rectangle");

        MenuItem drawEllipseItem = new MenuItem("Ellipse");
        drawEllipseItem.setActionCommand("Draw-Ellipse");

        MenuItem drawPolygon = new MenuItem("Polygon");
        drawPolygon.setActionCommand("Draw-Polygon");

        //add menu listener
        newMenuItem.addActionListener(menuController);
        openMenuItem.addActionListener(menuController);
        saveMenuItem.addActionListener(menuController);
        exitMenuItem.addActionListener(menuController);

        cutMenuItem.addActionListener(menuController);
        copyMenuItem.addActionListener(menuController);
        pasteMenuItem.addActionListener(menuController);

        drawItem.addActionListener(menuController);
        drawLineItem.addActionListener(menuController);
        drawRectangleItem.addActionListener(menuController);
        drawEllipseItem.addActionListener(menuController);
        drawPolygon.addActionListener(menuController);

        //add menu items to menus
        fileMenu.add(newMenuItem);
        fileMenu.add(openMenuItem);
        fileMenu.add(saveMenuItem);
        fileMenu.add(exitMenuItem);

        editMenu.add(cutMenuItem);
        editMenu.add(copyMenuItem);
        editMenu.add(pasteMenuItem);

        drawMenu.add(drawItem);
        drawMenu.add(drawLineItem);
        drawMenu.add(drawRectangleItem);
        drawMenu.add(drawEllipseItem);
        drawMenu.add(drawPolygon);

        //add menu to menubar
        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        menuBar.add(drawMenu);

        //add menubar to the frame
        setMenuBar(menuBar);
    }

    private void addDrawingPane(){
        setContentPane(drawingPanel);
    }

    public MainFrame () {
        setSize(Const.Main.WIDTH, Const.Main.HEIGHT);
        setTitle(Const.Main.TITLE);

        setResizable(true);
        addMenuBar();
        addDrawingPane();

        setVisible(true);
    }
}
