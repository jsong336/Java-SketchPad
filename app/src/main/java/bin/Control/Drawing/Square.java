package bin.Control.Drawing;

import bin.Control.Const;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class Square extends AbstractDrawing{
    int x, y, l;

    public Square(Square copy){
        x = copy.x + Const.Drawing.COPY_OFFSET;
        y = copy.y + Const.Drawing.COPY_OFFSET;
        l = copy.l;
    }
    public Square(int x, int y, int l) {
        this.x = x;
        this.y = y;
        this.l = l;
    }

    @Override
    public Shape getShapeNow() {
        return new Rectangle2D.Double(x, y, l, l);
    }

    @Override
    public void move(int newX, int newY) {
        this.x = newX;
        this.y = newY;
    }

    @Override
    public void resize(int mouseX, int mouseY) {
        l = (int)Math.sqrt(mouseX*mouseX + mouseY * mouseY);
    }
}
