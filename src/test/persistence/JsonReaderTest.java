package persistence;

import model.Cafe;
import model.ListOfCafe;
import model.categories.GoodCoffee;
import model.categories.GoodFood;
import model.categories.GoodStudySpot;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

//cite: JsonSerializationDemo
public class JsonReaderTest extends JsonTest{
    ListOfCafe listOfCafe = new ListOfCafe("Cafe List");

    @Test
    void testReaderDoesNotExist() {
        JsonReader reader = new JsonReader("./data/doesNotExist.json");
        try {
            ListOfCafe listOfCafe = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyList() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyList.json");
        try {
            ListOfCafe listOfCafe = reader.read();
            assertEquals("Cafe List", listOfCafe.getName());
            assertEquals(0, listOfCafe.count());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testCafeListReader() {
        JsonReader reader = new JsonReader("./data/testCafeListReader.json");
        try {
            ListOfCafe loc = reader.read();
            assertEquals("Cafe List", loc.getName());
            List<Cafe> cafes = loc.getCafeList();
            assertEquals(2, cafes.size());
            checkCafe("cafe", "cafe", 10, true, true, false, cafes.get(0));
            checkCafe("cafe2", "cafe", 10, false, false, true, cafes.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }


    @Test
    void testCoffeeListReader() {
        JsonReader reader = new JsonReader("./data/testCoffeeListReader.json");
        try {
            ListOfCafe loc = reader.read();
            List<Cafe> cafes = loc.getCafeList();
            assertEquals(1, cafes.size());
            checkCafe("cafe", "cafe", 10, true, true, false, cafes.get(0));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testFoodListReader() {
        JsonReader reader = new JsonReader("./data/testFoodListReader.json");
        try {
            ListOfCafe loc = reader.read();
            List<Cafe> cafes = loc.getCafeList();
            assertEquals(1, cafes.size());
            checkCafe("cafe2", "cafe", 10, false, false, true, cafes.get(0));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }


    @Test
    void testStudyListReader() {
        JsonReader reader = new JsonReader("./data/testStudyListReader.json");
        try {
            ListOfCafe loc = reader.read();
            List<Cafe> cafes = loc.getCafeList();
            assertEquals(1, cafes.size());
            checkCafe("cafe", "cafe", 10, true, true, false, cafes.get(0));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }


}
