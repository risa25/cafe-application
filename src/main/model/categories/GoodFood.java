package model.categories;

import model.Cafe;
import model.Event;
import model.EventLog;
import model.ListOfCafe;

import java.util.ArrayList;
import java.util.List;

//Create list of cafes with good food
public class GoodFood extends ListOfCafe {

    private List<GoodFood> goodFoods;

    //EFFECTS: Create new list for good food
    public GoodFood() {
        super("Cafe List");
        goodFoods = new ArrayList<>();
    }

    //MODIFIES: this
    //EFFECTS: adds the cafe to this list if it has good food
    @Override
    public void addCafe(Cafe cafe) {
        if (cafe.isGoodFood()) {
            super.addCafe(cafe);
            EventLog.getInstance().logEvent(new Event("Cafe added to good food list."));
        }
    }
}
