package ui;

import javax.swing.*;

import model.User;

import java.awt.*;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class MainJFrame extends JFrame {
   
    private JPanel topPanel;
    private CardJPanel bottomPanel;

    public MainJFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 800);

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

    private void signInButtonListener
                                    (
                                        java.awt.event.ActionEvent evet
                                    )
    {
        try
        {
            User user = new User();
            user.setFirstName(firstname.getText());
            user.setLastName(lastname.getText());
            user.setGender(genderComboBox.getSelectedItem().toString());
            user.setEmail(email.getText());
            user.setHobby(hobbyTextArea.getText());
            
            Integer ageInteger = Integer.parseInt(age.getText());
            user.setAge(ageInteger);

            Integer phoneNumInteger = Integer.parseInt(phoneNum.getText());
            user.setPhoneNumber(phoneNumInteger);

            if (user.getLastName().isBlank() || user.getFirstName().isBlank()) {
                JOptionPane.showMessageDialog
                    (
                        rootPane, 
                        "Please fill your name!", 
                        "Error", 
                        JOptionPane.ERROR_MESSAGE
                    );
            } else if(phoneNumInteger <= 0 || user.getEmail().isBlank()){
                JOptionPane.showMessageDialog
                    (
                        rootPane, 
                        "Please fill the correct contact info!", 
                        "Error", 
                        JOptionPane.ERROR_MESSAGE
                    );
            } else if(ageInteger < 0){
                JOptionPane.showMessageDialog
                    (
                        rootPane, 
                        "Something wrong with your age!", 
                        "Error", 
                        JOptionPane.ERROR_MESSAGE
                    );
            } else {
                JOptionPane.showMessageDialog
                    (
                        rootPane, 
                        "Welcome, " + user,
                        "Success", 
                        JOptionPane.HEIGHT,
                        this.getIcon()
                    );
            }
        }
        catch(NullPointerException ne)
        {
            JOptionPane.showMessageDialog
                (
                    rootPane, 
                    "Please select your gender!", 
                    "Oops", 
                    JOptionPane.ERROR_MESSAGE
                );
        }
        catch(NumberFormatException nex)
        {
            JOptionPane.showMessageDialog
            (
                rootPane, 
                "Please check your age and phone number is valid value!", 
                "Failed", 
                JOptionPane.ERROR_MESSAGE
            );
        }
        catch(Exception ex)
        {
            JOptionPane.showMessageDialog
            (
                rootPane, 
                "Oops! Something went wrong!", 
                "Failed", 
                JOptionPane.ERROR_MESSAGE
            );
        }
    }
    public static void main(String[] args) {
        MainJFrame mainJFrame = new MainJFrame();
    }
}

