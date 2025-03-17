package ui;

import java.awt.*;
import javax.swing.*;
import model.User;

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

    // Method to switch cards with user parameter
    public void showCard(String cardName, User user) {
        if (cardName.equals("CardView")) {
            panel2.refreshTableData();
        }
        cardLayout.show(this, cardName);
    }
    
    // Overloaded method to switch cards without user parameter
    public void showCard(String cardName) {
        if (cardName.equals("CardView")) {
            panel2.refreshTableData();
        }
        cardLayout.show(this, cardName);
    }
    
    public ViewJPanel getViewPanel() {
        return panel2;
    }
}
