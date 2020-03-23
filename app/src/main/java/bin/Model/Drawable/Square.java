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
}
