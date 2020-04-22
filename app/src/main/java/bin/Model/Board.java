package bin.Model;

import bin.Const;
import bin.Model.Drawable.*;
import bin.Model.Drawable.Polygon;
import bin.Model.Drawable.Rectangle;

import java.awt.*;
import java.util.ArrayList;

/*
* This is Board Model class. It contains board logic
* */
public class Board {
    private BoardMode _mode = BoardMode.DEFAULT;
    private ArrayList<AbstractDrawable> _onScreen = new ArrayList<>();
    private AbstractDrawable _selectedDrawable = null;

    public Board(){}
    public Board(java.util.List<AbstractDrawable> onScreen){
        _onScreen = (ArrayList<AbstractDrawable>) onScreen;
    }

    public BoardMode getMode () { return _mode; }
    public ArrayList<AbstractDrawable> getOnScreen() { return _onScreen; }
    public AbstractDrawable getSelectedDrawable() { return _selectedDrawable; }

    public void setMode(BoardMode _mode) {
        this._mode = _mode;
    }

    private void setSelectedDrawable(AbstractDrawable drawable) {
        boolean inBoard = false;

        // Check if the object is in board
        for(AbstractDrawable obj: _onScreen){
            if(obj == drawable) {
                inBoard = true;
                break;
            }
        }

        if(!inBoard)
            throw new Error("New selected drawable is not on the board");

        if(_selectedDrawable != null)
            _selectedDrawable.unSelectThis();
        drawable.selectThis();

        this._selectedDrawable = drawable;
    }

    public void unSelectAll(){
        for(AbstractDrawable obj: _onScreen)
            obj.unSelectThis();
        _selectedDrawable = null;
    }

    public void click(Point mousePoint){
        boolean clickedObj = false;
        for(AbstractDrawable obj: _onScreen){
            if(obj.isClicked(mousePoint)){
                setSelectedDrawable(obj);
                clickedObj = true;
            }
        }
        if(!clickedObj)
            unSelectAll();
    }

    public void initFreeHandDraw(Point startPoint){
        setMode(BoardMode.FREE_HAND);
        FreeHand fh = new FreeHand(startPoint);
        _onScreen.add(fh);
        setSelectedDrawable(fh);
    }

    public void initMultiLinesDraw(Point startPoint){
        setMode(BoardMode.MULTILINES_DRAW);
        MultiLines ml = new MultiLines(startPoint);
        _onScreen.add(ml);
        setSelectedDrawable(ml);
    }

    public void drawFreeHand(Point mousePoint){
        ((FreeHand)_selectedDrawable).drawPoint(mousePoint);
    }

    public void connectLines(Point mousePoint) { ((MultiLines)_selectedDrawable).drawPoint(mousePoint);}


    public void drawLine(Point mousePoint){
        Point p2 = new Point((int)mousePoint.getX() + 100, (int)mousePoint.getY()+100);
        _onScreen.add(new Line(p2, mousePoint));
        setMode(BoardMode.DEFAULT);
    }

    public void drawRectangle(Point mousePoint){
        _onScreen.add(new Rectangle(mousePoint, Const.Drawing.DEFAULTS_SIZE, Const.Drawing.DEFAULTS_SIZE+20));
        setMode(BoardMode.DEFAULT);
    }

    public void drawEllipse(Point mousePoint){
        _onScreen.add(new Ellipse(mousePoint, Const.Drawing.DEFAULTS_RADIUS + 2, Const.Drawing.DEFAULTS_RADIUS - 2));
        setMode(BoardMode.DEFAULT);
    }

    public void drawSquare(Point mousePoint){
        _onScreen.add(new Square(mousePoint, Const.Drawing.DEFAULTS_SIZE));
        setMode(BoardMode.DEFAULT);
    }

    public void drawCircle(Point mousePoint){
        _onScreen.add(new Circle(mousePoint, Const.Drawing.DEFAULTS_RADIUS));
        setMode(BoardMode.DEFAULT);
    }

    public void drawPolygon(Point mousePoint, int n){
        _onScreen.add(new Polygon(mousePoint, n));
        setMode(BoardMode.DEFAULT);
    }
}
