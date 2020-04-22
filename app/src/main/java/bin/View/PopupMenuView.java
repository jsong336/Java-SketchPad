package bin.View;

import bin.Model.BoardMode;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class PopupMenuView extends JPopupMenu {
    private BoardView _boardView;

    public PopupMenuView(BoardView boardView, boolean onShape){
        this._boardView = boardView;

        if(onShape){
            JMenuItem resizeItem = new JMenuItem("Resize");
            resizeItem.addActionListener(new PopupMenuActionListener());

            JMenuItem fillItem = new JMenuItem("Fill");
            fillItem.setActionCommand("Fill");
            fillItem.addActionListener(new PopupMenuActionListener());

            JMenuItem copyItem = new JMenuItem("Copy");
            copyItem.setActionCommand("Copy");
            copyItem.addActionListener(new PopupMenuActionListener());

            JMenuItem deleteItem = new JMenuItem("Delete");
            deleteItem.setActionCommand("Delete");
            deleteItem.addActionListener(new PopupMenuActionListener());

            add(resizeItem);
            add(fillItem);
            add(copyItem);
            add(deleteItem);
        }
        else{
            JMenuItem pasteMenu = new JMenuItem("Paste");
            pasteMenu.setActionCommand("Paste");
            pasteMenu.addActionListener(new PopupMenuActionListener());
            add(pasteMenu);
        }
    }
    class PopupMenuActionListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            switch (e.getActionCommand()){
                case "Resize":
                    _boardView.boardController.setBoardMode(BoardMode.RESIZE);
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
            _boardView.repaint();
        }
    }
}