package bin.Control.Drawing;

import java.awt.*;

public abstract class AbstractDrawing {
    private static AbstractDrawing selected;
    public static boolean isDragging = false;
    public static AbstractDrawing getSelected() { return AbstractDrawing.selected; }
    public static void unSelect(){ selected = null; }

    public abstract Shape getShapeNow();
    public abstract void move(int newX, int newY);

    private boolean isFilled = false;
    private Color fillColor = Color.white;
    private Color borderColor = Color.black;

    protected AbstractDrawing(){}

    protected AbstractDrawing(AbstractDrawing copy){
        this.isFilled = copy.isFilled;
        this.fillColor = copy.fillColor;
        this.borderColor = copy.borderColor;
    }

    public static AbstractDrawing copyAsChild(AbstractDrawing copy){
        if(copy instanceof CircleDrawing){
            return new CircleDrawing((CircleDrawing) copy);
        }

        if(copy instanceof EllipseDrawing){
            return new EllipseDrawing((EllipseDrawing) copy);
        }

        if(copy instanceof LineDrawing){
            return new LineDrawing((LineDrawing) copy);
        }

        if(copy instanceof PolygonDrawing){
            return new PolygonDrawing((PolygonDrawing) copy);
        }

        if(copy instanceof RectangleDrawing){
            return new RectangleDrawing((RectangleDrawing) copy);
        }
        if(copy instanceof Square){
            return new Square((Square)copy);
        }
        if(copy instanceof FreeHandDrawing){
            return new FreeHandDrawing((FreeHandDrawing) copy);
        }
        return null;
    }

    public Color getFillColor() {
        return fillColor;
    }

    public boolean isFilled(){
        return isFilled;
    }
    public Color getBorderColor(){
        return isSelected() ? Color.red : borderColor;
    }

    public boolean isSelected(){
        return selected == this;
    }

    public void selectThis(){
        selected = this;
    }

    public void fill(Color fillColor){
        this.fillColor = fillColor;
        this.isFilled = true;
    }

    public abstract void resize(int mouseX, int mouseY);
}
