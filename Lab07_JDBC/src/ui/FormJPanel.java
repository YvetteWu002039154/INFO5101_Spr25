package ui;

import java.awt.*;
import javax.swing.*;
import model.User;
import utility.DatabaseConnector;

public class FormJPanel extends JPanel{
    private JButton submitButton;
    private JTextField nameField;
    private JTextField ageField;
    private JTextArea hobbyTextArea;
    private JComboBox<String> collegeComboBox;
    private CardJPanel bottomPanel;
    private User user;
    
    public FormJPanel(CardJPanel cardJPanel){

        bottomPanel = cardJPanel;

        setLayout(new FlowLayout());
        setBackground(Color.LIGHT_GRAY);

        JLabel headlineLabel = new JLabel("Registration", SwingConstants.CENTER);
        headlineLabel.setFont(new Font("Arial", Font.BOLD, 24)); 
        headlineLabel.setBorder
            (
                BorderFactory
                    .createEmptyBorder(20, 0, 10, 0)); 
        add(headlineLabel, BorderLayout.NORTH); 

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridBagLayout()); 
        formPanel.setBorder
            (
                BorderFactory
                    .createEmptyBorder(0, 10, 10, 10)
            ); 
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); 
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST; 
        formPanel.add(new JLabel("Name:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;

        nameField = new JTextField();
        nameField.setBorder(BorderFactory
                                .createEmptyBorder(5, 5, 5, 5));
        formPanel.add(nameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(new JLabel("College:"), gbc);

        String[] options = {"COE", "CPS", "COS"};
        collegeComboBox = new JComboBox<>(options);

        JPanel collegePanel = new JPanel();
        collegePanel.setLayout(new BoxLayout(collegePanel, BoxLayout.Y_AXIS));
        collegePanel.add(collegeComboBox);

        gbc.gridx = 1;
        gbc.gridy = 1;

        formPanel.add(collegePanel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST; 
        formPanel.add(new JLabel("Age:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;

        ageField = new JTextField();
        ageField.setBorder(BorderFactory
                                .createEmptyBorder(5, 5, 5, 5));
        formPanel.add(ageField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.WEST; 
        formPanel.add(new JLabel("Hobbies:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;

        hobbyTextArea = new JTextArea();
        hobbyTextArea.setBorder(BorderFactory
                                .createEmptyBorder(5, 5, 5, 5));
        formPanel.add(hobbyTextArea, gbc);
        add(formPanel, BorderLayout.CENTER);

        submitButton = new JButton("Submit");
        submitButton.addActionListener((e) ->submitButtonActionPerformed(e));

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(submitButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void submitButtonActionPerformed(java.awt.event.ActionEvent evet){
            try{
                user = new User();
    
                user.setName(this.nameField.getText());
                user.setCollege(this.collegeComboBox.getSelectedItem().toString());
                user.setAge(Integer.parseInt(this.ageField.getText()));
                user.setHobby(this.hobbyTextArea.getText());

                if (user.getName().isBlank()) {
                    JOptionPane.showMessageDialog
                        (
                            this, 
                            "Please fill your name!", 
                            "Error", 
                            JOptionPane.ERROR_MESSAGE
                        );
                } else {
                    DatabaseConnector.addUser(user);
                    System.out.println("Database updated");
                    
                    JOptionPane.showMessageDialog
                        (
                            this, 
                            "Welcome, " + user.getName() + " Age: "+ user.getAge() + " with Hobbies: "+ user.getHobby() + " to " + user.getCollege() + "!", 
                            "Success", 
                            JOptionPane.HEIGHT
                        );
                        
                    this.bottomPanel.showCard("CardView",this.user);
                    // Refresh the table data in ViewJPanel after switching cards
                    ViewJPanel viewPanel = (ViewJPanel)this.bottomPanel.getComponents()[1]; // Get ViewJPanel
                    viewPanel.refreshTableData();
                }
            }
            catch(NullPointerException ne){
                JOptionPane.showMessageDialog
                        (
                            this, 
                            "Please select your college!", 
                            "Oops", 
                            JOptionPane.ERROR_MESSAGE
                        );
            }
            catch(NumberFormatException nue){
                JOptionPane.showMessageDialog
                (
                    this, 
                    "Please fill correct format of age!", 
                    "Oops", 
                    JOptionPane.ERROR_MESSAGE
                );
            }
            catch(Exception ex){
                JOptionPane.showMessageDialog
                    (
                        this, 
                        "Oops! Something went wrong!", 
                        "Failed", 
                        JOptionPane.ERROR_MESSAGE
                    );
            }
        }
}
