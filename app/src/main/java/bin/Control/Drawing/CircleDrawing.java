package bin.Control.Drawing;

import bin.Control.Const;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class CircleDrawing extends AbstractDrawing {
    public int radius;
    public int centerX;
    public int centerY;

    public CircleDrawing(int r, int x, int y){
        radius = r;
        centerX = x;
        centerY = y;
    }

    public CircleDrawing(CircleDrawing copy){
        this.radius = copy.radius;
        this.centerX = copy.centerX + Const.Drawing.COPY_OFFSET;
        this.centerY = copy.centerY + Const.Drawing.COPY_OFFSET;
    }

    @Override
    public Shape getShapeNow() {
        return new Ellipse2D.Double(centerX, centerY, radius, radius);
    }

    @Override
    public void move(int newX, int newY) {
        this.centerX = newX;
        this.centerY = newY;
    }
}
