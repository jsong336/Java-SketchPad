package bin.Control.View;
import bin.Control.Const;
import bin.Control.Drawing.AbstractDrawing;
import bin.Control.Drawing.CircleDrawing;
import bin.Control.Drawing.LineDrawing;
import bin.Control.Drawing.RectangleDrawing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Line2D;
import java.util.ArrayList;

public class DrawingPanel extends JPanel implements MouseListener, MouseMotionListener {
    private ArrayList<AbstractDrawing> onScreen = new ArrayList<>();
    public int mode = 0;

    public DrawingPanel(){
        setBackground(Color.lightGray);
        addMouseListener(this);
        addMouseMotionListener(this);
    }

    public void drawLine(){
        mode = 0;
        onScreen.add(new LineDrawing(50, 3, 100, 100));
        repaint();
    }

    public void drawRectangle(){
        mode = 1;
        onScreen.add(new RectangleDrawing(Const.Drawing.DEFAULTS_POS_X, Const.Drawing.DEFAULTS_POS_Y, Const.Drawing.DEFAULTS_SIZE, Const.Drawing.DEFAULTS_SIZE));
        repaint();
    }

    public void drawCircle(){
        mode = 2;
        onScreen.add(new CircleDrawing(Const.Drawing.DEFAULTS_RADIUS, Const.Drawing.DEFAULTS_POS_X, Const.Drawing.DEFAULTS_POS_Y));
        repaint();
    }

    public void drawEllipse(){}

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2 = (Graphics2D)g;
        g2.setStroke(new BasicStroke(2));
        for(AbstractDrawing obj : onScreen){
            g.setColor(obj.getBorderColor());
            g2.draw(obj.getShapeNow());
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getButton()!=1){
            // display menu
            AbstractDrawing drawing = null;
            for (AbstractDrawing cur : onScreen) {
                if (cur.getShapeNow().contains(e.getPoint())) {
                    drawing = cur;
                }
                else if(cur instanceof LineDrawing){
                    System.out.println(cur);
                    if(LineDrawing.isClicked(e.getX(), e.getY(), (Line2D.Double) cur.getShapeNow()))
                        drawing = cur;
                }
            }
            JPopupMenu popupMenu;
            if(drawing == null){
                popupMenu = new PopupMenu(e);
            }
            else{
                popupMenu = new PopupMenu(drawing);
            }
            popupMenu.show(e.getComponent(), e.getX(), e.getY());
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        for (AbstractDrawing drawing : onScreen) {
            if (drawing.getShapeNow().contains(e.getPoint())) {
                drawing.selectThis();
            }
            else if(drawing instanceof LineDrawing){
                if(LineDrawing.isClicked(e.getX(), e.getY(), (Line2D.Double)drawing.getShapeNow()))
                    drawing.selectThis();
            }
        }
        repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        AbstractDrawing.unSelect();
        AbstractDrawing.isDragging = false;
        repaint();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        AbstractDrawing.isDragging = true;
        if(AbstractDrawing.getSelected() != null){
            AbstractDrawing.getSelected().move(e.getX(), e.getY());
        }
        repaint();
    }
    @Override
    public void mouseMoved(MouseEvent e) {}
    @Override
    public void mouseExited(MouseEvent e) {}
    @Override
    public void mouseEntered(MouseEvent e) {}
}
