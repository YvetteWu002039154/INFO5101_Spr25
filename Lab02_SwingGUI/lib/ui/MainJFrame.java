package lib.ui; 

import javax.swing.*;
import java.awt.*;

public class MainJFrame extends JFrame {
    private JButton signInButton;
    private JTextField nameField;
    private ButtonGroup collegeGroup;

    public MainJFrame() {
        setSize(400, 300);
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

        JRadioButton coeButton = new JRadioButton("COE");
        coeButton.setActionCommand("COE");

        JRadioButton cpsButton = new JRadioButton("CPS");
        cpsButton.setActionCommand("CPS");

        JRadioButton cosButton = new JRadioButton("COS");
        cosButton.setActionCommand("COS");

        JPanel collegePanel = new JPanel();
        collegePanel.setLayout(new BoxLayout(collegePanel, BoxLayout.Y_AXIS));
        collegePanel.add(coeButton);
        collegePanel.add(cpsButton);
        collegePanel.add(cosButton); 

        collegeGroup = new ButtonGroup();
        collegeGroup.add(coeButton);
        collegeGroup.add(cpsButton);
        collegeGroup.add(cosButton);

        gbc.gridx = 1;
        gbc.gridy = 1;

        formPanel.add(collegePanel, gbc);
        add(formPanel, BorderLayout.CENTER);

        signInButton = new JButton("Sign In");

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(signInButton);
        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    public JButton getSignInButton() {
        return signInButton;
    }

    public JTextField getNameField() {
        return nameField;
    }

    public ButtonGroup getCollegeGroup() {
        return collegeGroup;
    }

    public static void main(String[] args) {
        MainJFrame mainJFrame = new MainJFrame();

        mainJFrame.getSignInButton().addActionListener(e -> {
            try
            {
                String name = mainJFrame
                                .getNameField()
                                .getText();

                String selectedCollege = mainJFrame
                                            .getCollegeGroup()
                                            .getSelection()
                                            .getActionCommand();

                /* String selectedCollege = null;
                if (coeButton.isSelected()) {
                    selectedCollege = "COE";
                } else if (cpsButton.isSelected()) {
                    selectedCollege = "CPS";
                } else if (cosButton.isSelected()) {
                    selectedCollege = "COS";
                } */

                if (name.isEmpty()) {
                    JOptionPane.showMessageDialog
                        (
                            mainJFrame, 
                            "Please fill your name!", 
                            "Error", 
                            JOptionPane.ERROR_MESSAGE
                        );
                } else {
                    JOptionPane.showMessageDialog
                        (
                            mainJFrame, 
                            "Welcome, " + name + " to " + selectedCollege + "!", 
                            "Success", 
                            JOptionPane.HEIGHT
                        );
                }
            }
            catch(NullPointerException ne)
            {
                JOptionPane.showMessageDialog
                    (
                        mainJFrame, 
                        "Please select your college!", 
                        "Oops", 
                        JOptionPane.ERROR_MESSAGE
                    );
            }
            catch(Exception ex)
            {
                JOptionPane.showMessageDialog
                (
                    mainJFrame, 
                    "Oops! Something went wrong!", 
                    "Failed", 
                    JOptionPane.ERROR_MESSAGE
                );
            }
        });
    }
}

