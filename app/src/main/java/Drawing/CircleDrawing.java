package Drawing;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class CircleDrawing extends AbstractDrawing {
    public double radius = 0;
    public double centerX = 0;
    public double centerY = 0;
    public CircleDrawing(double r, double x, double y){
        radius = r;
        centerX = x;
        centerY = y;
    }

    @Override
    public Shape getShapeNow() {
        return new Ellipse2D.Double(centerX, centerY, radius, radius);
    }
}
