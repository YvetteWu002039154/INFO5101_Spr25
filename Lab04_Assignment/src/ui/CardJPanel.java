// Source code is decompiled from a .class file using FernFlower decompiler.
package ui;

import java.awt.CardLayout;
import javax.swing.JPanel;
import model.User;

public class CardJPanel extends JPanel {
   private CardLayout cardLayout = new CardLayout();
   private FormJPanel formJPanel;
   private ViewJPanel viewJPanel;

   public CardJPanel() {
      this.setLayout(this.cardLayout);
      this.formJPanel = new FormJPanel(this);
      this.viewJPanel = new ViewJPanel();
      this.add(this.formJPanel, "CardForm");
      this.add(this.viewJPanel, "CardView");
   }

   public void showCard(String cardName) {
      this.cardLayout.show(this, cardName);
   }

   public void showCard(String cardName, User user) {
      this.viewJPanel.setUser(user);
      this.cardLayout.show(this, cardName);
   }
}
