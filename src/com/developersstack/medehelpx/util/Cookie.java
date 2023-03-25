package com.developersstack.medehelpx.util;

import com.developersstack.medehelpx.db.Database;
import com.developersstack.medehelpx.entity.User;

public class Cookie {
    public static User selectedUser= Database.userTable.get(0);
}
