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

/* contains most of board controlling activities */
public class BoardController {
    /* Board, BoardView, BoardMementoCareTaker are delegated */
    private Board _board;
    private BoardView _boardView = null;
    private BoardMementoCareTaker _mementoCareTaker;
    /* stores the instance of copied Drawable object */
    private AbstractDrawable _lastCopied = null; // TODO: _lastCopied & relevant logic should go to Board

    public BoardController(){
        /* create a board object */
        _board = new Board();
        _mementoCareTaker = new BoardMementoCareTaker(_board);
//        _redoCareTaker = new BoardMementoCareTaker(_board);
    }

    public void registerView(BoardView view){
        /* set board view */
        _boardView = view;
    }

    public ArrayList<AbstractDrawable> getOnScreen(){
        /* return objects on screen from board objects */
        return _board.getOnScreen();
    }

    public BoardMode getBoardMode(){
        return _board.getMode();
    }

    public void reset(java.util.List<AbstractDrawable> onScreen){
        /* clear out the board and create new board with onScreen */
        _lastCopied = null;
        _board = new Board(onScreen);
        _mementoCareTaker = new BoardMementoCareTaker(_board);
        _boardView.repaint();
    }

    public void setBoardMode(BoardMode mode){
        /*
        * set BoardMode of the board
        * @param  mode
        * @return
         */
        if(_board.getMode()==BoardMode.MULTILINES_DRAWING && mode == BoardMode.DEFAULT){
            _board.unSelectAll(); // TODO: check if this is appropriate place to put this logic
            _mementoCareTaker.screenShot();
        }
        _board.setMode(mode);
    }

    /* ************************************************************************
     * board utilities
     * ************************************************************************/
    public void copy(){
        /* update the _lastCopied with current selected */
        _lastCopied = AbstractDrawable.copyAsChild(_board.getSelectedDrawable());
        _boardView.repaint(); // TODO: is it necessary?
    }

    public void paste(){
        if(_lastCopied != null){
            /* add _lastCopied object to the board */
            _board.getOnScreen().add(_lastCopied);
            _lastCopied = AbstractDrawable.copyAsChild(_lastCopied); // _lastCopied is updated to most recently copied object - user expectation when copy paste multiple times
            return;
        }
        _board.unSelectAll();
        _boardView.repaint();
        _mementoCareTaker.screenShot();
    }

    public void remove(){
        /* remove selected object and repaint */
        _board.getOnScreen().remove(_board.getSelectedDrawable());
        _boardView.repaint();
        _mementoCareTaker.screenShot();
    }

    public void fill(Color color){
        /*
         * fill the selected object on the board
         * @param  color the selected color
         * @return
         */
        _board.getSelectedDrawable().fill(color); /* update only selected object */
        _boardView.repaint();
        _mementoCareTaker.screenShot();
    }

    public void redo(){
        System.out.println("Redo Pressed");
//        if(_redoCareTaker != null)
//            _board = _redoCareTaker.restoreTop();
//        _boardView.repaint();
    }

    public void undo(){
        System.out.println("Undo Pressed");
        Board memento = _mementoCareTaker.restoreTop();
        if(memento != null)
            _board = memento;
        _boardView.repaint();
    }

    /* ************************************************************************
     * mouse click handling based on the board's state
     * ************************************************************************/
    public void leftClickDoneOnBoard(Point mousePoint){
        /*
         * handles when left clicked on the board. Behaves based on current board mode.
         * @param  e MouseEvent
         * @return
         */
        switch (_board.getMode()){
            case DEFAULT:
                // if on default mode, click on the board. May or may not update the current selected object based on the mousePoint
                _board.click(mousePoint);
                break;

            case LINE_DRAW:
                _board.drawLine(mousePoint);
                _mementoCareTaker.screenShot();
                break;

            case RECTANGLE_DRAW:
                _board.drawRectangle(mousePoint);
                _mementoCareTaker.screenShot();
                break;

            case SQUARE_DRAW:
                _board.drawSquare(mousePoint);
                _mementoCareTaker.screenShot();
                break;

            case ELLIPSE_DRAW:
                _board.drawEllipse(mousePoint);
                _mementoCareTaker.screenShot();
                break;

            case CIRCLE_DRAW:
                _board.drawCircle(mousePoint);
                _mementoCareTaker.screenShot();
                break;

            case POLY_DRAW:
                /* retrieve n */
                int n = _boardView.promptPolyN();
                if(n > 1){
                    // if n is within range, create polygon
                    _board.drawPolygon(mousePoint, n);
                    _mementoCareTaker.screenShot();
                }
                // setBoardMode(BoardMode.DEFAULT);
                break;

            case MULTILINES_DRAW:
                // TODO: fix multilines draw
                _board.initMultiLinesDraw(mousePoint);
                setBoardMode(BoardMode.MULTILINES_DRAWING);
                _boardView.repaint();
                break;

            case MULTILINES_DRAWING:
                if(_board.getSelectedDrawable() instanceof MultiLines){
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
        /*
         * handles when right clicked on the board. Show pop up menu.
         * @param  e MouseEvent
         * @return
         */
        JPopupMenu popup = new PopupMenuView(_boardView, _board.getSelectedDrawable() != null);
        popup.show(e.getComponent(), e.getX(), e.getY());
    }

    public void pressOnBoard(Point mousePoint){
        /*
         * handles press events on board
         * @param  pressed mouse point
         * @return
         */
        if(_board.getMode() == BoardMode.FREE_HAND){
            // if user is starts free hand drawing, then initialize free hand drawing mode
            _board.initFreeHandDraw(mousePoint);
        }
        _boardView.repaint();
    }

    public void releaseOnBoard(){
        /* handles release events on board */
        switch (_board.getMode()){
            case RESIZE:
                /* if mouse was released while resizing, then resizing is done and returned to default */
                _board.setMode(BoardMode.DEFAULT);
                _board.unSelectAll();
                _mementoCareTaker.screenShot();
                break;

            case FREE_HAND:
                /* if mouse was released while drawing free hand, then the finish that stroke but next dragging will still draw free hand */
                //_board.setMode(BoardMode.DEFAULT);
                _board.unSelectAll();
                _mementoCareTaker.screenShot();
                break;

            default:
                break;
        }
        _boardView.repaint(); // update the board
    }

    public void dragOnBoard(Point mousePoint){
        /*
         * handles dragging events on board
         * @param  current mouse point
         * @return
         */
        AbstractDrawable drawable = _board.getSelectedDrawable();
        if(drawable != null){
            /* dragging only effective when an object is selected */
            switch (_board.getMode()){
                case DEFAULT:
                    /* if on default mode, move the selected object */
                    drawable.move(mousePoint);
                    break;

                case FREE_HAND:
                    /* if the user was drawing free hand, then keep drawing pixels */
                    _board.drawFreeHand(mousePoint);
                    break;

                case RESIZE:
                    /* if the user was resizing, then keep resizing object by pixels */
                    drawable.resize(mousePoint);
                    break;

                default:
                    break;
            }
        }
    }
}
