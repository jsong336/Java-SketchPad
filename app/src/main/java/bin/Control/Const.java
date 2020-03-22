package bin.Control;

public final class Const {
    private Const() {} // prevent instantiation

    /** bin.Control.Main constants  */
    public static final class Main {
        public static final String TITLE = "Sketch Pad";
        public static final int HEIGHT = 500;
        public static final int WIDTH = 500;
    }

    public static final class Drawing{
        public static final int DEFAULTS_POS_X = 10;
        public static final int DEFAULTS_POS_Y = 10;
        public static final int DEFAULTS_RADIUS = 30;
        public static final int DEFAULTS_SIZE = 50;
        public static final int LINE_MARGIN = 5;
    }
}