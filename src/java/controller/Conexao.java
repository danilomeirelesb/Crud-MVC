package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    public static Connection getConnection(){
         try{
             DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            return DriverManager.getConnection("jdbc:mysql://localhost/gestaoEscola","root","");
             } catch(SQLException ex){
                 throw new RuntimeException(ex);
             }   
        }
}
