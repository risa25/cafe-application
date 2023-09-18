package model;

import org.json.JSONObject;

//Create cafe
public class Cafe {
    private String name;
    private String address;
    private int rating;
    private boolean isGoodCoffee;
    private boolean isGoodStudySpot;
    private boolean isGoodFood;
    ListOfCafe loc = new ListOfCafe("Cafe List");


     //REQUIRES: name, address has a non-zero length, rating <= 10, three booleans for
     //          goodCoffee, goodStudy and food
     //EFFECTS: all inputs are assigned
    public Cafe(String name, String address, int rating, boolean goodCoffee, boolean goodStudy, boolean food) {
        this.name = name;
        this.address = address;
        this.rating = rating;
        this.isGoodCoffee = goodCoffee;
        this.isGoodStudySpot = goodStudy;
        this.isGoodFood = food;
    }

    //getters
    public int getRating() {
        return rating;
    }

    public String getAddress() {
        return address;
    }

    public String getName() {
        return name;
    }

    public boolean isGoodCoffee() {
        return isGoodCoffee;
    }

    public boolean isGoodFood() {
        return isGoodFood;
    }

    public boolean isGoodStudySpot() {
        return isGoodStudySpot;
    }

    // REQUIRES: goodCoffee != null
    // MODIFIES: this
    // EFFECTS: sets goodCoffee to true or false
    public void setGoodCoffee(boolean goodCoffee) {
        isGoodCoffee = goodCoffee;
    }

    // REQUIRES: goodFood != null
    // MODIFIES: this
    // EFFECTS: sets goodCoffee to true or false
    public void setGoodFood(boolean goodFood) {
        isGoodFood = goodFood;
    }

    // REQUIRES: goodStudySpot != null
    // MODIFIES: this
    // EFFECTS: sets goodCoffee to true or false
    public void setGoodStudySpot(boolean goodStudySpot) {
        isGoodStudySpot = goodStudySpot;
    }

    // REQUIRES: address != null
    // MODIFIES: this
    // EFFECTS: sets goodCoffee to true or false
    public void setAddress(String address) {
        this.address = address;
    }

    // REQUIRES: name != null
    // MODIFIES: this
    // EFFECTS: sets name to given name
    public void setName(String name) {
        this.name = name;
    }

    // REQUIRES: rating != null and rating<=10
    // MODIFIES: this
    // EFFECTS: sets rating to given number
    public void setRating(int rating) {
        this.rating = rating;
    }


    //EFFECTS: create a cafe and inputs it as json data
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("address", address);
        json.put("rating", rating);
        json.put("isGoodCoffee", isGoodCoffee);
        json.put("isGoodStudySpot", isGoodStudySpot);
        json.put("isGoodFood", isGoodFood);
        EventLog.getInstance().logEvent(new Event("Cafe is saved onto system."));
        return json;
    }

}
