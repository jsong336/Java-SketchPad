package bin.Control;

import bin.Model.*;
import bin.Model.Drawable.AbstractDrawable;
import bin.View.BoardView;
import bin.View.PopupMenuView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class BoardController {
    private Board _board;
    private BoardView _boardView = null;
    AbstractDrawable lastCopied = null;

    public BoardController(){
        _board = new Board();
    }

    public void RegisterView(BoardView view){
        _boardView = view;
    }

    public ArrayList<AbstractDrawable> getOnScreen(){
        return _board.getOnScreen();
    }

    public void setMode(BoardMode mode){
        _board.setMode(mode);
    }


    public void copy(){
        lastCopied = AbstractDrawable.copyAsChild(_board.getSelectedDrawable());
    }

    public void paste(){
        if(lastCopied != null){
            System.out.println(lastCopied);
            _board.getOnScreen().add(lastCopied);
            lastCopied = AbstractDrawable.copyAsChild(lastCopied);
        }
        else{
            System.out.println("Null");
        }
    }

    public void remove(){
        _board.getOnScreen().remove(_board.getSelectedDrawable());
    }

    public void fill(Color color){
        _board.getSelectedDrawable().fill(color);
    }

    public void LeftClickBoard(Point mousePoint){
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
            default:
                break;
        }
    }

    public void RightClickBoard(MouseEvent e){
        JPopupMenu popup = new PopupMenuView(_boardView, _board.getSelectedDrawable() != null);
        popup.show(e.getComponent(), e.getX(), e.getY());
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
    public void pressClick(Point mousePoint){
        if(_board.getMode() == BoardMode.FREE_HAND)
            _board.initFreeHandDraw(mousePoint);
    }
    public void releaseClick(){
        switch (_board.getMode()){
            case RESIZE:
            case FREE_HAND:
                _board.setMode(BoardMode.DEFAULT);
                break;
            default:
                break;
        }
    }
}
