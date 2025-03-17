package ui;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;
import model.User;
import utility.DatabaseConnector;

public class ViewJPanel extends JPanel{
    
    private JTextField nameField;
    private JTextField ageField;
    private JTextArea hobbyTextArea;
    private JComboBox<String> collegeComboBox;
    private JTable userTable;
    private JButton editButton;
    private JButton deleteButton;
    private JButton submitButton;
    private User selectedUser;

    public ViewJPanel(){
        setPreferredSize(new Dimension(800, 600));
        setLayout(new BorderLayout());
        setBackground(Color.LIGHT_GRAY);

        JLabel headlineLabel = new JLabel("Information", SwingConstants.CENTER);
        headlineLabel.setFont(new Font("Arial", Font.BOLD, 24)); 
        headlineLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0)); 
        add(headlineLabel, BorderLayout.NORTH); 

        // Create form panel
        JPanel formPanel = createFormPanel();
        add(formPanel, BorderLayout.CENTER);

        // Setup JTable
        JPanel tablePanel = createTablePanel();
        add(tablePanel, BorderLayout.SOUTH);

        // Clear form initially
        clearForm();
    }

    private JPanel createFormPanel() {
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); 
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Name field
        gbc.gridx = 0; gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST; 
        formPanel.add(new JLabel("Name:"), gbc);

        gbc.gridx = 1;
        nameField = new JTextField(20);
        nameField.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        formPanel.add(nameField, gbc);

        // College field
        gbc.gridx = 0; gbc.gridy = 1;
        formPanel.add(new JLabel("College:"), gbc);

        gbc.gridx = 1;
        String[] options = {"COE", "CPS", "COS"};
        collegeComboBox = new JComboBox<>(options);
        formPanel.add(collegeComboBox, gbc);

        // Age field
        gbc.gridx = 0; gbc.gridy = 2;
        formPanel.add(new JLabel("Age:"), gbc);

        gbc.gridx = 1;
        ageField = new JTextField(20);
        ageField.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        formPanel.add(ageField, gbc);

        // Hobbies field
        gbc.gridx = 0; gbc.gridy = 3;
        formPanel.add(new JLabel("Hobbies:"), gbc);

        gbc.gridx = 1;
        hobbyTextArea = new JTextArea(3, 20);
        hobbyTextArea.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        formPanel.add(hobbyTextArea, gbc);

        // Submit button (initially invisible)
        gbc.gridx = 1; gbc.gridy = 4;
        submitButton = new JButton("Update");
        submitButton.setVisible(false);
        submitButton.addActionListener(e -> updateUser());
        formPanel.add(submitButton, gbc);

        return formPanel;
    }

    private JPanel createTablePanel() {
        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Create button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        editButton = new JButton("Edit");
        deleteButton = new JButton("Delete");
        
        editButton.addActionListener(e -> handleEdit());
        deleteButton.addActionListener(e -> handleDelete());
        
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);
        tablePanel.add(buttonPanel, BorderLayout.NORTH);

        // Setup table
        String[] columnNames = {"User ID", "Name", "Age", "College", "Hobby"};
        ArrayList<User> users = DatabaseConnector.getUsers();
        Object[][] data = new Object[users.size()][5];
        
        for (int i = 0; i < users.size(); i++) {
            User user = users.get(i);
            data[i][0] = user.getUserId();
            data[i][1] = user.getName();
            data[i][2] = user.getAge();
            data[i][3] = user.getCollege();
            data[i][4] = user.getHobby();
        }
        
        userTable = new JTable(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        userTable.setFillsViewportHeight(true);
        userTable.getTableHeader().setReorderingAllowed(false);
        userTable.setRowHeight(25);
        userTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        userTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        
        JScrollPane scrollPane = new JScrollPane(userTable);
        scrollPane.setPreferredSize(new Dimension(750, 200));
        tablePanel.add(scrollPane, BorderLayout.CENTER);
        
        return tablePanel;
    }

    private void handleEdit() {
        int selectedRow = userTable.getSelectedRow();
        if (selectedRow >= 0) {
            selectedUser = new User();
            selectedUser.setUserId((Integer)userTable.getValueAt(selectedRow, 0));
            selectedUser.setName((String)userTable.getValueAt(selectedRow, 1));
            selectedUser.setAge((Integer)userTable.getValueAt(selectedRow, 2));
            selectedUser.setCollege((String)userTable.getValueAt(selectedRow, 3));
            selectedUser.setHobby((String)userTable.getValueAt(selectedRow, 4));
            
            // Fill form with selected user's data
            nameField.setText(selectedUser.getName());
            ageField.setText(String.valueOf(selectedUser.getAge()));
            collegeComboBox.setSelectedItem(selectedUser.getCollege());
            hobbyTextArea.setText(selectedUser.getHobby());
            
            // Show submit button
            submitButton.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, 
                "Please select a user to edit", 
                "No Selection", 
                JOptionPane.WARNING_MESSAGE);
        }
    }

    private void handleDelete() {
        int selectedRow = userTable.getSelectedRow();
        if (selectedRow >= 0) {
            int confirm = JOptionPane.showConfirmDialog(this,
                "Are you sure you want to delete this user?",
                "Confirm Delete",
                JOptionPane.YES_NO_OPTION);
                
            if (confirm == JOptionPane.YES_OPTION) {
                User userToDelete = new User();
                userToDelete.setUserId((Integer)userTable.getValueAt(selectedRow, 0));
                DatabaseConnector.deleteUser(userToDelete);
                refreshTableData();
                clearForm();
            }
        } else {
            JOptionPane.showMessageDialog(this, 
                "Please select a user to delete", 
                "No Selection", 
                JOptionPane.WARNING_MESSAGE);
        }
    }

    private void updateUser() {
        try {
            User updatedUser = new User();
            updatedUser.setUserId(selectedUser.getUserId());
            updatedUser.setName(nameField.getText());
            updatedUser.setAge(Integer.parseInt(ageField.getText()));
            updatedUser.setCollege(collegeComboBox.getSelectedItem().toString());
            updatedUser.setHobby(hobbyTextArea.getText());
            
            DatabaseConnector.updateUser(selectedUser, updatedUser);
            refreshTableData();
            clearForm();
            
            JOptionPane.showMessageDialog(this,
                "User updated successfully!",
                "Success",
                JOptionPane.INFORMATION_MESSAGE);
                
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this,
                "Please enter a valid age",
                "Invalid Input",
                JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearForm() {
        nameField.setText("");
        ageField.setText("");
        collegeComboBox.setSelectedIndex(0);
        hobbyTextArea.setText("");
        submitButton.setVisible(false);
        selectedUser = null;
    }

    public void refreshTableData() {
        ArrayList<User> users = DatabaseConnector.getUsers();
        Object[][] newData = new Object[users.size()][5];
        
        for (int i = 0; i < users.size(); i++) {
            User user = users.get(i);
            newData[i][0] = user.getUserId();
            newData[i][1] = user.getName();
            newData[i][2] = user.getAge();
            newData[i][3] = user.getCollege();
            newData[i][4] = user.getHobby();
        }
        
        userTable.setModel(new JTable(newData, new String[]{"User ID", "Name", "Age", "College", "Hobby"}).getModel());
    }
}
