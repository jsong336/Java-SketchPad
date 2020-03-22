package bin.Control.Drawing;

import java.awt.*;
import java.awt.geom.Line2D;

public class LineDrawing extends AbstractDrawing {
    public int x1, x2, y1, y2;

    public LineDrawing(int x1, int x2, int y1, int y2){
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
    }

    @Override
    public Shape getShapeNow() {
        return new Line2D.Double(x1, y1, x2, y2);
    }

    @Override
    public void move(int newX, int newY) {
        x1 = newX;
        x2 = (newX - x1) + x2;
        y1 = newY;
        y2 = (newY - y1) + y1;
    }
}
