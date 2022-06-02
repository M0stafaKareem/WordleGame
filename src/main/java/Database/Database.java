/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package Database;

import java.sql.Connection;
//import java.sql.PreparedStatement;
import java.sql.DriverManager;
import java.sql.Statement;
//import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Dell G3
 */
public class Database {
   private Connection cn;
   private Statement st;

   
   
    public Connection openConnection() throws SQLException{
        if(cn==null){
            //String url="jdbc:mysql//localhost/";
            String dbName="Xampp";
            String driver="com.mysql.cj.jdbc.Driver";
            String userName="root";
            String pass="";
            try{
                Class.forName(driver);
                this.cn=(Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/wordle",userName,pass);
                System.out.println("CONNECTED");
            }
            catch(ClassNotFoundException | SQLException sqle){
                System.out.println("FAILEDDDD  ");
            }
        }
        
        return cn;
    }

    //void openConnection() {
      //  throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    //}
}
