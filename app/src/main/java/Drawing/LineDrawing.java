package Drawing;

import java.awt.*;
import java.awt.geom.Line2D;

public class LineDrawing extends AbstractDrawing {
    public Color color;
    public double x1;
    public double x2;
    public double y1;
    public double y2;

    public LineDrawing(double x1, double x2, double y1, double y2){
        color = super.borderColor;
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
    }

    @Override
    public Shape getShapeNow() {
        return new Line2D.Double(x1, y1, x2, y2);
    }
}
