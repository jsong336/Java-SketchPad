package bin.Control.Control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PopupController implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()){
            case "Resize":
                break;
            case "Copy":
                break;
            case "Paste":
                break;
            case "Delete":
                break;
            default:
                break;
        }
    }
}
