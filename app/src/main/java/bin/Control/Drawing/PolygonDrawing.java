package bin.Control.Drawing;

import bin.Control.Const;
import java.awt.*;

public class PolygonDrawing extends AbstractDrawing{
    int n, centerX, centerY, radius;
    int[] pointXs, pointYs;

    public PolygonDrawing(int n, int x, int y){
        if(n>0){
            this.n = n;
            pointXs = new int[n];
            pointYs = new int[n];
            radius = Const.Drawing.DEFAULTS_RADIUS;
            move(x, y);
        }
        else{
            System.out.println("Polygon with n<=0");
        }
    }

    public PolygonDrawing(PolygonDrawing copy){
        super(copy);
        this.n  = copy.n;
        this.radius = copy.radius;
        this.pointXs = new int[n];
        this.pointYs = new int[n];
        move(centerX + 2, centerY + 2);
    }

    @Override
    public Shape getShapeNow() {
        return new Polygon(pointXs, pointYs, n);
    }

    @Override
    public void move(int newX, int newY) {
        centerX = newX;
        centerY = newY;
        for (int i = 0; i < n; i++) {
            pointXs[i] = (int) (radius * Math.cos(2 * i * Math.PI / n)) + centerX;
            pointYs[i] = (int) (radius * Math.sin(2 * i * Math.PI / n)) + centerY;
        }
    }

    @Override
    public void resize(int mouseX, int mouseY) {
        radius = (int)Math.sqrt(mouseX * mouseX + mouseY * mouseY);
    }
}
