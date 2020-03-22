package bin.Control.View;

import bin.Control.Drawing.AbstractDrawing;

import javax.swing.*;
import java.awt.event.MouseEvent;

public class PopupMenu extends JPopupMenu {
    private AbstractDrawing drawing = null;

    public PopupMenu(MouseEvent e){
        JMenuItem pasteMenu = new JMenuItem("Paste");
        //pasteMenu.addActionListener();
        add(pasteMenu);
    }

    public PopupMenu(AbstractDrawing drawing){
        this.drawing = drawing;
        JMenuItem resizeItem = new JMenuItem("Resize");
        resizeItem.setActionCommand("Resize");
        JMenuItem fillItem = new JMenuItem("Fill");
        resizeItem.setActionCommand("Fill");
        JMenuItem copyItem = new JMenuItem("Copy");
        resizeItem.setActionCommand("Copy");
        JMenuItem deleteItem = new JMenuItem("Delete");
        resizeItem.setActionCommand("Delete");

        add(resizeItem);
        add(fillItem);
        add(copyItem);
        add(deleteItem);
    }

}
