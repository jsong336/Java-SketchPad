package bin.Model;

import bin.Model.Drawable.AbstractDrawable;

import java.util.ArrayList;

public class BoardMemento {
    private ArrayList<AbstractDrawable> _onScreen = new ArrayList<>();

    public BoardMemento(ArrayList<AbstractDrawable> moment){
        for (AbstractDrawable obj: moment){
            _onScreen.add(AbstractDrawable.copyAsChild(obj));
        }
    }
    public ArrayList<AbstractDrawable> getOnScreenMoment(){
        return _onScreen;
    }
}
