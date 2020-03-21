package Drawing;

public abstract class AbstractDrawing {
    abstract void move();
    abstract void setColour();
    abstract void delete();
    abstract void copy();
    abstract void paste();
    abstract void draw();
    int posX;
    int posY;
}
