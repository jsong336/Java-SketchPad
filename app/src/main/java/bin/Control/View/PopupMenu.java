package bin.Control.View;

import bin.Control.Drawing.AbstractDrawing;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class PopupMenu extends JPopupMenu implements MouseListener, ActionListener {
    DrawingPanel drawingPanel;
    AbstractDrawing lastCopied = null;

    public PopupMenu(MouseEvent e){
        JMenuItem pasteMenu = new JMenuItem("Paste");
        pasteMenu.addActionListener(this);
        add(pasteMenu);
    }

    public PopupMenu(AbstractDrawing drawing, DrawingPanel drawingPanel){
        this.drawingPanel = drawingPanel;
        JMenuItem resizeItem = new JMenuItem("Resize");
        resizeItem.setActionCommand("Resize");
        resizeItem.addActionListener(this);

        JMenuItem fillItem = new JMenuItem("Fill");
        fillItem.setActionCommand("Fill");
        fillItem.addActionListener(this);

        JMenuItem copyItem = new JMenuItem("Copy");
        copyItem.setActionCommand("Copy");
        copyItem.addActionListener(this);

        JMenuItem deleteItem = new JMenuItem("Delete");
        deleteItem.setActionCommand("Delete");
        deleteItem.addActionListener(this);

        add(resizeItem);
        add(fillItem);
        add(copyItem);
        add(deleteItem);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        AbstractDrawing.unSelect();
    }

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(e.getActionCommand());
        switch (e.getActionCommand()){
            case "Resize":
                break;
            case "Copy":
                // lastCopied = AbstractDrawing.getSelected(); // Create copy constructor
                break;
            case "Paste":
                // drawingPanel.pasteCopy(lastCopied);
                break;
            case "Delete":
                drawingPanel.removeItem(AbstractDrawing.getSelected());
                break;
            default:
                break;
        }
        AbstractDrawing.unSelect();
    }
}
