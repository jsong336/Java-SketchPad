package View;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;

public class DrawingPanel extends JPanel {
    public DrawingPanel(){
        setBackground(Color.lightGray);
    }
    public int mode = 0;

    public void paint(Graphics g) {
        g.setColor(Color.RED);
        Graphics2D g2 = (Graphics2D)g;
        System.out.println(mode);
        Shape obj = null;
        switch (mode){
            case 0:
                obj = new Line2D.Double(3, 3, 303, 303);
                break;
            case 1:
                obj = new Rectangle(3, 3, 100, 100);
                break;
            case 2:
                obj = new Ellipse2D.Double(100, 100, 100, 100);
                break;
            default:
                break;
        }
        //Shape roundRect = new RoundRectangle2D.Double(20, 20, 250, 250, 5, 25);
        g2.draw(obj);
    }
    public void drawLine(){
        mode = 0;
        repaint();
    }
    public void drawRectangle(){
        mode = 1;
        System.out.println(mode);
        repaint();
    }

    public void drawCircle(){
        mode = 2;
        repaint();
    }


    public void drawEllipse(){

    }
}
