package bin.Control.Drawing;

import bin.Control.Const;

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

    public LineDrawing(LineDrawing copy){
        super(copy);
        this.x1 = copy.x1 + Const.Drawing.COPY_OFFSET;
        this.x2 = copy.x2 + Const.Drawing.COPY_OFFSET;
        this.y1 = copy.y1 + Const.Drawing.COPY_OFFSET;
        this.y2 = copy.y2 + Const.Drawing.COPY_OFFSET;
    }

    @Override
    public Shape getShapeNow() {
            return new Line2D.Double(x1, y1, x2, y2);
    }

    @Override
    public void move(int newX, int newY) {
        x2 += newX - x1;
        x1 = newX;
        y2 += newY - y1;
        y1 = newY;
    }

    public static boolean isClicked(int x, int y, Line2D.Double line) {
        int boxX = x - Const.Drawing.LINE_MARGIN / 2;
        int boxY = y - Const.Drawing.LINE_MARGIN / 2;

        int width = Const.Drawing.LINE_MARGIN;
        int height = Const.Drawing.LINE_MARGIN;

        return line.intersects(boxX, boxY, width, height);
    }
}
