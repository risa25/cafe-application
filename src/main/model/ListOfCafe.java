package model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

//List of Cafe using Cafe class
public class ListOfCafe {
    private String name;
    private List<Cafe> cafeList;

    //REQUIRES: Name of list
    //EFFECTS:Create list of cafes
    public ListOfCafe(String name) {
        this.name = name;
        cafeList = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    //MODIFIES: this
    //EFFECTS: adds cafe to the cafe list
    public void addCafe(Cafe cafe) {
        cafeList.add(cafe);
        EventLog.getInstance().logEvent(new Event("This cafe has been added to the CafeList"));
    }

    //getter
    public List<Cafe> getCafeList() {
        return cafeList;
    }

    //MODIFIES: this
    //EFFECTS: sets a cafe list to a given cafe list
    public void setCafeList(List<Cafe> cafeList) {
        this.cafeList = cafeList;
    }

    //EFFECTS: checks whether a cafe is contained in a list of cafe
    public boolean contains(Cafe c) {
        return cafeList.contains(c);
    }

    //EFFECTS: returns size of the cafe list
    public int count() {
        return cafeList.size();
    }

    // EFFECTS: returns cafe in the list as a JSON array
    public JSONArray listJson() {
        JSONArray jsonArray = new JSONArray();

        for (Cafe c : cafeList) {
            jsonArray.put(c.toJson());
        }
        return jsonArray;

    }

    //EFFECTS: puts object into Json data
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", "Cafe List");
        json.put("ListOfCafe", listJson());
        return json;
    }


}
