package bin.Model.Drawable;

import bin.Const;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class Ellipse extends AbstractDrawable {
    int a, b, centerX, centerY;

    public Ellipse(Point mousePoint, int a, int b){
        super();
        this.a = a;
        this.b = b;
        this.centerX = (int)mousePoint.getX();
        this.centerY = (int)mousePoint.getY();
    }

    public Ellipse(Ellipse copy){
        super(copy);
        this.a = copy.a;
        this.b = copy.b;
        this.centerX = copy.centerX + Const.Drawing.COPY_OFFSET;
        this.centerY = copy.centerY + Const.Drawing.COPY_OFFSET;
    }
    @Override
    public void drawInstance(Graphics2D g2) {
        Shape newShape = new Ellipse2D.Double(centerX, centerY, a, b);
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
        Shape instance = new Ellipse2D.Double(centerX, centerY, a, b);
        return instance.contains(mousePoint);
    }

    @Override
    public void move(Point mousePoint) {
        this.centerX = (int)mousePoint.getX();
        this.centerY = (int)mousePoint.getY();
    }

    @Override
    public void resize(Point mousePoint) {
        a = (int)mousePoint.getX();
        b = (int)mousePoint.getY();
    }
}