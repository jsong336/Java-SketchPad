package bin.View;

import bin.Const;
import bin.Control.MainController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

        Font f = new Font("sans-serif", Font.PLAIN, 15);
        UIManager.put("Menu.font", f);

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
        final JMenuBar menuBar = new JMenuBar(){
            Color bgColor= new Color(224, 215,132);

            public void setColor(Color color) {
                bgColor=color;
            }

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setColor(bgColor);
                g2d.fillRect(0, 0, getWidth() - 1, getHeight() - 1);

            }
        };

        //create menus
        JMenu fileMenu = new JMenu("File");
        JMenu editMenu = new JMenu("Edit");
        JMenu drawMenu = new JMenu("Draw");

        //create fileMenu items
        JMenuItem openMenuItem = new JMenuItem("Open");
        openMenuItem.setActionCommand("File-Open");

        JMenuItem saveMenuItem = new JMenuItem("Save");
        saveMenuItem.setActionCommand("File-Save");

        JMenuItem exitMenuItem = new JMenuItem("Exit");
        exitMenuItem.setActionCommand("File-Exit");

        //create editMenu items
        JMenuItem cutMenuItem = new JMenuItem("Cut");
        cutMenuItem.setActionCommand("Edit-Cut");

        JMenuItem copyMenuItem = new JMenuItem("Copy");
        copyMenuItem.setActionCommand("Edit-Copy");

        JMenuItem pasteMenuItem = new JMenuItem("Paste");
        pasteMenuItem.setActionCommand("Edit-Paste");

        //create drawMenu items
        JMenuItem drawFreeHandItem = new JMenuItem("FreeHand");
        drawFreeHandItem.setActionCommand("Draw-Freehand");

        JMenuItem drawLineItem = new JMenuItem("Line");
        drawLineItem.setActionCommand("Draw-Line");

        JMenuItem drawRectangleItem = new JMenuItem("Rectangle");
        drawRectangleItem.setActionCommand("Draw-Rectangle");

        JMenuItem drawSquareItem = new JMenuItem("Square");
        drawSquareItem.setActionCommand("Draw-Square");

        JMenuItem drawEllipseItem = new JMenuItem("Ellipse");
        drawEllipseItem.setActionCommand("Draw-Ellipse");

        JMenuItem drawCircleItem = new JMenuItem("Circle");
        drawCircleItem.setActionCommand("Draw-Circle");

        JMenuItem drawPolygon = new JMenuItem("Polygon");
        drawPolygon.setActionCommand("Draw-Polygon");

        JMenuItem drawClosedPolygon = new JMenuItem("Multiple Lines");
        drawClosedPolygon.setActionCommand("Draw-Multilines");

        //add menu listener
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
        drawClosedPolygon.addActionListener(_menuListener);

        //add menu items to menus
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
        drawMenu.add(drawClosedPolygon);

        //add menu to menubar
        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        menuBar.add(drawMenu);

        //add menubar to the frame
        setJMenuBar(menuBar);
    }

    private void setMenuBar(JMenuBar menuBar) {
    }

    class MainMenuListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String[] actCommand = e.getActionCommand().split("-");
            String type = actCommand[0];
            String argument = actCommand[1];

            switch (type){
                case "File":
                    _mainController.fileControl(argument);
                    break;

                case "Edit":
                    _mainController.edit(argument);
                    repaint();
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
