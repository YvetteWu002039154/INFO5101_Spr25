package ui;

import javax.swing.*;

import model.User;

import java.awt.*;

public class CardJPanel extends JPanel{
    private CardLayout cardLayout; 
    private FormJPanel panel1;
    private ViewJPanel panel2;

    public CardJPanel() {
        cardLayout = new CardLayout(); 
        setLayout(cardLayout); 

        panel1 = new FormJPanel(this);

        panel2 = new ViewJPanel();

        add(panel1, "CardForm");
        add(panel2, "CardView");
    }

    // Method to switch cards
    public void showCard(String cardName) {
        cardLayout.show(this, cardName);
    }

    public void showCard(String cardName,User user){
        this.panel2.setUser(user);
        cardLayout.show(this, cardName);
    }
    
}
