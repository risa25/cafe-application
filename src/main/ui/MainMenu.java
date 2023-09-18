package ui;

import model.EventLog;
import ui.add.AddClass;
import ui.view.ViewListMenuPage;

import model.Event;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainMenu {
    private JFrame frame;
    private JPanel panel;
    private JButton viewCafe;
    private JButton searchCafe;
    private JButton ratingGraph;
    private JButton addCafe;
    private EventLog eventLog;
    private Event event;

    //EFFECTS: initializes a frame to see all applicable sections of the cafe application
    public MainMenu() {
        frame = new JFrame("Cafe Application");
        frame.setResizable(false);

        addButtonImage();
        viewButtonImage();
        searchButtonImage();
        ratingGraphButton();


        initializePanel();
        initializeFrame();


    }

    //EFFECTS: initialize button to go to separate graph frame
    private void ratingGraphButton() {
        ratingGraph = new JButton("Show Cafes Ratings");

        ratingGraph.setVerticalTextPosition(JLabel.TOP);
        ratingGraph.setHorizontalTextPosition(JLabel.CENTER);
        ratingGraph.setFont(new Font("Times New Roman", Font.BOLD, 14));
        Image img = new ImageIcon(getClass().getResource("/images/graph.png")).getImage();
        ratingGraph.setIcon(new ImageIcon(img.getScaledInstance(100, 100,100)));
        ratingGraph.setBounds(500, 479, 500, 479);
        ratingGraph.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ViewGraph();
                frame.dispose();
            }
        });
    }

    //EFFECTS: initialize the button to go to add cafe frame
    private void addButtonImage() {
        addCafe = new JButton("Add a Cafe");
        addCafe.setVerticalTextPosition(JLabel.TOP);
        addCafe.setHorizontalTextPosition(JLabel.CENTER);
        addCafe.setFont(new Font("Times New Roman", Font.BOLD, 14));
        Image img = new ImageIcon(getClass().getResource("/images/cafeicon.png")).getImage();
        addCafe.setIcon(new ImageIcon(img.getScaledInstance(100, 100,100)));
        addCafe.setBounds(500, 479, 500, 479);
        addCafe.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new AddClass();
                frame.dispose();
            }
        });

    }

    //EFFECTS: initialize the button to see all cafes
    private void viewButtonImage() {
        viewCafe = new JButton("View Cafe List");
        viewCafe.setVerticalTextPosition(JLabel.TOP);
        viewCafe.setHorizontalTextPosition(JLabel.CENTER);
        viewCafe.setFont(new Font("Times New Roman", Font.BOLD, 14));
        Image img = new ImageIcon(getClass().getResource("/images/viewicon.png")).getImage();
        viewCafe.setIcon(new ImageIcon(img.getScaledInstance(110, 100,100)));
        viewCafe.setBounds(500, 479, 500, 479);
        viewCafe.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new ViewListMenuPage();
                frame.dispose();
            }
        });

    }

    //EFFECTS: initialize the button to search for cafes
    private void searchButtonImage() {
        searchCafe = new JButton("Search for a Cafe");
        searchCafe.setVerticalTextPosition(JLabel.TOP);
        searchCafe.setHorizontalTextPosition(JLabel.CENTER);
        searchCafe.setFont(new Font("Times New Roman", Font.BOLD, 14));
        Image img = new ImageIcon(getClass().getResource("/images/searchicon.jpeg")).getImage();
        searchCafe.setIcon(new ImageIcon(img.getScaledInstance(100, 100,100)));
        searchCafe.setBounds(500, 479, 500, 479);
        searchCafe.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new SearchClass();
                frame.dispose();
            }
        });

    }

    //EFFECTS: initialize the panel and add all items to the panel
    private void initializePanel() {
        panel = new JPanel();
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createEmptyBorder(30,30,30,30));
        panel.setLayout(new GridLayout(0, 1));
        panel.add(ratingGraph);
        panel.add(addCafe);
        panel.add(viewCafe);
        panel.add(searchCafe);
    }

    //EFFECTS: creating the frame so that it is visible
    private void initializeFrame() {
        frame.add(panel, BorderLayout.CENTER);
        frame.setBounds(500, 200, 100, 100);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter() {
            /**
             * Invoked when a window has been opened.
             *
             * @parame
             */
            @Override
            public void windowClosing(WindowEvent e) {
                printLog(eventLog.getInstance());
                System.exit(0);
            }
        });

        frame.pack();
        frame.setVisible(true);
    }

    //EFFECTS: prints the eventlog to the console
    private void printLog(EventLog eventLog) {
        for (Event e: eventLog) {
            System.out.println(e.toString());
        }
    }

}
