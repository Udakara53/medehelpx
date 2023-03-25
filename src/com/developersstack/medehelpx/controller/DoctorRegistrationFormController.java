package com.developersstack.medehelpx.controller;

import com.developersstack.medehelpx.db.Database;
import com.developersstack.medehelpx.entity.Doctor;
import com.developersstack.medehelpx.entity.User;
import com.developersstack.medehelpx.enums.GenderType;
import com.developersstack.medehelpx.util.Cookie;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class DoctorRegistrationFormController {
    public AnchorPane doctorRegistrationContext;
    public TextField txtFirstName;
    public TextField txtNic;
    public TextField txtEmail;
    public TextField txtLastName;
    public JFXRadioButton rBtnMale;
    public TextField txtContact;
    public TextField txtSpecialization;
    public TextArea txtAddress;
    public ToggleGroup gender;
    public JFXButton btnSubmitData;

    public void initialize(){
        loadUserData();

        txtNic.textProperty().addListener((observable, oldValue, newValue) -> {
           if (Database.doctorTable.stream().filter(e->e.getNic().equals(newValue)).findFirst().isPresent()){
               new Alert(Alert.AlertType.WARNING,"NIC Conflict!").show();
               btnSubmitData.setDisable(true);
               txtNic.setStyle("-fx-border-color: red");
               return;
           }
           btnSubmitData.setDisable(false);
        });

    }

    private void loadUserData() {
        User selectedUser = Cookie.selectedUser;
        txtFirstName.setText(selectedUser.getFirstName());
        txtLastName.setText(selectedUser.getLastName());
        txtEmail.setText(selectedUser.getEmail());
    }


    public void submitOnAction(ActionEvent actionEvent) {
        Doctor doctor = new Doctor(
                txtFirstName.getText().trim(),
                txtLastName.getText().trim(),
                txtNic.getText(),
                txtContact.getText(),
                txtEmail.getText(),
                txtSpecialization.getText(),
                txtAddress.getText(),
                rBtnMale.isSelected()? GenderType.MALE:GenderType.FE_MALE
        );
        Database.doctorTable.add(doctor);
        new Alert(Alert.AlertType.CONFIRMATION,"Registration success!").show();
        Stage stage = (Stage) doctorRegistrationContext.getScene().getWindow();
        stage.close();
    }
}
