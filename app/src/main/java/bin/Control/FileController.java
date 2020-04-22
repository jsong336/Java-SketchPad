package bin.Control;

import bin.Model.Drawable.AbstractDrawable;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileController {

    void save(String path, List<AbstractDrawable> onScreen) throws IOException {

        FileOutputStream fs = new FileOutputStream(path);
        ObjectOutputStream os = new ObjectOutputStream(fs);

        //ObjectOutputStream.writeObject(object) inherently writes binary
        os.writeObject(onScreen);

        os.close();
        fs.close();
    }

    List<AbstractDrawable> load(String path) throws IOException, ClassNotFoundException {
        FileInputStream fi = new FileInputStream(path);
        ObjectInputStream os = new ObjectInputStream(fi);
        ArrayList<AbstractDrawable> onScreen = ((ArrayList<AbstractDrawable>)os.readObject());
        return onScreen;
    }
}
