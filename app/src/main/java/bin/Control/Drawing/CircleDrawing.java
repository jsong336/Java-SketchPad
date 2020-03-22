package bin.Control.Drawing;

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
