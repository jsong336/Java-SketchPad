package View;

import Drawing.AbstractDrawing;
import Drawing.CircleDrawing;
import Drawing.LineDrawing;
import Drawing.RectangleDrawing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class DrawingPanel extends JPanel implements MouseListener {
    private ArrayList<AbstractDrawing> onScreen = new ArrayList<>();
    public DrawingPanel(){
        setBackground(Color.lightGray);
        addMouseListener(this);
    }
    public int mode = 0;
    public Color color = Color.BLACK;

    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;
        for(AbstractDrawing obj : onScreen){
            g.setColor(obj.borderColor);
            g2.draw(obj.getShapeNow());
        }

    }

    public void drawLine(){
        mode = 0;
        onScreen.add(new LineDrawing(50, 3, 100, 100));
        repaint();
    }

    public void drawRectangle(){
        mode = 1;
        onScreen.add(new RectangleDrawing(3, 3, 100, 100));
        repaint();
    }

    public void drawCircle(){
        mode = 2;
        onScreen.add(new CircleDrawing(100, 100, 100));
        repaint();
    }


    public void drawEllipse(){

    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        for (int i = 0; i < onScreen.size(); i++) {
            AbstractDrawing drawing = onScreen.get(i);
            if (drawing.getShapeNow().contains(e.getPoint())) {
                drawing.borderColor = Color.red;
            }
        }
        repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        for (int i = 0; i < onScreen.size(); i++) {
            AbstractDrawing drawing = onScreen.get(i);
            if (drawing.getShapeNow().contains(e.getPoint())) {
                drawing.borderColor = Color.black;
            }
        }
        repaint();
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
