package bin.Model.Drawable;

import java.awt.*;

public class Circle extends Ellipse{
    public Circle(Point point, int r){
        super(point, r, r);
    }
    public Circle(Circle copy){
        super(copy);
        this.a = this.b;
    }
    @Override
    public void resize(Point mousePoint){
        int x = (int)mousePoint.getX();
        int y = (int)mousePoint.getY();
        a = (int)Math.sqrt(x * x + y * y);
        b = a;
    }

    @Override
    public String toString() {
        return "Ellipse{" +
                "_borderColor=" + _borderColor +
                ", _filledColor=" + _filledColor +
                ", _isFilled=" + _isFilled +
                ", _isSelected=" + _isSelected +
                ", a=" + a +
                ", b=" + b +
                ", x=" + centerX +
                ", y=" + centerY +
                '}';
    }
}