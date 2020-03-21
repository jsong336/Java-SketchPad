import java.awt.*;
import java.awt.event.*;

public class MainView {
    private Frame mainFrame;
    private Label statusLabel;   /* TODO: Connect*/

    private void prepareMainPage(){
        mainFrame = new Frame(Const.Main.TITLE);
        mainFrame.setSize(Const.Main.WIDTH, Const.Main.HEIGHT);
        mainFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent){
                System.exit(0);
            }
        });

        /* TODO: Connect*/
        statusLabel = new Label();
        statusLabel.setAlignment(Label.CENTER);
        statusLabel.setSize(350,100);
        /* TODO: Connect END*/

        mainFrame.add(statusLabel);
        mainFrame.setVisible(true);
    }

    private void addMenuBar(){
        //create a menu bar
        final MenuBar menuBar = new MenuBar();

        //create menus
        Menu fileMenu = new Menu("File");
        Menu editMenu = new Menu("Edit");
        Menu drawMenu = new Menu("Draw");

        //create fileMenu items
        MenuItem newMenuItem = new MenuItem("New",new MenuShortcut(KeyEvent.VK_N));
        newMenuItem.setActionCommand("New");

        MenuItem openMenuItem = new MenuItem("Open");
        openMenuItem.setActionCommand("Open");

        MenuItem saveMenuItem = new MenuItem("Save");
        saveMenuItem.setActionCommand("Save");

        MenuItem exitMenuItem = new MenuItem("Exit");
        exitMenuItem.setActionCommand("Exit");

        //create editMenu items
        MenuItem cutMenuItem = new MenuItem("Cut");
        cutMenuItem.setActionCommand("Cut");

        MenuItem copyMenuItem = new MenuItem("Copy");
        copyMenuItem.setActionCommand("Copy");

        MenuItem pasteMenuItem = new MenuItem("Paste");
        pasteMenuItem.setActionCommand("Paste");

        //create drawMenu items
        MenuItem drawItem = new MenuItem("Draw");
        drawItem.setActionCommand("Draw");

        MenuItem drawLineItem = new MenuItem("Line");
        drawLineItem.setActionCommand("Draw Line");

        MenuItem drawRectangleItem = new MenuItem("Rectangle");
        drawRectangleItem.setActionCommand("Draw Rectangle");

        MenuItem drawEllipseItem = new MenuItem("Ellipse");
        drawEllipseItem.setActionCommand("Draw Ellipse");

        MenuItem drawPolygon = new MenuItem("Polygon");
        drawPolygon.setActionCommand("Draw Polygon");

        //add menu listener
        MenuItemListener menuItemListener = new MenuItemListener();

        newMenuItem.addActionListener(menuItemListener);
        openMenuItem.addActionListener(menuItemListener);
        saveMenuItem.addActionListener(menuItemListener);
        exitMenuItem.addActionListener(menuItemListener);

        cutMenuItem.addActionListener(menuItemListener);
        copyMenuItem.addActionListener(menuItemListener);
        pasteMenuItem.addActionListener(menuItemListener);

        drawItem.addActionListener(menuItemListener);
        drawLineItem.addActionListener(menuItemListener);
        drawRectangleItem.addActionListener(menuItemListener);
        drawEllipseItem.addActionListener(menuItemListener);
        drawPolygon.addActionListener(menuItemListener);

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
        mainFrame.setMenuBar(menuBar);
    }

    class MenuItemListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            statusLabel.setText(e.getActionCommand() + " MenuItem clicked.");
        }
    }
    public void display(){
        mainFrame.setVisible(true);
    }

    public MainView(){
        prepareMainPage();
    }

    public static void main(String[] args) {
        MainView mainView = new MainView();
        mainView.addMenuBar();
        mainView.display();
    }
}
