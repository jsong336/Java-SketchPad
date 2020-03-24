package bin.Control;

import bin.Model.*;
import bin.Model.Drawable.AbstractDrawable;
import bin.Model.Drawable.MultiLines;
import bin.View.BoardView;
import bin.View.PopupMenuView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class BoardController {
    private Board _board;
    private BoardView _boardView = null;
    private AbstractDrawable _lastCopied = null;

    public BoardController(){
        _board = new Board();
    }

    public void registerView(BoardView view){
        _boardView = view;
    }

    public ArrayList<AbstractDrawable> getOnScreen(){ return _board.getOnScreen();  }

    public void setMode(BoardMode mode){
        _board.setMode(mode);
    }

    // Board utility functions
    public void copy(){
        _lastCopied = AbstractDrawable.copyAsChild(_board.getSelectedDrawable());
    }

    public void paste(){
        if(_lastCopied != null){
            _board.getOnScreen().add(_lastCopied);
            _lastCopied = AbstractDrawable.copyAsChild(_lastCopied);
            return;
        }
        System.out.println("Paste null");
    }

    public void remove(){
        _board.getOnScreen().remove(_board.getSelectedDrawable());
    }

    public void fill(Color color){
        _board.getSelectedDrawable().fill(color);
    }

    // View Mouse Action
    public void leftClickDoneOnBoard(Point mousePoint){
        switch (_board.getMode()){
            case DEFAULT:
                _board.click(mousePoint);
                break;
            case RECTANGLE_DRAW:
                _board.drawRectangle(mousePoint);
                break;
            case ELLIPSE_DRAW:
                _board.drawEllipse(mousePoint);
                break;
            case POLY_DRAW:
                int n = _boardView.promptPolyN();
                if(n >= 0){
                    _board.drawPolygon(mousePoint, n);
                }
                break;
            case SQUARE_DRAW:
                _board.drawSquare(mousePoint);
                break;
            case CIRCLE_DRAW:
                _board.drawCircle(mousePoint);
                break;
            case LINE_DRAW:
                _board.drawLine(mousePoint);
                break;
            case MULTILINES_DRAW:
                _board.initMultiLinesDraw(mousePoint);
                setMode(BoardMode.MULTILINES_DRAWING);
                break;
            case MULTILINES_DRAWING:
                System.out.println(_board.getSelectedDrawable());
                if(_board.getSelectedDrawable() instanceof MultiLines){
                    System.out.println(((MultiLines)_board.getSelectedDrawable()).i);
                    if(((MultiLines)_board.getSelectedDrawable()).i >= 1){
                        _board.connectLines(mousePoint);
                    }
                }
                break;
            default:
                break;
        }
        _boardView.repaint();
    }

    public void rightClickDoneOnBoard(MouseEvent e){
        JPopupMenu popup = new PopupMenuView(_boardView, _board.getSelectedDrawable() != null);
        popup.show(e.getComponent(), e.getX(), e.getY());
    }

    public void pressOnBoard(Point mousePoint){
        if(_board.getMode() == BoardMode.FREE_HAND)
            _board.initFreeHandDraw(mousePoint);
        _boardView.repaint();
    }

    public void releaseOnBoard(){
        switch (_board.getMode()){
            case RESIZE:
                _board.setMode(BoardMode.DEFAULT);
                break;
            case FREE_HAND:
                //_board.setMode(BoardMode.DEFAULT);
                break;
            default:
                break;
        }
    }

    public void dragOnBoard(Point mousePoint){
        AbstractDrawable drawable = _board.getSelectedDrawable();
        if(drawable != null){
            switch (_board.getMode()){
                case DEFAULT:
                    drawable.move(mousePoint);
                    break;

                case FREE_HAND:
                    _board.drawFreeHand(mousePoint);
                    break;

                case RESIZE:
                    drawable.resize(mousePoint);
                    break;
            }
        }
    }
}
