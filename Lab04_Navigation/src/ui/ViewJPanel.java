package ui;

import javax.swing.*;
import java.awt.*;
import model.User;

public class ViewJPanel extends JPanel{
    
    private JTextField nameField;
    private JTextField ageField;
    private JTextArea hobbyTextArea;
    private JComboBox<String> collegeComboBox;

    public void setUser(User user) {
        nameField.setText(user.getName());
        ageField.setText(String.valueOf(user.getAge()));
        collegeComboBox.setSelectedItem(user.getCollege());
        hobbyTextArea.setText(user.getHobby());
    }

    public ViewJPanel(){

        setLayout(new FlowLayout());
        setBackground(Color.LIGHT_GRAY);

        JLabel headlineLabel = new JLabel("Information", SwingConstants.CENTER);
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
        nameField.setEditable(false);
        formPanel.add(nameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(new JLabel("College:"), gbc);

        String[] options = {"COE", "CPS", "COS"};
        collegeComboBox = new JComboBox<>(options);
        collegeComboBox.setEnabled(false);
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
        ageField.setEditable(false); 
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
                                
        hobbyTextArea.setEditable(false);
        formPanel.add(hobbyTextArea, gbc);
        add(formPanel, BorderLayout.CENTER);
    }
}
