package com.developersstack.medehelpx.controller;
import com.developersstack.medehelpx.enums.AccountType;
import com.developersstack.medehelpx.util.CrudUtil;
import com.developersstack.medehelpx.util.PasswordConfig;
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
import java.io.IOException;
import java.sql.*;

public class LoginFormController {
    public JFXTextField txtEmail;
    public JFXPasswordField txtPassword;
    public JFXRadioButton rBtnDoctor;
    public ToggleGroup accountType;
    public AnchorPane loginContext;

    public void signInOnAction(ActionEvent actionEvent) throws IOException {
        String email =txtEmail.getText().toLowerCase().trim();
        String password = txtPassword.getText().trim();
        AccountType accountType = rBtnDoctor.isSelected()?AccountType.DOCTOR: AccountType.PATIENT;
        try{
            ResultSet rst = CrudUtil.execute("SELECT*FROM user WHERE email=? AND account_type=?",
                    email,accountType.name());
            if (rst.next()){
                if (new PasswordConfig().decrypt(password, rst.getString("password"))){
                    if (accountType.equals(AccountType.DOCTOR)){
                        setUi("DoctorDashboardForm");
                    }else{
                        setUi("PatientDashboardForm");
                    }
                }
            }else{
                new Alert(Alert.AlertType.WARNING,
                        String.format("We can't find an email %s ",email)).show();
            }
        }catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }

    }

    public void createAnAccountOnAction(ActionEvent actionEvent) throws IOException {
        setUi("SignUpForm");
    }
    private void setUi(String location) throws IOException {
        Stage stage = (Stage) loginContext.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/"+location+".fxml"))));
        stage.centerOnScreen();
        stage.show();
    }
}
