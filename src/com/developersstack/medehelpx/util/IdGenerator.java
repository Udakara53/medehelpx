package com.developersstack.medehelpx.util;

import java.sql.*;

public class IdGenerator {
    public int generateId(){
      try{
          Class.forName("com.mysql.cj.jdbc.Driver");
          Connection connection = DriverManager.getConnection(
                  "jdbc:mysql://localhost:3306/medehelpx",
                  "root",
                  "1234"
          );
          ResultSet rst = CrudUtil.executeQuery("SELECT user_id FROM user ORDER BY user_id DESC LIMIT 1");
          if (rst.next()){
              return rst.getInt("user_id")+1;
          }

      } catch (SQLException | ClassNotFoundException e) {
          e.printStackTrace();
      }
        return 1;
    }
}
