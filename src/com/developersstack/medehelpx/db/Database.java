package com.developersstack.medehelpx.db;

import com.developersstack.medehelpx.entity.Doctor;
import com.developersstack.medehelpx.entity.User;
import com.developersstack.medehelpx.enums.AccountType;
import com.developersstack.medehelpx.enums.GenderType;

import java.util.ArrayList;

public class Database {
    public static ArrayList<User> userTable = new ArrayList<User>();

    public static ArrayList<Doctor> doctorTable = new ArrayList<>();


    static{
        userTable.add(new User("Tharindu","Udakara","t@gmail.com","1234", AccountType.DOCTOR));
        userTable.add(new User("Hasika","Sandaruwan","h@gmail.com","1234",AccountType.PATIENT));

        doctorTable.add(new Doctor(
           "Tharindu","Udakara",
                "9612345678V","+94 71 2345 698",
                "t@gmail.com","eye",
                "Colombo", GenderType.MALE
        ));
    }

}
