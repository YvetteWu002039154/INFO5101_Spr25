package ui;

import javax.swing.*;

import model.User;

import java.awt.*;

public class MainJFrame extends JFrame {
    private JButton submitButton;
    private JTextField nameField;
    private JTextField ageField;
    private JTextArea hobbyTextArea;
    private JComboBox<String> collegeComboBox;

    public MainJFrame() {
        setSize(400, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); 
        setLayout(new BorderLayout(10, 10)); 
        getContentPane().setBackground(Color.BLUE);

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

        setVisible(true);
    }

    private void submitButtonActionPerformed(java.awt.event.ActionEvent evet){
            try{
                User user = new User();
    
                user.setName(this.nameField.getText());
                user.setCollege(this.collegeComboBox.getSelectedItem().toString());
                user.setAge(Integer.parseInt(this.ageField.getText()));
                user.setHobby(this.hobbyTextArea.getText());
    
                if (user.getName().isBlank()) {
                    JOptionPane.showMessageDialog
                        (
                            rootPane, 
                            "Please fill your name!", 
                            "Error", 
                            JOptionPane.ERROR_MESSAGE
                        );
                } else {
                    JOptionPane.showMessageDialog
                        (
                            rootPane, 
                            "Welcome, " + user.getName() + " Age: "+ user.getAge() + " with Hobbies: "+ user.getHobby() + " to " + user.getCollege() + "!", 
                            "Success", 
                            JOptionPane.HEIGHT
                        );
                }
            }
            catch(NullPointerException ne){
                JOptionPane.showMessageDialog
                        (
                            rootPane, 
                            "Please select your college!", 
                            "Oops", 
                            JOptionPane.ERROR_MESSAGE
                        );
            }
            catch(NumberFormatException nue){
                JOptionPane.showMessageDialog
                (
                    rootPane, 
                    "Please fill correct format of age!", 
                    "Oops", 
                    JOptionPane.ERROR_MESSAGE
                );
            }
            catch(Exception ex){
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


