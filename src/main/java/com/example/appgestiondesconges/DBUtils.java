package com.example.appgestiondesconges;
import java.sql.Connection;
import java.sql.DriverManager;
public class DBUtils {
    public Connection DBlink;
    public Connection getConnection(){
        String DBname="gestion_des_conges";
        String DBuser="root";
        String DBpassword="";
        String url="jdbc:mysql://localhost/"+DBname ;
        try {
            //Class.forName("com.mysql.cj.jdbc.Driver");
            Class.forName("com.mysql.jdbc.Driver");
            DBlink=DriverManager.getConnection(url,DBuser,DBpassword);
        }catch (Exception e){
            e.printStackTrace();
        }
        return DBlink;


    }
}
