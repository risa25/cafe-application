package persistence;

import model.Cafe;
import model.ListOfCafe;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

//cite: JsonSerializationDemo
public class JsonWriterTest extends JsonTest{
    ListOfCafe listOfCafe;

    @BeforeEach
    void runBefore() {
        listOfCafe = new ListOfCafe("Cafe List");
    }


    @Test
    void testWriterInvalidFile() {
        try {
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyList() {
        try {
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyList.json");
            writer.open();
            writer.write(listOfCafe);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyList.json");
            listOfCafe = reader.read();
            assertEquals("Cafe List", listOfCafe.getName());
            assertEquals(0, listOfCafe.count());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralWorkroom() {
        try {
            listOfCafe.addCafe(new Cafe("cafe", "cafe", 10, true, true, false));
            listOfCafe.addCafe(new Cafe("cafe2", "cafe", 10, false, false, true));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralList.json");
            writer.open();
            writer.write(listOfCafe);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralList.json");
            listOfCafe = reader.read();
            assertEquals("Cafe List", listOfCafe.getName());
            List<Cafe> cafes = listOfCafe.getCafeList();
            assertEquals(2, cafes.size());
            checkCafe("cafe", "cafe", 10, true, true, false, cafes.get(0));
            checkCafe("cafe2", "cafe", 10, false, false, true, cafes.get(1));
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
