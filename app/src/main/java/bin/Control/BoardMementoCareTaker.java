package bin.Control;

import bin.Model.Board;
import bin.Model.BoardMemento;
import java.util.Stack;

public class BoardMementoCareTaker {
    private Board _board;
    private Stack<BoardMemento> mementoStack = new Stack<>();
    public BoardMementoCareTaker(Board board){
        _board = board;
        screenShot();
    }
    public void screenShot(){
        mementoStack.push(new BoardMemento(_board.getOnScreen()));
    }
    public Board restoreTop(){
        try{
            mementoStack.pop();
            return new Board(mementoStack.peek().getOnScreenMoment());
        }
        catch (Exception e){
            return null;
        }
    }
}
