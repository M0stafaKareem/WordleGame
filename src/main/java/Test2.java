/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */



import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;

/**
 *
 * @author Dell G3
 */
public class Test2 {
    public static void main(String[] args) {
        try {
            // TODO add your handling code here:

            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql1?zeroDateTimeBehavior=CONVERT_TO_NULL","root","");
            PreparedStatement ps=con.prepareStatement("INSERT INTO leaderboard (PlayerName,PlayerScore) VALUES (?,?)");
            ps.setString(1, "Alas");
            ps.setString(2,"0");
            ps.executeUpdate();
            System.out.print("Add Success");
            //JOptionPane.showMessageDialog(this,"Connected");
            

        } catch (Exception ex) {
            //Logger.getLogger(leaderboard.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("NOT SUCCESS");
        }
    }
    
}
