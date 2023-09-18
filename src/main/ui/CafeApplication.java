package ui;

import model.Cafe;
import model.ListOfCafe;
import model.categories.GoodCoffee;
import model.categories.GoodFood;
import model.categories.GoodStudySpot;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

//Runs the Cafe Application
public class CafeApplication {
    /*
    private static final String JSON_STORE = "./data/cafelist.json";
    private static final String JSON_STORE_C = "./data/goodCoffee.json";
    private static final String JSON_STORE_F = "./data/goodFood.json";
    private static final String JSON_STORE_S = "./data/goodStudy.json";

    private String name;
    private String address;
    private int rating;
    private boolean isGoodC;
    private boolean isGoodF;
    private boolean isGoodS;
    private Scanner input;
    private boolean isRunning;
    ListOfCafe loc;
    GoodFood goodFood;
    GoodCoffee goodCoffee;
    GoodStudySpot goodStudySpot;

    private JsonWriter jsonWriter;
    private JsonWriter jsonWriterC;
    private JsonWriter jsonWriterF;
    private JsonWriter jsonWriterS;
    private JsonReader jsonReader;
    private JsonReader jsonReaderC;
    private JsonReader jsonReaderF;
    private JsonReader jsonReaderS;

    //EFFECTS: runs the application
    public CafeApplication() {
        loc = new ListOfCafe("Cafe List");
        goodFood = new GoodFood();
        goodCoffee = new GoodCoffee();
        goodStudySpot = new GoodStudySpot();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonWriterC = new JsonWriter(JSON_STORE_C);
        jsonWriterF = new JsonWriter(JSON_STORE_F);
        jsonWriterS = new JsonWriter(JSON_STORE_S);
        jsonReader = new JsonReader(JSON_STORE);
        jsonReaderC = new JsonReader(JSON_STORE_C);
        jsonReaderF = new JsonReader(JSON_STORE_F);
        jsonReaderS = new JsonReader(JSON_STORE_S);
        run();
    }

    //MODIFIES: this
    //EFFECTS: displays the menu page and takes in the users input to know what to do next
    private void run() {
        loadCafeList();
        loadCategoryList();
        menuPage();
        isRunning = true;
        initialize();

        while (isRunning) {
            String in = input.next();
            in = in.toLowerCase();

            if (in.equals("leave")) {
                saveCafeList();
                saveCoffeeList();
                saveFoodList();
                saveStudyList();
                isRunning = false;
            } else {
                choiceMenu(in);
            }
        }


    }

    // MODIFIES: this
    // EFFECTS: allows the user to make inputs
    private void initialize() {
        //Cafe c1 = new Cafe("Hello", "asdf", 10, false, false, false);
        //Cafe c2 = new Cafe("Pop", "123", 9, true, true, true);
        //loc.addCafe(c1);
        //loc.addCafe(c2);
        input = new Scanner(System.in);
    }

    // EFFECTS: Shows what the options are for the user
    private void menuPage() {
        System.out.println("\nWould you like to:");
        System.out.println("\tAdd: Add a cafe");
        System.out.println("\tView: View cafes");
        System.out.println("\tSearch: Search for a cafe");
        System.out.println("\tLeave:Leave the application");
    }

    //REQUIRES: input != null
    //MODIFIES: this
    //EFFECTS: takes the user to where they want to go according to their input
    private void choiceMenu(String input) {
        if (input.length() >= 1) {
            if (input.equals("add")) {
                addCafeToList();
            } else if (input.equals("view")) {
                viewCafe();
            } else if (input.equals("search")) {
                searchCafe();
            } else if (input.equals("a")) {
                displayAllCafe();
            } else if (input.equals("c")) {
                displayGoodCoffee();
            } else if (input.equals("s")) {
                displayGoodStudy();
            } else if (input.equals("f")) {
                displayGoodFood();
            } else {
                System.out.println("I am not sure what you wrote");
            }
        }
    }

    //EFFECTS: searches for cafe information by name
    private void searchCafe() {
        System.out.println("\tName:");
        String name = input.next();
        search(name, loc.getCafeList());
    }

    //EFFECTS: takes the name of the cafe and displays information
    private void search(String name, List<Cafe> loc) {
        for (Cafe c : loc) {
            if (c.getName().equals(name)) {
                System.out.println("\tName:" + c.getName());
                System.out.println("\tAddress:" + c.getAddress());
                System.out.println("\tRating:" + c.getRating());
                System.out.println("\tGood Food?:" + c.isGoodFood());
                System.out.println("\tGood Study Spot?:" + c.isGoodStudySpot());
                System.out.println("\tGood Coffee?:" + c.isGoodCoffee());
            }
        }
    }


    //EFFECTS: View menu page for viewing different categories of cafes
    private void viewCafe() {
        categoryMenu();
    }

    //EFFECTS: Display category list
    private void categoryMenu() {
        System.out.println("\nWould you like to:");
        System.out.println("\ta -> all cafes");
        System.out.println("\tc -> good coffee spot");
        System.out.println("\ts -> good study spot");
        System.out.println("\tf -> good food spot");
    }

    //EFFECTS: displays the list of good coffee spots
    private void displayGoodCoffee() {
        System.out.println("List of cafes with good coffee:");
        printList(goodCoffee.getCafeList());
        menuPage();
    }

    //EFFECTS: displays the list of good study spots
    private void displayGoodStudy() {
        System.out.println("List of good study spots:");
        printList(goodStudySpot.getCafeList());
        menuPage();
    }

    //EFFECTS: displays the list of good food spots
    private void displayGoodFood() {
        System.out.println("List of cafes with good food:");
        printList(goodFood.getCafeList());
        menuPage();
    }

    //EFFECTS: displays the list of all cafes
    private void displayAllCafe() {
        System.out.println("List of cafes below:");
        printList(loc.getCafeList());
        menuPage();
    }

    //REQUIRES: all fields != null
    //MODIFIES: this
    //EFFECTS: adds user-inputted cafe to the lists that it belongs to
    private void addCafeToList() {
        System.out.println("\nPlease write below:");
        System.out.println("\tName:");
        this.name = input.next();
        System.out.println("\tAddress:");
        this.address = input.next();
        System.out.println("\tRating:");
        this.rating = input.nextInt();
        System.out.println("\tGood Food? (t/f):");
        this.isGoodF = input.nextBoolean();
        System.out.println("\tGood Study Spot? (t/f):");
        this.isGoodS = input.nextBoolean();
        System.out.println("\tGood Coffee? (t/f):");
        this.isGoodC = input.nextBoolean();
        Cafe c = new Cafe(this.name, this.address, this.rating, this.isGoodC, this.isGoodS, this.isGoodF);

        filter(c);
        loc.addCafe(c);
        menuPage();
    }

    //REQUIRES: there is a cafe to filter
    //MODIFIES: this
    //EFFECTS: adds user-inputted cafe to the lists that it belongs to
    private void filter(Cafe c) {
        if (c.isGoodFood()) {
            goodFood.addCafe(c);
        }
        if (c.isGoodStudySpot()) {
            goodStudySpot.addCafe(c);
        }
        if (c.isGoodCoffee()) {
            goodCoffee.addCafe(c);
        }
    }

    //EFFECTS: prints out the list of cafes by name
    public void printList(List<Cafe> list) {
        for (Cafe cafe : list) {
            System.out.println(cafe.getName());
        }
    }

    // EFFECTS: saves the list to file
    private void saveCafeList() {
        try {
            jsonWriter.open();
            jsonWriter.write(loc);
            jsonWriter.close();
            System.out.println("Saved list to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads list from file
    private void loadCafeList() {
        try {
            loc = jsonReader.read();
            System.out.println("Loaded list from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    // EFFECTS: saves the list to file
    private void saveCoffeeList() {
        try {
            jsonWriterC.open();
            jsonWriterC.write(goodCoffee);
            jsonWriterC.close();
            System.out.println("Saved list to " + JSON_STORE_C);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE_C);
        }
    }

    // EFFECTS: saves the list to file
    private void saveFoodList() {
        try {
            jsonWriterF.open();
            jsonWriterF.write(goodFood);
            jsonWriterF.close();
            System.out.println("Saved list to " + JSON_STORE_F);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE_F);
        }
    }

    // EFFECTS: saves the list to file
    private void saveStudyList() {
        try {
            jsonWriterS.open();
            jsonWriterS.write(goodStudySpot);
            jsonWriterS.close();
            System.out.println("Saved list to " + JSON_STORE_S);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE_S);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads list from file
    private void loadCategoryList() {
        try {
            loc = jsonReaderC.read();
            loc = jsonReaderF.read();
            loc = jsonReaderS.read();
            System.out.println("Loaded list from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }
*/

}
