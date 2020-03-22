package bin.Control.Drawing;

import bin.Control.Const;

import java.awt.*;

public class RectangleDrawing extends AbstractDrawing {
    double x, y, w, h;

    public RectangleDrawing(double x, double y, double w, double h){
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }

    public RectangleDrawing(RectangleDrawing copy){
        super(copy);
        this.x = copy.x + Const.Drawing.COPY_OFFSET;
        this.y = copy.y + Const.Drawing.COPY_OFFSET;
        this.w = copy.w;
        this.h = copy.h;
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
