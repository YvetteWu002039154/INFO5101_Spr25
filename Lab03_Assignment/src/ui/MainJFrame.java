package ui;

import javax.swing.*;

import model.User;

import java.awt.*;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class MainJFrame extends JFrame {
    // first name, last name, gender, age, phone number, email
    private ImageIcon imageIcon;
    private JTextField firstname;
    private JTextField lastname;
    private JTextField age;
    private JTextField phoneNum;
    private JTextField email;
    private JComboBox<String> genderComboBox;
    private JTextArea hobbyTextArea;
    public MainJFrame() {
        setSize(400, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); 
        setLayout(new BorderLayout(10, 10)); 
        getContentPane().setBackground(Color.BLUE);

        JLabel headlineLabel = new JLabel("Registration", SwingConstants.CENTER);
        headlineLabel.setFont(new Font("Arial", Font.BOLD, 24)); 
        headlineLabel.setBorder(BorderFactory
                                    .createEmptyBorder(20, 0, 10, 0)); 
        add(headlineLabel, BorderLayout.NORTH); 

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridBagLayout()); 
        formPanel.setBorder(BorderFactory
                                .createEmptyBorder(0, 10, 10, 10)); 
        
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
        formPanel.add(firstname, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST; 
        formPanel.add(new JLabel("LastName:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;

        lastname = new JTextField();
        formPanel.add(lastname, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST; 
        formPanel.add(new JLabel("Age:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;

        age = new JTextField();
        formPanel.add(age, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.WEST; 
        formPanel.add(new JLabel("Phone Number:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;

        phoneNum = new JTextField();
        formPanel.add(phoneNum, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.WEST; 
        formPanel.add(new JLabel("Email:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 4;

        email = new JTextField();
        formPanel.add(email, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 5;
        formPanel.add(new JLabel("Gender:"), gbc);

        String[] options = {"Male", "Female", "Non-Bisexual"};
        genderComboBox = new JComboBox<>(options);

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
        formPanel.add(hobbyTextArea, gbc);

        add(formPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        JButton uploadButton = new JButton("Upload Image");
        uploadButton.addActionListener(e -> uploadButtonListener(e));
        buttonPanel.add(uploadButton);

        
        JButton signInButton = new JButton("Submit");

        signInButton.addActionListener(e -> signInButtonListener(e));

        buttonPanel.add(signInButton);

        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    public void setIcon(Image image){
        imageIcon = new ImageIcon(image);
    }

    public ImageIcon getIcon(){
        return imageIcon;
    }

    private void uploadButtonListener(java.awt.event.ActionEvent evet){
        JFileChooser file = new JFileChooser();
        file.setDialogTitle("Select an Image File");

        if (file.showSaveDialog(null) == JFileChooser.APPROVE_OPTION){
            try 
            {
                BufferedImage img = ImageIO.read(file.getSelectedFile());

                Image edited_image = img
                                        .getScaledInstance
                                            (
                                                60, 
                                                80,
                                                Image.SCALE_SMOOTH
                                            );

                if (edited_image != null) 
                {
                    this.setIcon(edited_image);
                }
                else
                {
                    JOptionPane
                    .showMessageDialog(
                        rootPane, 
                        "Please upload image correctly.", 
                        "Error - Incorrect image", 
                        JOptionPane.ERROR_MESSAGE
                    );
                }
            } 
            catch (Exception ex) 
            {
                JOptionPane
                    .showMessageDialog(
                        rootPane, 
                        "Please upload image correctly.", 
                        "Error - Incorrect image", 
                        JOptionPane.ERROR_MESSAGE
                    );
                ex.printStackTrace();
            }
        }
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

