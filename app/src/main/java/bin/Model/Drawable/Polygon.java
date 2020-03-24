package bin.Model.Drawable;

import bin.Const;

import java.awt.*;

public class Polygon extends AbstractDrawable{
    int n, centerX, centerY, radius;
    int[] pointXs, pointYs;

    public Polygon(Point mousePoint, int n){
        super();
        if(n>0){
            this.n = n;
            pointXs = new int[n];
            pointYs = new int[n];
            radius = Const.Drawing.DEFAULTS_RADIUS;
            move(mousePoint);
        }
        else{
            System.out.println("Polygon with n<=0");
        }
    }

    public Polygon(Polygon copy){
        super(copy);
        this.n  = copy.n;
        this.radius = copy.radius;
        this.pointXs = new int[n];
        this.pointYs = new int[n];
        move(new Point(centerX + 2, centerY + 2));
    }

    @Override
    public void drawInstance(Graphics2D g2) {
        Shape newShape = new java.awt.Polygon(pointXs, pointYs, n);
        Color fill = getFilledColor();
        if(fill!=null){
            g2.setPaint(fill);
            g2.fill(newShape);
        }
        g2.setColor(getBorderColor());
        g2.draw(newShape);
    }

    @Override
    public boolean isClicked(Point mousePoint) {
        Shape instance = new java.awt.Polygon(pointXs, pointYs, n);
        return instance.contains(mousePoint);
    }

    @Override
    public void move(Point mousePoint) {
        centerX = (int)mousePoint.getX();
        centerY = (int)mousePoint.getY();

        for (int i = 0; i < n; i++) {
            pointXs[i] = (int) (radius * Math.cos(2 * i * Math.PI / n)) + centerX;
            pointYs[i] = (int) (radius * Math.sin(2 * i * Math.PI / n)) + centerY;
        }
    }

    @Override
    public void resize(Point mousePoint) {
        double x = mousePoint.getX();
        double y = mousePoint.getY();
        radius = (int)Math.sqrt(x*x + y*y);
    }
}
