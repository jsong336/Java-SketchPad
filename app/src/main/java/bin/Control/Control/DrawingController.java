package bin.Control.Control;

import bin.Control.View.DrawingPanel;
import bin.Control.View.MainFrame;

public class DrawingController {
    private DrawingPanel drawingPanel;

    public DrawingController(MainFrame mainFrame){
        drawingPanel = mainFrame.drawingPanel;
    }

    void draw(String command){
        switch (command) {
            case "Line":
                drawingPanel.drawLine();
                break;
            case "Rectangle":
                drawingPanel.drawRectangle();
                break;
            case "Ellipse":
                drawingPanel.drawCircle();
                break;
            case "Polygon":
                drawingPanel.drawPolygon();
                break;
            case "Freehand":
                drawingPanel.initiateFreehand();
                break;
            default:
                break;
        }
    }
}
