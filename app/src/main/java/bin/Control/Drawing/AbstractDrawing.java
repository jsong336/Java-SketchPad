package bin.Control.Drawing;

import java.awt.*;

public abstract class AbstractDrawing {
    private boolean isFilled = false;
    private Color fillColor = Color.white;
    private Color borderColor = Color.black;
    private static AbstractDrawing selected;
    public static boolean isDragging = false;
    public static AbstractDrawing getSelected(){
        return AbstractDrawing.selected;
    }

    public abstract Shape getShapeNow();
    public abstract void move(int newX, int newY);

    public Color getFillColor() {
        return fillColor;
    }

    public boolean isSelected(){
        return selected == this;
    }

    public Color getBorderColor(){
        return isSelected() ? Color.red : borderColor;
    }

    public void fill(Color fillColor){
        this.fillColor = fillColor;
    }

    public void selectThis(){
        selected = this;
    }

    public static void unSelect(){
        selected = null;
    }
}
