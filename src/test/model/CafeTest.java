package model;

import model.categories.GoodCoffee;
import model.categories.GoodFood;
import model.categories.GoodStudySpot;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ui.CafeApplication;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CafeTest {
    Cafe c1;
    Cafe c2;
    ListOfCafe loc;
    GoodCoffee gc;
    GoodFood gf;
    GoodStudySpot gs;

    @BeforeEach
    void runBefore() {
        loc = new ListOfCafe("Cafe List");
        c1 = new Cafe("Caf", "UBC",10, false, false, true);
        c2 = new Cafe("R", "Lo", 5, true, true, false);
        gc = new GoodCoffee();
        gf = new GoodFood();
        gs = new GoodStudySpot();
    }

    @Test
    void testConstructor() {
        assertEquals("Cafe List", loc.getName());
        assertEquals("UBC", c1.getAddress());
        assertEquals("Caf", c1.getName());
        assertEquals(10, c1.getRating());
        assertFalse(c1.isGoodCoffee());
        assertTrue(c1.isGoodFood());
        assertFalse(c1.isGoodStudySpot());

        c1.setAddress("UBC1");
        assertEquals("UBC1", c1.getAddress());
        c1.setName("Cafe");
        assertEquals("Cafe", c1.getName());
        c1.setRating(9);
        assertEquals(9, c1.getRating());
        c1.setGoodCoffee(true);
        assertTrue(c1.isGoodCoffee());
        c1.setGoodFood(false);
        assertFalse(c1.isGoodFood());
        c1.setGoodStudySpot(true);
        assertTrue(c1.isGoodStudySpot());
    }


    @Test
    void testList() {
        loc.addCafe(c1);
        assertTrue(loc.contains(c1));

        gc.addCafe(c2);
        gc.addCafe(c1);
        assertTrue(gc.contains(c2));
        assertFalse(gc.contains(c1));
        assertEquals(1, gc.getCafeList().size());

        gs.addCafe(c1);
        gs.addCafe(c2);
        assertTrue(gc.contains(c2));
        assertFalse(gc.contains(c1));
        assertEquals(1, gc.getCafeList().size());

        gf.addCafe(c1);
        gf.addCafe(c2);
        assertTrue(gf.contains(c1));
        assertFalse(gf.contains(c2));
        assertEquals(1, gc.getCafeList().size());

        //loc.getCafeList();
        assertEquals(1, loc.getCafeList().size());

        loc.setCafeList(gc.getCafeList());
        assertEquals(1, loc.getCafeList().size());

        //printList(loc.getCafeList());


    }

}