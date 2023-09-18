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

public class ViewGoodCoffee {
    private static final String JSON_STORE_C = "./data/goodCoffee.json";
    ListOfCafe loc;
    private JsonReader jsonReaderC;

    private JFrame frame;
    private JPanel panel;
    private JScrollPane pane;
    private JButton backButton;

    //EFFECTS: able to view cafes with good coffee in the gui
    public ViewGoodCoffee() {
        loadCafeList();

        backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });


        panel = new JPanel();
        panel.add(pane, BorderLayout.NORTH);
        panel.add(backButton);


        frame.add(panel);
        frame.setBounds(500, 200, 100, 100);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    //EFFECTS: traverse the list of names so it can be displayed
    private ArrayList<String> traverseList() {
        ArrayList<String> listOfNames = new ArrayList<>();
        for (Cafe c: loc.getCafeList()) {
            listOfNames.add(c.getName());
        }
        return listOfNames;
    }

    //EFFECTS: loads the cafe list from json store
    private void loadCafeList() {
        jsonReaderC = new JsonReader(JSON_STORE_C);
        frame = new JFrame("Cafe Application");
        try {
            loc = jsonReaderC.read();
            System.out.println("Loaded list from " + JSON_STORE_C);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE_C);
        }

        ArrayList<String> listNames = traverseList();
        String[] cafe = new String[listNames.size()];
        JList<String> cafeJlist = new JList<>(listNames.toArray(cafe));

        cafeJlist.setVisibleRowCount(4);
        pane = new JScrollPane(cafeJlist);
    }
}
