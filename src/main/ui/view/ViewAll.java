package ui.view;

import model.Cafe;
import model.ListOfCafe;
import persistence.JsonReader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

//View the entire list

public class ViewAll {

    private static final String JSON_STORE = "./data/cafelist.json";
    ListOfCafe loc;
    private JsonReader jsonReader;

    private JFrame frame;
    private JPanel panel;
    private JScrollPane pane;
    private JButton backButton;

    //EFFECTS: Creates a new frame showing a list of all of the cafes in the list
    public ViewAll() {
        frame = new JFrame("Cafe Application");
        loadCafeList();

        backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });

        initializePanel();
        initializeFrame();
    }

    //EFFECTS: creates a list of string names of all cafes
    private ArrayList<String> traverseList() {
        ArrayList<String> listOfNames = new ArrayList<>();
        for (Cafe c: loc.getCafeList()) {
            listOfNames.add(c.getName());
        }
        return listOfNames;
    }

    //EFFECTS: loads the list from the json file
    public void loadCafeList() {
        jsonReader = new JsonReader(JSON_STORE);
        try {
            loc = jsonReader.read();
            System.out.println("Loaded list from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }

        ArrayList<String> listNames = traverseList();
        String[] cafe = new String[listNames.size()];
        JList<String> cafeJlist = new JList<>(listNames.toArray(cafe));

        cafeJlist.setVisibleRowCount(4);
        pane = new JScrollPane(cafeJlist);

    }


    // EFFECTS: initialize and add items to panel
    public void initializePanel() {
        panel = new JPanel();
        panel.add(pane, BorderLayout.NORTH);
        panel.add(backButton);
    }

    // EFFECTS: make frame visible and edit to liking
    public void initializeFrame() {
        frame.add(panel);
        frame.setBounds(500, 200, 100, 100);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
