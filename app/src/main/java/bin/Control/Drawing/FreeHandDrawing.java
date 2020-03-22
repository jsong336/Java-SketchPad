package bin.Control.Drawing;

import bin.Control.Const;
import java.awt.*;
import java.awt.geom.Ellipse2D;

public class FreeHandDrawing extends AbstractDrawing{
    public int[] xs = new int[1000];
    public int[] ys = new int[1000];
    public int i = 0;
    public FreeHandDrawing(int startX, int startY){
        drawPoint(startX, startY);
    }

    public FreeHandDrawing(FreeHandDrawing copy){
        super(copy);
        this.i = copy.i;
        System.arraycopy(copy.xs, 0, xs, 0, 1000);
        System.arraycopy(copy.ys, 0, ys, 0, 1000);
        for(int j=0;j<i;j++){
            xs[j] += Const.Drawing.COPY_OFFSET;
            ys[j] += Const.Drawing.COPY_OFFSET;
        }
    }

    public void drawPoint(int x, int y){
        xs[i] = x;
        ys[i] = y;
        i++;
    }

    @Override
    public Shape getShapeNow() {
        return new Ellipse2D.Double(xs[i], ys[i], 1, 1);
    }

    @Override
    public void move(int newX, int newY) {
        int dx = newX - xs[0];
        int dy = newY - ys[0];
        for(int j=0;j < i;j++){
            xs[j] += dx;
            ys[j] += dy;
        }
    }

    public static boolean isClicked(int x, int y, FreeHandDrawing stroke) {
        boolean inX = false;
        boolean inY = false;
        System.out.println(stroke.i);
        for(int j=0; j < stroke.i; j++){
            if(Math.abs(stroke.xs[j] -  x) < Const.Drawing.LINE_MARGIN) inX = true;
            if(Math.abs(stroke.ys[j] - y) < Const.Drawing.LINE_MARGIN) inY = true;
        }

        return inX&&inY;
    }
}