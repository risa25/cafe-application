package ui.add;

import model.Cafe;
import model.ListOfCafe;
import model.categories.GoodCoffee;
import model.categories.GoodFood;
import model.categories.GoodStudySpot;
import persistence.JsonReader;
import persistence.JsonWriter;
import ui.MainMenu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

public class AddClass {
    private static final String JSON_STORE = "./data/cafelist.json";
    private static final String JSON_STORE_C = "./data/goodCoffee.json";
    private static final String JSON_STORE_F = "./data/goodFood.json";
    private static final String JSON_STORE_S = "./data/goodStudy.json";
    ListOfCafe loc;
    ListOfCafe goodFood;
    ListOfCafe goodCoffee;
    ListOfCafe goodStudySpot;

    private JsonWriter jsonWriter;
    private JsonWriter jsonWriterC;
    private JsonWriter jsonWriterF;
    private JsonWriter jsonWriterS;
    private JsonReader jsonReader;
    private JsonReader jsonReaderC;
    private JsonReader jsonReaderF;
    private JsonReader jsonReaderS;

    private JFrame frame;
    private JPanel panel;
    private JTextField nameField;
    private JTextField addressField;
    private JTextField ratingField;
    private JRadioButton yesCoffee;
    private JRadioButton yesFood;
    private JRadioButton yesStudy;
    private JButton backButton;
    private JButton buttonSubmit;

    //EFFECTS: Initializes the frame so that the functionalities for the gui are available
    public AddClass() {
        frame = new JFrame("Cafe Application");

        initializeInputFields();

        intializeJsonReaderAndWriters();


        buttonSubmit = new JButton("Submit");
        buttonSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveCafeList();
                saveCoffeeList();
                saveFoodList();
                saveStudyList();
                frame.dispose();
                new MainMenu();
            }
        });


        initializeBackButton();
        initializePanel();
        initializeFrame();


    }

    //EFFECTS: Initialize and make functionality for the back to menu button
    private void initializeBackButton() {
        backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MainMenu();
                frame.dispose();
            }
        });
    }

    //EFFECTS: initialize all the necessary objects to read and write from json file
    private void intializeJsonReaderAndWriters() {
        goodCoffee = new GoodCoffee();
        goodFood = new GoodFood();
        goodStudySpot = new GoodStudySpot();

        jsonReader = new JsonReader(JSON_STORE);
        jsonReaderC = new JsonReader(JSON_STORE_C);
        jsonReaderF = new JsonReader(JSON_STORE_F);
        jsonReaderS = new JsonReader(JSON_STORE_S);

        jsonWriter = new JsonWriter(JSON_STORE);
        jsonWriterC = new JsonWriter(JSON_STORE_C);
        jsonWriterF = new JsonWriter(JSON_STORE_F);
        jsonWriterS = new JsonWriter(JSON_STORE_S);

        loadCafeList();
        loadCategoryList();

    }

    // MODIFIES: this
    //EFFECTS: loads the cafe list from the json file
    private void loadCafeList() {
        try {
            loc = jsonReader.read();
            System.out.println("Loaded list from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads list from file
    private void loadCategoryList() {
        try {
            goodCoffee = jsonReaderC.read();
            goodFood = jsonReaderF.read();
            goodStudySpot = jsonReaderS.read();
            System.out.println("Loaded list from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    //EFFECTS: initializes fields for inputting in gui
    private void initializeInputFields() {
        nameField = new JTextField();
        addressField = new JTextField();
        ratingField = new JTextField();
        yesCoffee = new JRadioButton("Yes", true);
        yesFood = new JRadioButton("Yes", true);
        yesStudy = new JRadioButton("Yes", true);
    }

    //EFFECTS: initializes all elements in the frame
    private void initializePanel() {
        panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        panel.setLayout(new GridLayout(0, 1));
        panel.add(new JLabel("Name:"));
        panel.add(nameField);
        panel.add(new JLabel("Address:"));
        panel.add(addressField);
        panel.add(new JLabel("Rating:"));
        panel.add(ratingField);
        panel.add(new JLabel("Good food?:"));
        panel.add(yesFood);
        panel.add(new JLabel("Good Coffee?:"));
        panel.add(yesCoffee);
        panel.add(new JLabel("Good Study Spot?:"));
        panel.add(yesStudy);

        panel.add(buttonSubmit);
        panel.add(backButton);

    }

    //EFFECTS: adds the cafe to the list of cafe
    private ListOfCafe addToCafeList() {
        loc.addCafe(new Cafe(nameField.getText(), addressField.getText(), Integer.parseInt(ratingField.getText()),
                yesCoffee.isSelected(), yesStudy.isSelected(), yesFood.isSelected()));

        return loc;
    }

    //EFFECTS: adds cafe to good food list
    private ListOfCafe addToGoodFoodList() {
        if (yesFood.isSelected()) {
            goodFood.addCafe(new Cafe(nameField.getText(), addressField.getText(),
                    Integer.parseInt(ratingField.getText()), yesCoffee.isSelected(), yesStudy.isSelected(),
                    yesFood.isSelected()));
        }
        return goodFood;
    }

    //EFFECTS: adds cafe to good coffee list
    private ListOfCafe addToGoodCoffeeList() {
        if (yesCoffee.isSelected()) {
            goodCoffee.addCafe(new Cafe(nameField.getText(), addressField.getText(),
                    Integer.parseInt(ratingField.getText()), yesCoffee.isSelected(), yesStudy.isSelected(),
                    yesFood.isSelected()));
        }
        return goodCoffee;
    }

    //EFFECTS: adds cafe to good study list
    private ListOfCafe addToGoodStudyList() {
        if (yesStudy.isSelected()) {
            goodStudySpot.addCafe(new Cafe(nameField.getText(), addressField.getText(),
                    Integer.parseInt(ratingField.getText()), yesCoffee.isSelected(), yesStudy.isSelected(),
                    yesFood.isSelected()));
        }
        return goodStudySpot;
    }

    //EFFECTS: initialize so you can view frame
    private void initializeFrame() {
        frame.add(panel, BorderLayout.CENTER);
        frame.setBounds(500, 200, 100, 100);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }


    // EFFECTS: saves the list to file
    private void saveCafeList() {
        try {
            jsonWriter.open();
            jsonWriter.write(addToCafeList());
            jsonWriter.close();
            System.out.println("Saved list to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // EFFECTS: saves the list to file
    private void saveCoffeeList() {
        try {
            jsonWriterC.open();
            jsonWriterC.write(addToGoodCoffeeList());
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
            jsonWriterF.write(addToGoodFoodList());
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
            jsonWriterS.write(addToGoodStudyList());
            jsonWriterS.close();
            System.out.println("Saved list to " + JSON_STORE_S);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE_S);
        }
    }


}
