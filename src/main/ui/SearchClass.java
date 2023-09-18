package ui;


import model.Cafe;
import model.ListOfCafe;
import persistence.JsonReader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;

public class SearchClass {
    private static final String JSON_STORE = "./data/cafelist.json";
    ListOfCafe loc;
    private JsonReader jsonReader;

    private JFrame frame;
    private final JPanel panel;
    private final JButton backButton;
    private JTextField searchCafe;
    private JButton searchButton;
    private JTextArea nameArea;
    private JTextArea addressDisplay;
    private JTextArea ratingDisplay;
    private JTextArea goodFoodDisplay;
    private JTextArea goodCoffeeDisplay;
    private JTextArea goodStudySpotDisplay;

    //EFFECTS: Initialize the frame and add all of the capabilities to the frame
    public SearchClass() {
        frame = new JFrame("Cafe Application");

        loadCafeList();
        searchButtonAndText();

        backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new MainMenu();
            }
        });


        panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        panel.setLayout(new GridLayout(0, 1));
        panel.add(searchButton);
        panel.add(searchCafe);
        panel.add(backButton);

        initializeTextArea();

        initializeFrame();


    }

    //EFFECTS: initialize the button to search for a cafe
    private void searchButtonAndText() {
        searchButton = new JButton("Search");
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                search(searchCafe.getText(), loc.getCafeList());
            }
        });
        searchCafe = new JTextField();
        searchCafe.setColumns(10);
    }

    //EFFECTS: initialize the text areas that the cafe info will show up on
    private void initializeTextArea() {
        nameArea = new JTextArea();
        addressDisplay = new JTextArea();
        ratingDisplay = new JTextArea();
        goodFoodDisplay = new JTextArea();
        goodCoffeeDisplay = new JTextArea();
        goodStudySpotDisplay = new JTextArea();
    }

    //EFFECTS: takes the name of the cafe and displays information
    private void search(String name, List<Cafe> loc) {
        for (Cafe c : loc) {
            if (c.getName().equals(name)) {
                panel.add(nameArea);
                nameArea.setText("Name: " + c.getName());
                panel.add(addressDisplay);
                addressDisplay.setText("Address: " + c.getAddress());
                panel.add(ratingDisplay);
                ratingDisplay.setText("Rating: " + Integer.toString(c.getRating()));
                panel.add(goodFoodDisplay);
                goodFoodDisplay.setText("Good Food?: " + Boolean.toString(c.isGoodFood()));
                panel.add(goodCoffeeDisplay);
                goodCoffeeDisplay.setText("Good Coffee?: " + Boolean.toString(c.isGoodCoffee()));
                panel.add(goodStudySpotDisplay);
                goodStudySpotDisplay.setText("Good Study Spot?: " + Boolean.toString(c.isGoodStudySpot()));
            }
        }
    }

    //EFFECTS: add panel to frame and set visible to true
    private void initializeFrame() {
        frame.add(panel);
        frame.setPreferredSize(new Dimension(500, 500));
        frame.setBounds(500, 200, 100, 100);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    //EFFECTS: load the cafe list from json in order to search for cafe
    private void loadCafeList() {
        jsonReader = new JsonReader(JSON_STORE);
        frame = new JFrame("Cafe Application");
        try {
            loc = jsonReader.read();
            System.out.println("Loaded list from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }
}
