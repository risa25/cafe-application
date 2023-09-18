package persistence;

import model.Cafe;

import static org.junit.jupiter.api.Assertions.assertEquals;

//cite: JsonSerializationDemo
public class JsonTest {
    protected void checkCafe(String name, String address, int rating, boolean isGoodC, boolean isGoodS, boolean isGoodF,
                             Cafe c) {
        assertEquals(name, c.getName());
        assertEquals(address, c.getAddress());
        assertEquals(rating, c.getRating());
        assertEquals(isGoodC, c.isGoodCoffee());
        assertEquals(isGoodS, c.isGoodStudySpot());
        assertEquals(isGoodF, c.isGoodFood());
    }
}
