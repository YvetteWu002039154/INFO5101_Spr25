package ui;

import javax.swing.*;

import com.toedter.calendar.JDateChooser;

import java.awt.*;
import model.User;

public class ViewJPanel extends JPanel{
    
    // first name, last name, gender, age, phone number, email
    private JTextField firstname;
    private JTextField lastname;
    private JTextField age;
    private JTextField phoneNum;
    private JTextField email;
    private JComboBox<String> genderComboBox;
    private JTextArea hobbyTextArea;
    private JLabel imageLabel;
    private JDateChooser birthDay;

    public void setUser(User user) {
        firstname.setText(user.getFirstName());
        lastname.setText(user.getLastName());
        age.setText(String.valueOf(user.getAge()));
        phoneNum.setText(String.valueOf(user.getPhoneNumber()));
        email.setText(user.getEmail());
        genderComboBox.setSelectedItem(user.getGender());
        hobbyTextArea.setText(user.getHobby());
        imageLabel.setIcon(user.getImageIcon());
        birthDay.setDate(user.getBirthday());
    }

    public ViewJPanel(){
        JLabel headlineLabel = new JLabel("Information", SwingConstants.CENTER);
        headlineLabel.setFont(new Font("Arial", Font.BOLD, 24)); 
        add(headlineLabel, BorderLayout.NORTH); 

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridBagLayout()); 
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15); 
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST; 
        formPanel.add(new JLabel("FirstName:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        
        firstname = new JTextField();
        firstname.setEditable(false);
        formPanel.add(firstname, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST; 
        formPanel.add(new JLabel("LastName:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;

        lastname = new JTextField();
        lastname.setEditable(false);
        formPanel.add(lastname, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST; 
        formPanel.add(new JLabel("Age:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;

        age = new JTextField();
        age.setEditable(false);
        formPanel.add(age, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.WEST; 
        formPanel.add(new JLabel("Phone Number:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;

        phoneNum = new JTextField();
        phoneNum.setEditable(false);
        formPanel.add(phoneNum, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.WEST; 
        formPanel.add(new JLabel("Email:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 4;

        email = new JTextField();
        email.setEditable(false);
        formPanel.add(email, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 5;
        formPanel.add(new JLabel("Gender:"), gbc);

        String[] options = {"Male", "Female", "Non-Bisexual"};
        genderComboBox = new JComboBox<>(options);
        genderComboBox.setEnabled(false);

        JPanel genderPanel = new JPanel();
        genderPanel.setLayout(new BoxLayout(genderPanel, BoxLayout.Y_AXIS)); 
        genderPanel.add(genderComboBox);

        gbc.gridx = 1;
        gbc.gridy = 5;
        formPanel.add(genderPanel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.anchor = GridBagConstraints.WEST; 
        formPanel.add(new JLabel("Hobbies:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 6;

        hobbyTextArea = new JTextArea();
        hobbyTextArea.setBorder(BorderFactory
                                .createEmptyBorder(5, 5, 5, 5));
        hobbyTextArea.setEditable(false);
        formPanel.add(hobbyTextArea, gbc);

        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.anchor = GridBagConstraints.WEST; 
        formPanel.add(new JLabel("Birthday:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 7;

        birthDay = new JDateChooser();
        birthDay.setEnabled(false);
        formPanel.add(birthDay, gbc);

        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.anchor = GridBagConstraints.WEST; 
        formPanel.add(new JLabel("Picture:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 8;

        imageLabel = new JLabel();
        imageLabel.setBorder(BorderFactory
                                .createEmptyBorder(5, 5, 5, 5));
        formPanel.add(imageLabel, gbc);

        add(formPanel, BorderLayout.CENTER);
    }
}
