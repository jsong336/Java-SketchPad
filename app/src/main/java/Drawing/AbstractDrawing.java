package Drawing;

import java.awt.*;

public abstract class AbstractDrawing {
    public boolean isFilled = false;
    public Color fillColor = Color.white;
    public Color borderColor = Color.black;

    public abstract Shape getShapeNow();
}
