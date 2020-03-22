package bin.Control.Drawing;

import bin.Control.Const;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class EllipseDrawing extends AbstractDrawing{
    int a;
    int b;
    int centerX;
    int centerY;

    public EllipseDrawing(int x, int y, int a, int b){
        this.a = a;
        this.b = b;
        this.centerX = x;
        this.centerY = y;
    }

    public EllipseDrawing(EllipseDrawing copy){
        this.a = copy.a;
        this.b = copy.b;
        this.centerX = copy.centerX + Const.Drawing.COPY_OFFSET;
        this.centerY = copy.centerY + Const.Drawing.COPY_OFFSET;
    }

    @Override
    public Shape getShapeNow() {
        return new Ellipse2D.Double(centerX, centerY, a, b);
    }

    @Override
    public void move(int newX, int newY) {
        this.centerX = newX;
        this.centerY = newY;
    }

    @Override
    public void resize(int mouseX, int mouseY) {
        a = mouseX;
        b = mouseY;
    }
}
