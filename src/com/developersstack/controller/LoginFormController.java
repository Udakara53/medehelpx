package com.developersstack.controller;

import com.developersstack.db.Database;
import com.developersstack.entity.User;
import com.developersstack.enums.AccountType;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;

public class LoginFormController {
    public JFXTextField txtEmail;
    public JFXPasswordField txtPassword;
    public JFXRadioButton rBtnDoctor;
    public ToggleGroup accountType;
    public AnchorPane loginContext;

    public void signInOnAction(ActionEvent actionEvent) {
        String email =txtEmail.getText().toLowerCase().trim();
        String password = txtPassword.getText().trim();
        AccountType accountType = AccountType.PATIENT;
        if (rBtnDoctor.isSelected()){
            accountType =AccountType.DOCTOR;

        }
        for (User dto:Database.userTable
             ) {
            if (dto.getEmail().equals(email)){
                if (dto.getPassword().equals(password)){
                    if (dto.getAccountType().equals(accountType)) {
                        new Alert(Alert.AlertType.CONFIRMATION,"LoginSuccess!").show();
                        return;
                    }else{
                        new Alert(Alert.AlertType.WARNING,String.format("We can't find Your %s Account ",accountType)).show();
                        return;
                    }
                }else{
                    new Alert(Alert.AlertType.WARNING,"Password is incorrect").show();
                    return;
                }
            }
        }new Alert(Alert.AlertType.WARNING,String.format("we can't find %s address ",email)).show();

    }

    public void createAnAccountOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) loginContext.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/SignUpForm.fxml"))));
        stage.centerOnScreen();
        stage.show();
    }
}
