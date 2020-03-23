package bin.Model.Drawable;

import bin.Const;

import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

public class Line extends AbstractDrawable{
    public int x1, x2, y1, y2;

    public Line(Point start,  Point end){
        super();
        this.x1 = (int)start.getX();
        this.x2 = (int)start.getY();
        this.y1 = (int)end.getX();
        this.y2 = (int)end.getY();
    }

    public Line(Line copy){
        super(copy);
        this.x1 = copy.x1 + Const.Drawing.COPY_OFFSET;
        this.x2 = copy.x2 + Const.Drawing.COPY_OFFSET;
        this.y1 = copy.y1 + Const.Drawing.COPY_OFFSET;
        this.y2 = copy.y2 + Const.Drawing.COPY_OFFSET;
    }

    @Override
    public void drawInstance(Graphics2D g2) {
        Shape newShape = new Line2D.Double(x1, y1, x2, y2);
        Color fill = getFilledColor();
        if(fill!=null){
            g2.setPaint(fill);
            g2.fill(newShape);
        }
        if(_isSelected){
            g2.setColor(Color.red);
        }
        g2.draw(newShape);
    }

    @Override
    public boolean isClicked(Point mousePoint) {
        int x = (int)mousePoint.getX();
        int y = (int)mousePoint.getY();

        int boxX = x - Const.Drawing.LINE_MARGIN / 2;
        int boxY = y - Const.Drawing.LINE_MARGIN / 2;

        int width = Const.Drawing.LINE_MARGIN;
        int height = Const.Drawing.LINE_MARGIN;

        Shape momentOfMe = new Line2D.Double(x1, y1, x2, y2);
        return momentOfMe.intersects(boxX, boxY, width, height);

    }

    @Override
    public void move(Point mousePoint) {
        x2 += mousePoint.getX() - x1;
        x1 = (int)mousePoint.getX();
        y2 += mousePoint.getY() - y1;
        y1 = (int)mousePoint.getY();
    }

    @Override
    public void resize(Point mousePoint) {
        x2 = (int)mousePoint.getX();
        y2 = (int)mousePoint.getY();
    }
}