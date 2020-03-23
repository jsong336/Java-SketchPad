package bin.View;

import bin.Model.BoardMode;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class PopupMenuView extends JPopupMenu {
    private BoardView _boardView;
    private boolean _onShape;

    public PopupMenuView(BoardView boardView, boolean onShape){
        this._boardView = boardView;
        this._onShape = onShape;

        JMenuItem pasteMenu = new JMenuItem("Paste");
        pasteMenu.setActionCommand("Paste");
        pasteMenu.addActionListener(new PopumMenuActionListener());

        JMenuItem resizeItem = new JMenuItem("Resize");
        resizeItem.addActionListener(new PopumMenuActionListener());

        JMenuItem fillItem = new JMenuItem("Fill");
        fillItem.setActionCommand("Fill");
        fillItem.addActionListener(new PopumMenuActionListener());

        JMenuItem copyItem = new JMenuItem("Copy");
        copyItem.setActionCommand("Copy");
        copyItem.addActionListener(new PopumMenuActionListener());

        JMenuItem deleteItem = new JMenuItem("Delete");
        deleteItem.setActionCommand("Delete");
        deleteItem.addActionListener(new PopumMenuActionListener());

        add(resizeItem);
        add(fillItem);
        add(copyItem);
        add(deleteItem);
        add(pasteMenu);
    }
    class PopumMenuActionListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println(e.getActionCommand());
            switch (e.getActionCommand()){
                case "Resize":
                    _boardView.boardController.setMode(BoardMode.RESIZE);
                    break;

                case "Copy":
                    _boardView.boardController.copy();
                    break;

                case "Paste":
                    _boardView.boardController.paste();
                    break;

                case "Delete":
                    _boardView.boardController.remove();
                    break;

                case "Fill":
                    _boardView.boardController.fill(JColorChooser.showDialog(null, "Choose a color", Color.RED));
                    break;

                default:
                    break;
            }
        }
    }

    class PopupMenuMouseListener implements MouseListener{

        @Override
        public void mouseClicked(MouseEvent e) {
        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }
}