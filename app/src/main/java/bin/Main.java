package bin;

import bin.Control.MainController;

public class Main {
    public static void main(String[] args){
        javax.swing.SwingUtilities.invokeLater(() -> {
            MainController mainController = new MainController();
        });
    }
}
