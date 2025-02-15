package Controller;

import javax.swing.JOptionPane;

import Model.User;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Controller {

    @FXML
    private TextField nameField;

    @FXML
    private TextField ageField;

    @FXML
    private TextField emailField;

    @FXML
    private Button submitButton;

    @FXML
    private void handleSubmit() {
        try
        {
            User user = new User();
            
            String name = nameField.getText();
            String age = ageField.getText();
            String email = emailField.getText();

            user.setAge(Integer.parseInt(age));
            user.setEmail(email);
            user.setName(name);

            if (user.getName().isBlank()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Failed");
                alert.setHeaderText(null);
                alert.setContentText("Please fill your name!");
                alert.showAndWait();
            }else if(user.getAge() <= 0){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Failed");
                alert.setHeaderText(null);
                alert.setContentText("Please check your age is valid value!");
                alert.showAndWait();
            }else if(user.getEmail().isBlank()){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Failed");
                alert.setHeaderText(null);
                alert.setContentText("Please fill your email!");
                alert.showAndWait();
            }else{
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Registration Info");
                alert.setHeaderText(null);
                alert.setContentText(user.toString());
                alert.showAndWait();
            }
        }
        catch(NumberFormatException nex)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Failed");
            alert.setHeaderText(null);
            alert.setContentText("Please check your age is valid value!");
            alert.showAndWait();
        }
        catch(Exception ex)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Failed");
            alert.setHeaderText(null);
            alert.setContentText("Oops! Something went wrong!");
            alert.showAndWait();
        }
    }
}
