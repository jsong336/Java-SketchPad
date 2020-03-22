package bin.Control.Drawing;

import java.awt.*;

public class RectangleDrawing extends AbstractDrawing {
    double x, y, w, h;

    public RectangleDrawing(double x, double y, double w, double h){
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }
    @Override
    public Shape getShapeNow() {
        return new Rectangle((int)x, (int)y, (int)w, (int)h);
    }

    @Override
    public void move(int newX, int newY) {
        x = newX;
        y = newY;
    }
}
