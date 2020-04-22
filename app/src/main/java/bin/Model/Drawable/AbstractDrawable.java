package bin.Model.Drawable;

import java.awt.*;
import java.io.Serializable;

public abstract class AbstractDrawable implements Serializable {
    protected Color _borderColor = Color.black;
    protected Color _filledColor = Color.white;
    protected boolean _isFilled = false;
    protected boolean _isSelected = false;

    protected AbstractDrawable(){}

    protected AbstractDrawable(AbstractDrawable copy){
        _borderColor = copy._borderColor;
        _isFilled = copy._isFilled;
        _filledColor = copy._filledColor;
    }

    public void fill(Color fill){
        _isFilled = true;
        _filledColor = fill;
    }

    protected Color getBorderColor(){
        return _isSelected ? Color.red :  _borderColor;
    }

    protected Color getFilledColor(){
        return _isFilled ? _filledColor : null;
    }

    public void selectThis(){ _isSelected = true; }

    public void unSelectThis(){ _isSelected = false; }

    public abstract boolean isClicked(Point mousePoint);

    public abstract void drawInstance(Graphics2D g2);

    public abstract void move(Point mousePoint);

    public abstract void resize(Point mousePoint);

    public static AbstractDrawable copyAsChild(AbstractDrawable copy){
        if(copy instanceof Circle){
            return new Circle((Circle) copy);
        }

        if(copy instanceof Square){
            return new Square((Square)copy);
        }

        if(copy instanceof FreeHand){
            return new FreeHand((FreeHand) copy);
        }

        if(copy instanceof Line){
            return new Line((Line) copy);
        }

        if(copy instanceof Ellipse){
            return new Ellipse((Ellipse) copy);
        }

        if(copy instanceof Rectangle){
            return new Rectangle((Rectangle) copy);
        }

        if(copy instanceof Polygon){
            return new Polygon((Polygon) copy);
        }

        if(copy instanceof MultiLines){
            return new MultiLines((MultiLines) copy);
        }
        return null;
    }

    @Override
    public String toString() {
        return "AbstractDrawable{" +
                "_borderColor=" + _borderColor +
                ", _filledColor=" + _filledColor +
                ", _isFilled=" + _isFilled +
                ", _isSelected=" + _isSelected +
                '}';
    }
}
