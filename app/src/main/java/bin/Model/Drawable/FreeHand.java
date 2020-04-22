package bin.Model.Drawable;

import bin.Const;

import java.awt.*;
import java.util.Arrays;

public class FreeHand extends AbstractDrawable {
    public int[] xs = new int[1000];
    public int[] ys = new int[1000];
    public int i = 0;

    public FreeHand(Point startPoint){
        super();
        drawPoint(startPoint);
    }

    public FreeHand(FreeHand copy){
        super(copy);
        this.i = copy.i;
        System.arraycopy(copy.xs, 0, xs, 0, 1000);
        System.arraycopy(copy.ys, 0, ys, 0, 1000);
        for(int j=0;j<i;j++){
            xs[j] += Const.Drawing.COPY_OFFSET;
            ys[j] += Const.Drawing.COPY_OFFSET;
        }
    }

    public void drawPoint(Point mousePoint){
        xs[i] = (int)mousePoint.getX();
        ys[i] = (int)mousePoint.getY();
        i++;
    }

    @Override
    public void drawInstance(Graphics2D g2) {
        Color fill = getFilledColor();
        if(fill!=null)
            g2.setPaint(fill);
        g2.setColor(getBorderColor());
        g2.drawPolyline(xs, ys, i);
    }

    @Override
    public boolean isClicked(Point mousePoint) {
        boolean inX = false;
        boolean inY = false;
        for(int j=0; j < i; j++){
            if(Math.abs(xs[j] -  mousePoint.getX()) < Const.Drawing.LINE_MARGIN) inX = true;
            if(Math.abs(ys[j] - mousePoint.getY()) < Const.Drawing.LINE_MARGIN) inY = true;
        }
        return inX&&inY;
    }

    @Override
    public void move(Point mousePoint) {
        int dx = (int)mousePoint.getX() - xs[0];
        int dy = (int)mousePoint.getY() - ys[0];
        for(int j=0;j < i;j++){
            xs[j] += dx;
            ys[j] += dy;
        }
    }

    @Override
    public void fill(Color fill){
        super.fill(fill);
        _borderColor = fill;
    }

    @Override
    public void resize(Point mousePoint) {
        // TODO: current version does not support free hand resize
    }

    @Override
    public String toString() {
        return "FreeHand{" +
                "xs=" + Arrays.toString(xs) +
                ", ys=" + Arrays.toString(ys) +
                ", i=" + i +
                ", _borderColor=" + _borderColor +
                ", _filledColor=" + _filledColor +
                ", _isFilled=" + _isFilled +
                ", _isSelected=" + _isSelected +
                '}';
    }
}