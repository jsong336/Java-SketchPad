package bin.View;

import bin.Control.BoardController;
import bin.Model.Drawable.AbstractDrawable;
import bin.Model.BoardMode;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class BoardView extends JPanel {
    public BoardController boardController;

    public BoardView(BoardController controller){
        boardController = controller;

        setBackground(new Color(242, 238, 203));

        addMouseListener(new BoardMouseListener());
        addMouseMotionListener(new BoardMouseMotionListener());
        addKeyListener(new BoardKeyListener());
        setFocusable(true);
    }

    public int promptPolyN(){
        int n = 0;
        try{
            n = Integer.parseInt(JOptionPane.showInputDialog("Please enter number of n-polygon"));
            if(n < 3){
                JOptionPane.showMessageDialog(getParent().getComponent(0), "Please enter number greater than 2");
                n = 0;
            }
        }
        catch (Exception e){
            JOptionPane.showMessageDialog(getParent().getComponent(0), "Please enter valid input!");
        }
        return n;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(2));

        for (AbstractDrawable obj : boardController.getOnScreen())
            obj.drawInstance(g2);

        switch (boardController.getBoardMode()){
            case DEFAULT:
                setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                break;
            case RECTANGLE_DRAW:
            case ELLIPSE_DRAW:
            case POLY_DRAW:
            case CIRCLE_DRAW:
            case SQUARE_DRAW:
            case LINE_DRAW:
            case MULTILINES_DRAW:
            case MULTILINES_DRAWING:
            case FREE_HAND:
                setCursor(new Cursor(Cursor.HAND_CURSOR));
                break;
            case RESIZE:
                setCursor(new Cursor(Cursor.E_RESIZE_CURSOR));
                break;
        }
    }

    class BoardKeyListener implements KeyListener {

        @Override
        public void keyTyped(KeyEvent e) {}

        @Override
        public void keyPressed(KeyEvent e) {
            if(e.getKeyCode() == KeyEvent.VK_ESCAPE)
                boardController.setBoardMode(BoardMode.DEFAULT);
            if(e.isControlDown() && e.getKeyCode() == KeyEvent.VK_C)
                boardController.copy();
            if(e.isControlDown() && e.getKeyCode() == KeyEvent.VK_V)
                boardController.paste();
            if(e.getKeyCode() == 8)
                boardController.remove();
            if(e.isControlDown() && e.getKeyCode() == KeyEvent.VK_Z)
                boardController.undo();
            if(e.isControlDown() && e.getKeyCode() == KeyEvent.VK_P)
                boardController.redo();
            repaint();
        }

        @Override
        public void keyReleased(KeyEvent e) {}
    }

    class BoardMouseListener implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
            if(e.getButton() != 1){
               boardController.rightClickDoneOnBoard(e);
            }
            else{
                boardController.leftClickDoneOnBoard(e.getPoint());
            }
            repaint();
        }

        @Override
        public void mousePressed(MouseEvent e) {
            boardController.pressOnBoard(e.getPoint());
            repaint();
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            boardController.releaseOnBoard();
            repaint();
        }

        @Override
        public void mouseEntered(MouseEvent e) { }

        @Override
        public void mouseExited(MouseEvent e) { }
    }

    class BoardMouseMotionListener implements MouseMotionListener {

        @Override
        public void mouseDragged(MouseEvent e) {
            boardController.dragOnBoard(e.getPoint());
            repaint();
        }

        @Override
        public void mouseMoved(MouseEvent e) {
            repaint();
        }
    }
}

