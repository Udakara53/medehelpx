package com.developersstack.medehelpx.controller;

import com.developersstack.medehelpx.db.Database;
import com.developersstack.medehelpx.entity.Doctor;
import com.developersstack.medehelpx.util.Cookie;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Optional;

public class DoctorDashboardFormController {

    public Label lblDate;
    public Label lblTime;
    public AnchorPane doctorDashboardContext;

    public void initialize() throws IOException {
        //checkUser();
        initializeData();
        checkDoctorData();
    }

    private void checkDoctorData() throws IOException {
        Optional<Doctor> selectedDoctor =
                Database.doctorTable.stream().filter(g -> g.getEmail().equals("t@gmail.com")).findFirst();
        if (!selectedDoctor.isPresent()){
            setUi("DoctorRegistrationForm");
        }
    }

    private void initializeData() {
        lblDate.setText(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(0),
                        e->{
                            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("hh:mm:ss");
                            lblTime.setText(LocalTime.now().format(dtf));
                        }),
                new KeyFrame(Duration.seconds(1))
        );
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }




    public void checkUser() throws IOException {
        if (null==Cookie.selectedUser){
            Stage stage= (Stage) doctorDashboardContext.getScene().getWindow();
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/LoginForm.fxml"))));
            stage.centerOnScreen();
            stage.show();
        }
    }

    public void logOutOnAction(ActionEvent actionEvent) throws IOException {
        setUi("LoginForm");
    }
    private void setUi(String location) throws IOException {
        Stage stage = (Stage) doctorDashboardContext.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/"+location+".fxml"))));
        stage.centerOnScreen();
        stage.show();
    }
}
