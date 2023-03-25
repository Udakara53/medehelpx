package com.developersstack.medehelpx.controller;

import com.developersstack.medehelpx.db.Database;
import com.developersstack.medehelpx.entity.Patient;
import com.developersstack.medehelpx.view.tm.PatientTM;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.text.SimpleDateFormat;

public class PatientManagementFormController {
    public AnchorPane patientManagementContext;
    public TextField txtSearch;
    public TableView<PatientTM>tblPatient;
    public TableColumn colNic;
    public TableColumn colFirstName;
    public TableColumn colLastName;
    public TableColumn colDob;
    public TableColumn colGender;
    public TableColumn colAddress;
    public TableColumn colAge;
    public TableColumn colEmail;

    public void initialize(){
        loadAllData("");

        txtSearch.textProperty().addListener( (observable, oldValue, newValue) -> {
            loadAllData(newValue);
        });
        colNic.setCellValueFactory(new PropertyValueFactory<>("nic"));
        colFirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        colLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        colGender.setCellValueFactory(new PropertyValueFactory<>("genderType"));
        colDob.setCellValueFactory(new PropertyValueFactory<>("dob"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colAge.setCellValueFactory(new PropertyValueFactory<>("age"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));

    }

    private void loadAllData(String s) {
        s = s.toLowerCase();
        ObservableList<PatientTM> tmList = FXCollections.observableArrayList();
        for (Patient dto:Database.patientTable
             ) {
            if (dto.getFirstName().toLowerCase().contains(s)||dto.getLastName().toLowerCase().contains(s)||dto.getEmail().toLowerCase().contains(s)){
                tmList.add(
                  new PatientTM(dto.getNic(),dto.getFirstName(),dto.getLastName(),new SimpleDateFormat("yyyy-MM-dd").format(dto.getDob()),
                          dto.getGenderType(),dto.getAddress(),10,dto.getEmail())
                );
            }
        }
        tblPatient.setItems(tmList);

    }

    public void backToHomeOnAction(ActionEvent actionEvent) {
    }
}
