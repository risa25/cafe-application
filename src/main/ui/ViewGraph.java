package ui;


import model.Cafe;
import model.ListOfCafe;
import org.jfree.data.category.CategoryDataset;
import persistence.JsonReader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

public class ViewGraph {

    private static final String JSON_STORE = "./data/cafelist.json";
    ListOfCafe loc;
    private JsonReader jsonReader;
    private JFrame frame;

    private JPanel panel;
    private JScrollPane pane;
    private JButton backButton;
    private ChartPanel chartPanel;

    //EFFECTS: initialize the frame and make sure all capabilities are set so graph is displayed in frame
    public ViewGraph() {
        frame = new JFrame("Cafe Application");

        loadCafeList();

        JFreeChart barChart = ChartFactory.createBarChart("Cafe Ratings",
                "Cafe", "Rating", createDataset(),PlotOrientation.VERTICAL,
                true, true, false);

        // Adding chart into a chart panel
        chartPanel = new ChartPanel(barChart);

        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));


        backButton = new JButton("Back to Main Menu");
        backButton.setSize(100, 50);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MainMenu();
                frame.dispose();
            }
        });

        initializePanel();

        initializeFrame();

    }

    //EFFECTS: creates the data to put into the graph
    private CategoryDataset createDataset() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        for (Cafe c: loc.getCafeList()) {
            dataset.addValue(c.getRating(), c.getName(), c.getName());
        }

        return dataset;

    }



    //EFFECTS: creates a list of string names of all cafes
    private ArrayList<Integer> traverseList() {
        ArrayList<Integer> listOfRating = new ArrayList<>();
        for (Cafe c: loc.getCafeList()) {
            listOfRating.add(c.getRating());
        }
        return listOfRating;
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

        ArrayList<Integer> listRating = traverseList();
        Integer[] cafe = new Integer[listRating.size()];
        JList<Integer> cafeJlist = new JList<>(listRating.toArray(cafe));

        cafeJlist.setVisibleRowCount(4);
        pane = new JScrollPane(cafeJlist);
    }

    //EFFECTS: adds panel to frame so that all items are visible on the frame
    private void initializeFrame() {
        frame.add(panel, BorderLayout.CENTER);
        frame.setBounds(500, 200, 100, 100);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    //EFFECTS: initialize and add items to the panel
    private void initializePanel() {
        panel = new JPanel();
        panel.add(chartPanel);
        panel.add(backButton);
        panel.setBorder(BorderFactory.createEmptyBorder(30,30,30,30));
        panel.setLayout(new GridLayout(0, 1));
    }


}
