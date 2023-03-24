package com.developersstack.db;

import com.developersstack.entity.User;
import com.developersstack.enums.AccountType;

import java.util.ArrayList;

public class Database {
    public static ArrayList<User> userTable = new ArrayList<User>();

    static{
        userTable.add(new User("Tharindu","Udakara","t@gmail.com","1234", AccountType.DOCTOR));
        userTable.add(new User("Hasika","Sandaruwan","h@gmail.com","1234",AccountType.PATIENT));
    }

}
