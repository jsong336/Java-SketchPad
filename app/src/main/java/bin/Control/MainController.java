package bin.Control;

import bin.Model.BoardMode;
import bin.View.MainView;

import java.io.IOException;

/*
 * creation of this object will initiate the view, and associated controller
 * MainController object will call initiate different handlers if any menu events occurs
 */
public class MainController {
    /* delegate board controller*/
    public BoardController boardController;
    public FileController fileController;
    public MainView mainView;

    public MainController(){
        /* main controller will automatically create its associates */
        boardController = new BoardController();
        mainView = new MainView(this);
        boardController.registerView(mainView.getBoardView()); // register board
        fileController = new FileController();
   }

   /* ************************************************************************
    * called when event occurs from main menu
    * ************************************************************************/
    public void setDrawMode(String cmd){
        /* Draw Menu events */
        switch (cmd){
            case "Line":
                boardController.setBoardMode(BoardMode.LINE_DRAW);
                break;

            case "Freehand":
                boardController.setBoardMode(BoardMode.FREE_HAND);
                break;

            case "Rectangle":
                boardController.setBoardMode(BoardMode.RECTANGLE_DRAW);
                break;

            case "Ellipse":
                boardController.setBoardMode(BoardMode.ELLIPSE_DRAW);
                break;

            case "Square":
                boardController.setBoardMode(BoardMode.SQUARE_DRAW);
                break;

            case "Polygon":
                boardController.setBoardMode(BoardMode.POLY_DRAW);
                break;

            case "Circle":
                boardController.setBoardMode(BoardMode.CIRCLE_DRAW);
                break;

            case "Multilines":
                boardController.setBoardMode(BoardMode.MULTILINES_DRAW); // no break

            default:
                break;
        }
    }

    public void edit(String cmd){
        /* Edit Menu events */
        switch (cmd){
            case "Copy":
                boardController.copy();
                break;

            case "Paste":
                boardController.paste();
                break;

            case "Cut":
                boardController.copy();
                boardController.remove();
                break;

            case "Redo":
                boardController.redo();
                break;

            case "Undo":
                boardController.undo();
                break;

            default:
                break;
        }
    }

    public void fileControl(String cmd){
        /* File Menu events */
        switch (cmd){
            case "Open":
                try{
                    boardController.reset(fileController.load(mainView.getPath()));
                }
                catch (Exception e){
                    System.out.println(e);
                }
                break;

            case "Save":
                try{
                    fileController.save(mainView.createPath(), boardController.getOnScreen());
                }
                catch (IOException e){
                    System.out.println(e);
                }
                // unimplemented
                break;
            case "Exit":
                System.exit(0); // system terminates
                break;
            default:
                break;
        }
    }
}
