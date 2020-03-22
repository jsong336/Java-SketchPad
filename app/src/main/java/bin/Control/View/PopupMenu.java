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
        JMenuItem resizeMenu = new JMenuItem("Resize");
        JMenuItem fillMenu = new JMenuItem("Fill");
        JMenuItem copyMenu = new JMenuItem("Copy");
        JMenuItem deleteMenu = new JMenuItem("Delete");
        add(resizeMenu);
        add(fillMenu);
        add(copyMenu);
        add(deleteMenu);
    }

}
