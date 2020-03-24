package bin.Control;

import bin.Model.BoardMode;
import bin.View.MainView;

public class MainController {
    public BoardController boardController;

    public MainController(){
        boardController = new BoardController();
        MainView mainView = new MainView(this);
        boardController.registerView(mainView.getBoardView());
   }

    public void setDrawMode(String cmd){
        switch (cmd){
            case "Line":
                boardController.setMode(BoardMode.LINE_DRAW);
                break;
            case "Freehand":
                boardController.setMode(BoardMode.FREE_HAND);
                break;
            case "Rectangle":
                boardController.setMode(BoardMode.RECTANGLE_DRAW);
                break;
            case "Ellipse":
                boardController.setMode(BoardMode.ELLIPSE_DRAW);
                break;
            case "Square":
                boardController.setMode(BoardMode.SQUARE_DRAW);
                break;
            case "Polygon":
                boardController.setMode(BoardMode.POLY_DRAW);
                break;
            case "Circle":
                boardController.setMode(BoardMode.CIRCLE_DRAW);
                break;

            case "Multilines":
                boardController.setMode(BoardMode.MULTILINES_DRAW);

            default:
                break;
        }
    }
}
