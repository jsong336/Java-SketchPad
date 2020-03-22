package bin.Control;

import bin.Control.View.MainFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class MenuController implements ActionListener {
    private DrawingController drawingController;
    public MenuController(MainFrame mainFrame){
        drawingController = new DrawingController(mainFrame);
    }
    public void actionPerformed(ActionEvent e) {
        String[] actCommand = e.getActionCommand().split("-");
        String type = actCommand[0];
        String command = actCommand[1];

        switch (type){
            case "File":
                break;

            case "Edit":
                break;

            case "Draw":
                drawingController.draw(command);
                break;

            default:
                break;
        }
    }
}