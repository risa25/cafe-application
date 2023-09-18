package ui.view;

import ui.MainMenu;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewListMenuPage {
    private JFrame frame;
    private JPanel panel;
    private JButton backButton;
    private JButton viewAllButton;
    private JButton viewGoodCoffeeButton;
    private JButton viewGoodFoodButton;
    private JButton viewGoodStudySpotButton;

    //EFFECTS: able to view gui to select what list of cafes user wants to see
    public ViewListMenuPage() {

        backButton = new JButton("Back to Menu");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new MainMenu();
            }
        });

        initializeViewAllButton();

        initializeViewFiltered();

        initializePanel();

        initializeFrame();
    }

    //EFFECTS: initialize panel and add items to the panel
    private void initializePanel() {
        panel = new JPanel();
        panel.add(backButton);
        panel.add(viewAllButton);
        panel.add(viewGoodCoffeeButton);
        panel.add(viewGoodFoodButton);
        panel.add(viewGoodStudySpotButton);
    }

    //EFFECTS: initialize frame and make it visible
    private void initializeFrame() {
        frame = new JFrame("Cafe Application");
        frame.add(panel);
        frame.setBounds(500, 200, 100, 100);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    //EFFECTS: initialize the button to see all cafes
    private void initializeViewAllButton() {
        viewAllButton = new JButton("View All Cafes");
        viewAllButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ViewAll();
            }
        });
    }

    //EFFECTS: initialize the button to see all filtered cafes
    private void initializeViewFiltered() {
        viewGoodCoffeeButton = new JButton("View Cafes with Good Coffee");
        viewGoodCoffeeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ViewGoodCoffee();
            }
        });

        viewGoodFoodButton = new JButton("View Cafes with Good Food");
        viewGoodFoodButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ViewGoodFood();
            }
        });

        viewGoodStudySpotButton = new JButton("View Good Study Spots");
        viewGoodStudySpotButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ViewGoodStudySpot();
            }
        });
    }


}
