package com.developersstack.medehelpx.controller;

import com.developersstack.medehelpx.db.Database;
import com.developersstack.medehelpx.entity.User;
import com.developersstack.medehelpx.enums.AccountType;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class SignUpFormController {
    public AnchorPane signupContext;
    public JFXTextField txtFirstName;
    public JFXTextField txtLastName;
    public JFXTextField txtEmail;
    public JFXPasswordField txtPassword;
    public JFXRadioButton rBtnDoctor;

    public void signupOnAction(ActionEvent actionEvent) throws IOException {
        String firstName = txtFirstName.getText().trim();
        String lastName = txtLastName.getText().trim();
        String email = txtEmail.getText().trim().toLowerCase();
        String password = txtPassword.getText().trim();
        AccountType accountType = rBtnDoctor.isSelected()?AccountType.DOCTOR:AccountType.PATIENT;
        if (isExistsEmail(email)){
            new Alert(Alert.AlertType.WARNING,"email address already exists!").show();
            return;
        }
        Database.userTable.add(new User(firstName,lastName,email,password,accountType));
        new Alert(Alert.AlertType.CONFIRMATION,"Sign up success!").show();
        setUi();
    }
    private boolean isExistsEmail(String email){
        Optional<User> findUser = Database.userTable.stream().filter(e -> e.getEmail().equals(email)).findFirst();
        return findUser.isPresent();
    }

    public void alreadyHaveAnAccountOnAction(ActionEvent actionEvent) throws IOException {
        setUi();
    }
    private void setUi() throws IOException {
        Stage stage= (Stage) signupContext.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/LoginForm.fxml"))));
        stage.centerOnScreen();
        stage.show();
    }
}
