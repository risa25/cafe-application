package model.categories;

import model.Cafe;
import model.Event;
import model.EventLog;
import model.ListOfCafe;

import java.util.ArrayList;
import java.util.List;

//Create list of good study spots
public class GoodStudySpot extends ListOfCafe {
    private List<GoodStudySpot> gss;

    //EFFECTS: Create list of good study spots as a filter for list of cafe
    public GoodStudySpot() {
        super("Cafe List");
        gss = new ArrayList<>();
    }

    //MODIFIES: this
    //EFFECTS: adds the cafe to this list if it is a good study spot
    @Override
    public void addCafe(Cafe cafe) {
        if (cafe.isGoodStudySpot()) {
            super.addCafe(cafe);
            EventLog.getInstance().logEvent(new Event("Cafe added to good study spot list."));
        }
    }
}
