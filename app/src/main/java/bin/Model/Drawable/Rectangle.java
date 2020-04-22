package bin.Model.Drawable;

import bin.Const;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class Rectangle extends AbstractDrawable {
    int x, y, w, h;

    public Rectangle(Point mousePoint, int w, int h){
        super();
        x = (int)mousePoint.getX();
        y = (int)mousePoint.getY();
        this.w = w;
        this.h = h;
    }

    public Rectangle(Rectangle copy){
        super(copy);
        this.x = copy.x + Const.Drawing.COPY_OFFSET;
        this.y = copy.y + Const.Drawing.COPY_OFFSET;
        this.w = copy.w;
        this.h = copy.h;
    }

    @Override
    public void drawInstance(Graphics2D g2) {
        Shape newShape = new Rectangle2D.Double(x, y, w, h);
        Color fill = getFilledColor();
        if(fill!=null){
            g2.setPaint(fill);
            g2.fill(newShape);
        }
        g2.setColor(getBorderColor());
        g2.draw(newShape);
    }

    @Override
    public boolean isClicked(Point mousePoint) {
        Shape instance = new Rectangle2D.Double(x, y, w, h);
        return instance.contains(mousePoint);
    }

    @Override
    public void move(Point mousePoint) {
        x = (int)mousePoint.getX();
        y = (int)mousePoint.getY();
    }

    @Override
    public void resize(Point mousePoint) {
        w = (int)mousePoint.getX();
        h = (int)mousePoint.getY();
    }

    @Override
    public String toString() {
        return "Rectangle{" +
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