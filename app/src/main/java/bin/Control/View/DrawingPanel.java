package bin.Control.View;
import bin.Control.Const;
import bin.Control.Drawing.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Line2D;
import java.util.ArrayList;

public class DrawingPanel extends JPanel implements MouseListener, MouseMotionListener {
    private ArrayList<AbstractDrawing> onScreen = new ArrayList<>();
    public static DrawingPanel onlyOneInstance;
    public int mode = 0;
    public boolean isFreeHand = false;
    public boolean isResizing = false;

    public DrawingPanel(){
        onlyOneInstance = this;
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

    public void drawSquare(){
        onScreen.add(new Square(Const.Drawing.DEFAULTS_POS_X, Const.Drawing.DEFAULTS_POS_Y, Const.Drawing.DEFAULTS_SIZE));
        repaint();
    }

    public void drawCircle(){
        mode = 2;
        onScreen.add(new CircleDrawing(Const.Drawing.DEFAULTS_RADIUS, Const.Drawing.DEFAULTS_POS_X, Const.Drawing.DEFAULTS_POS_Y));
        repaint();
    }

    public void drawEllipse(){
        onScreen.add(new EllipseDrawing(Const.Drawing.DEFAULTS_POS_X, Const.Drawing.DEFAULTS_POS_Y, Const.Drawing.DEFAULTS_RADIUS+5, Const.Drawing.DEFAULTS_RADIUS - 5));
        repaint();
    }

    public void drawPolygon(){
        try{
            int n = Integer.parseInt(JOptionPane.showInputDialog("Please enter number of n-polygon"));
            onScreen.add(new PolygonDrawing(n, 4*Const.Drawing.DEFAULTS_POS_X, 4*Const.Drawing.DEFAULTS_POS_Y));
        }
        catch (Exception e){
            JOptionPane.showMessageDialog(getParent().getComponent(0), "Please enter valid input!");
        }
        repaint();
    }

    public void initiateFreehand(){
        isFreeHand = true;
    }

    public void initiateResize(){
        isResizing = true;
    }

    public void removeItem(AbstractDrawing item){
        onScreen.remove(item);
        repaint();
    }

    public void pasteCopy(){
        onScreen.add(PopupMenu.lastCopied);
        repaint();
    }

    public void fillColor(){
        AbstractDrawing.getSelected().fill(JColorChooser.showDialog(null, "Choose a color", Color.RED));
        repaint();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2 = (Graphics2D)g;
        g2.setStroke(new BasicStroke(2));
        for(AbstractDrawing obj : onScreen){
            Shape newShape = obj.getShapeNow();
            if(obj.isFilled()){
                System.out.println(obj.getFillColor());
                g2.setPaint(obj.getFillColor());
                g2.fill(newShape);
            }
            g.setColor(obj.getBorderColor());
            g2.draw(newShape);
            if(obj instanceof FreeHandDrawing){
                g2.drawPolyline(((FreeHandDrawing) obj).xs, ((FreeHandDrawing) obj).ys, ((FreeHandDrawing) obj).i);
            }
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
                    if(LineDrawing.isClicked(e.getX(), e.getY(), (Line2D.Double) cur.getShapeNow()))
                        drawing = cur;
                }
                else if(cur instanceof FreeHandDrawing){
                    if(FreeHandDrawing.isClicked(e.getX(), e.getY(), (FreeHandDrawing)cur)){
                        drawing = cur;
                    }
                }
            }

            JPopupMenu popupMenu;
            if(drawing == null){
                popupMenu = new PopupMenu(e);
                AbstractDrawing.unSelect();
            }
            else{
                drawing.selectThis();
                popupMenu = new PopupMenu(drawing, this);
            }
            popupMenu.show(e.getComponent(), e.getX(), e.getY());
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(isFreeHand){
            AbstractDrawing stroke = new FreeHandDrawing(e.getX(), e.getY());
            onScreen.add(stroke);
            stroke.selectThis();
        }
        else{
            for (AbstractDrawing drawing : onScreen) {
                if (drawing.getShapeNow().contains(e.getPoint())) {
                    drawing.selectThis();
                }
                else if(drawing instanceof LineDrawing){
                    if(LineDrawing.isClicked(e.getX(), e.getY(), (Line2D.Double)drawing.getShapeNow()))
                        drawing.selectThis();
                }
                else if(drawing instanceof FreeHandDrawing){
                    if(FreeHandDrawing.isClicked(e.getX(), e.getY(), (FreeHandDrawing)drawing)){
                        drawing.selectThis();
                    }
                }
            }
        }
        repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        AbstractDrawing.unSelect();
        AbstractDrawing.isDragging = false;
        if(isFreeHand) isFreeHand = false;
        if(isResizing) isResizing = false;
        repaint();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        AbstractDrawing.isDragging = true;
        if(isFreeHand){
            if(AbstractDrawing.getSelected() != null){
                ((FreeHandDrawing)AbstractDrawing.getSelected()).drawPoint(e.getX(), e.getY());
            }
        }
        else if(isResizing){
            AbstractDrawing.getSelected().resize(e.getX(), e.getY());
        }
        else{
            if(AbstractDrawing.getSelected() != null){
                AbstractDrawing.getSelected().move(e.getX(), e.getY());
            }
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
