package bin.Model.Drawable;

import java.awt.*;

public class Square extends Rectangle{
    public Square(Point point, int l){
        super(point, l, l);
    }

    public Square(Square copy){
        super(copy);
        this.w = this.h;
    }

    @Override
    public void resize(Point mousePoint){
        int x = (int)mousePoint.getX();
        int y = (int)mousePoint.getY();
        w = (int)Math.sqrt(x*x + y*y);
        h = w;
    }
    @Override
    public String toString() {
        return "Square{" +
                "x=" + x +
                ", y=" + y +
                ", w=" + w +
                ", h=" + h +
                ", _borderColor=" + _borderColor +
                ", _filledColor=" + _filledColor +
                ", _isFilled=" + _isFilled +
                ", _isSelected=" + _isSelected +
                '}';
    }
}
