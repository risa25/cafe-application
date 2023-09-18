package persistence;

import org.json.JSONObject;

//cite: JsonSerializationDemo
//Writes file to store the data in the application
public interface Writable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}
