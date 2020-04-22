package bin.Control;

import bin.Model.Board;
import bin.Model.BoardMemento;
import bin.Model.Drawable.AbstractDrawable;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class BoardMementoCareTaker {
    private Board _board;
    private LinkedList<BoardMemento> mementoStack = new LinkedList<>();
    private LinkedList<BoardMemento> redoQueue = new LinkedList<>();
    public BoardMementoCareTaker(Board board){
        _board = board;
    }

    public void screenShot(){
        System.out.println(mementoStack);
        ArrayList<AbstractDrawable> copy = new ArrayList<>();
        for(AbstractDrawable obj : _board.getOnScreen())
            copy.add(obj);
        mementoStack.push(new BoardMemento(copy));
    }

    public Board restoreTop(){
        try{
            BoardMemento m = mementoStack.pop();
            redoQueue.push(m);
            System.out.println(mementoStack);
            System.out.println(m);
            _board = new Board(m.getOnScreenMoment());
            return _board;
        }
        catch (Exception e){
            return null;
        }
    }

    public Board goBack(){
        try{
            screenShot();
            _board = new Board(redoQueue.removeFirst().getOnScreenMoment());
            return _board;
        }
        catch (Exception e){
            return null;
        }
    }
}
