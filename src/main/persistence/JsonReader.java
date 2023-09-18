package persistence;

import model.Cafe;
import model.ListOfCafe;
import model.categories.GoodCoffee;
import model.categories.GoodFood;
import model.categories.GoodStudySpot;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

//cite: JsonSerializationDemo
// reads the json data to load the cafe list
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads list from file and returns it;
    // throws IOException if an error occurs reading data from file
    public ListOfCafe read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseCafeList(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses cafelist from JSON object and returns it
    private ListOfCafe parseCafeList(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        ListOfCafe loc = new ListOfCafe(name);
        addCafeList(loc, jsonObject);
        return loc;
    }

    // MODIFIES: loc
    // EFFECTS: parses cafes from JSON object and adds them to list of cafe
    private void addCafeList(ListOfCafe loc, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("ListOfCafe");
        for (Object json : jsonArray) {
            JSONObject nextCafe = (JSONObject) json;
            addOneCafe(loc, nextCafe);
        }
    }


    // MODIFIES: loc
    // EFFECTS: parses one cafe from JSON object and adds it to the list of cafe
    private void addOneCafe(ListOfCafe loc, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        String address = jsonObject.getString("address");
        int rating = jsonObject.getInt("rating");
        boolean gc = jsonObject.getBoolean("isGoodCoffee");
        boolean gf = jsonObject.getBoolean("isGoodFood");
        boolean gss = jsonObject.getBoolean("isGoodStudySpot");
        Cafe cafe = new Cafe(name, address, rating, gc, gss, gf);
        loc.addCafe(cafe);
    }
}
