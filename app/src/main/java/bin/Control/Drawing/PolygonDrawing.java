package bin.Control.Drawing;

import bin.Control.Const;
import java.awt.*;

public class PolygonDrawing extends AbstractDrawing{
    int n;
    int[] pointXs;
    int[] pointYs;
    int centerX;
    int centerY;
    public PolygonDrawing(int n, int x, int y){
        if(n>0){
            this.n = n;
            pointXs = new int[n];
            pointYs = new int[n];
            move(x, y);
        }
        else{
            System.out.println("Polygon with n<=0");
        }
    }

    @Override
    public Shape getShapeNow() {
        return new Polygon(pointXs, pointYs, n);
    }

    @Override
    public void move(int newX, int newY) {
        centerX = newX;
        centerY = newY;
        for(int i = 0; i < n; i++){
            pointXs[i] = (int)(Const.Drawing.DEFAULTS_RADIUS * Math.cos(2*i*Math.PI/n)) + centerX;
            pointYs[i] = (int)(Const.Drawing.DEFAULTS_RADIUS * Math.sin(2*i*Math.PI/n)) + centerY;
        }
    }
}
