package model.categories;

import model.Cafe;
import model.Event;
import model.EventLog;
import model.ListOfCafe;

import java.util.ArrayList;
import java.util.List;

//List for good coffee
public class GoodCoffee extends ListOfCafe {

    private List<GoodCoffee> gc;

    //EFFECTS: Create new list for good coffee
    public GoodCoffee() {
        super("Cafe List");
        gc = new ArrayList<>();
    }

    //MODIFIES: this
    //EFFECTS: adds the cafe to this list if it has good coffee
    @Override
    public void addCafe(Cafe cafe) {
        if (cafe.isGoodCoffee()) {
            super.addCafe(cafe);
            EventLog.getInstance().logEvent(new Event("Cafe added to good coffee list."));
        }
    }
}
