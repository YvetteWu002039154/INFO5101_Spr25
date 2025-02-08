package ui;

import javax.swing.*;
import java.awt.*;

public class MainJFrame extends JFrame {
    private JPanel topPanel;
    private CardJPanel bottomPanel;

    public MainJFrame() {

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400);

        bottomPanel = new CardJPanel();

        topPanel = new JPanel();
        JButton formbtn = new JButton("Form");
        JButton viewbtn = new JButton("View");

        formbtn.addActionListener(e -> bottomPanel.showCard("CardForm"));
        viewbtn.addActionListener(e -> bottomPanel.showCard("CardView"));

        topPanel.add(formbtn);
        topPanel.add(viewbtn);

        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, topPanel, bottomPanel);
        splitPane.setDividerLocation(50); 
        splitPane.setResizeWeight(0.5);

        this.add(splitPane);

        setVisible(true);
    }
    public static void main(String[] args) {
        MainJFrame mainJFrame = new MainJFrame();
    }
}


