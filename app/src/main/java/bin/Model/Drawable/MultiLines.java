package bin.Model.Drawable;

import bin.Const;

import java.awt.*;
import java.awt.geom.Line2D;

public class MultiLines extends AbstractDrawable{
    public int[] xs = new int[1000];
    public int[] ys = new int[1000];
    public int i = 0;

    public MultiLines(Point startPoint){
        super();
        drawPoint(startPoint);
    }

    public MultiLines(MultiLines copy){
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
        System.out.println();
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
        for(int j=0;j<i-1;j++)
            g2.draw(new Line2D.Double(xs[j], ys[j], xs[j+1], ys[j+1]));
    }

    @Override
    public boolean isClicked(Point mousePoint) {
        int x = (int)mousePoint.getX();
        int y = (int)mousePoint.getY();

        int boxX = x - Const.Drawing.LINE_MARGIN / 2;
        int boxY = y - Const.Drawing.LINE_MARGIN / 2;

        int width = Const.Drawing.LINE_MARGIN;
        int height = Const.Drawing.LINE_MARGIN;

        for(int j=0;j<i-1;j++){
            Shape line =  new Line2D.Double(xs[j], ys[j], xs[j+1], ys[j+1]);
            if(line.intersects(boxX, boxY, width, height))
                return true;
        }
        return false;
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
        // TODO
    }
}
