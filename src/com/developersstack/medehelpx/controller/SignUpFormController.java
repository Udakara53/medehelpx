package com.developersstack.medehelpx.controller;
import com.developersstack.medehelpx.db.Database;
import com.developersstack.medehelpx.entity.User;
import com.developersstack.medehelpx.enums.AccountType;
import com.developersstack.medehelpx.util.PasswordConfig;
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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Optional;

public class SignUpFormController {
    public AnchorPane signupContext;
    public JFXTextField txtFirstName;
    public JFXTextField txtLastName;
    public JFXTextField txtEmail;
    public JFXPasswordField txtPassword;
    public JFXRadioButton rBtnDoctor;

    public void signupOnAction(ActionEvent actionEvent) throws IOException {
        User user = new User(txtFirstName.getText().trim(),txtLastName.getText().trim(),
                txtEmail.getText().trim().toLowerCase(),new PasswordConfig().encrypt(txtPassword.getText().trim()),
                rBtnDoctor.isSelected()?AccountType.DOCTOR:AccountType.PATIENT);

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/medehelpx",
                    "root",
                    "1234"
            );
            String sql = "INSERT INTO user VALUES (?,?,?,?,?,?)";
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setInt(1,1001);
            pstm.setString(2, user.getFirstName());
            pstm.setString(3, user.getLastName());
            pstm.setString(4, user.getEmail());
            pstm.setString(5, user.getPassword());
            pstm.setString(6,user.getAccountType().name());

            int rowCount = pstm.executeUpdate();
            if (rowCount>0){
                new Alert(Alert.AlertType.CONFIRMATION,"Saved.!").show();
                setUi();
            }else{
                new Alert(Alert.AlertType.WARNING,"Try again.!").show();
            }

        }catch (ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }

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
