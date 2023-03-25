package com.developersstack.medehelpx.db;

import com.developersstack.medehelpx.entity.Doctor;
import com.developersstack.medehelpx.entity.Patient;
import com.developersstack.medehelpx.entity.User;
import com.developersstack.medehelpx.enums.AccountType;
import com.developersstack.medehelpx.enums.GenderType;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Database {
    public static ArrayList<User> userTable = new ArrayList<User>();

    public static ArrayList<Doctor> doctorTable = new ArrayList<>();

    public static ArrayList<Patient> patientTable = new ArrayList<>();



    static{
        userTable.add(new User("Tharindu","Udakara","t@gmail.com","1234", AccountType.DOCTOR));
        userTable.add(new User("Hasika","Sandaruwan","h@gmail.com","1234",AccountType.PATIENT));

        doctorTable.add(new Doctor(
           "Tharindu","Udakara",
                "9612345678V","+94 71 2345 698",
                "t@gmail.com","eye",
                "Colombo", GenderType.MALE
        ));
        try{
            patientTable.add(new Patient(
                    "2002", "Oshara", "Irundi", new Date(),
                    GenderType.FE_MALE, "raagama","erathna@gmail.com"
            ));
            patientTable.add(new Patient(
                    "98123", "Rashinthi", "Era", new SimpleDateFormat("yyyy-MM-dd").parse("1998-10-12"),
                    GenderType.FE_MALE, "galaha","ras@gmail.com"
            ));
            patientTable.add(new Patient(
                    "961", "Shan", "Wijesinghe", new SimpleDateFormat("yyyy-MM-dd").parse("2008-10-12"),
                    GenderType.MALE, "pibura","wij@gmail.com"
            ));
            patientTable.add(new Patient(
                    "951", "Dilan", "Eranga", new SimpleDateFormat("yyyy-MM-dd").parse("2018-10-12"),
                    GenderType.MALE, "ambilipitiya","dil@gmail.com"
            ));
            patientTable.add(new Patient(
                    "90", "Dusantha", "sangeeth", new Date(),
                    GenderType.MALE, "nawalapitiya","dus@gmail.com"
            ));
        }catch (Exception e){
            e.printStackTrace();
        }

    }

}
