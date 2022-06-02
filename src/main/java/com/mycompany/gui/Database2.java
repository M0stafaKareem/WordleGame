/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gui;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Dell G3
 */
public class Database2 {

    public String userName = "root";
    public String pass = "";

    public void INSERTNEWUSER(String name,String score  ) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection cn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/wordle", userName, pass);
            PreparedStatement ps = cn.prepareStatement("INSERT INTO `leaderboard` (`Id`, `Name`, `Rank`, `Score`) VALUES (NULL,? ,'', ?)");
            //PreparedStatement ps = cn.prepareStatement("DELETE from leaderboard");

            ps.setString(1, name);
            ps.setString(2, score);
            //ps.setString(2, age);
            ps.executeUpdate();
        } catch (Exception ex) {
            Logger.getLogger(UserPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String InsertScore(String Score) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection cn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/wordle", userName, pass);
           
            PreparedStatement ps = cn.prepareStatement("insert into leaderboard(Score)values(?)");
            //PreparedStatement ps = cn.prepareStatement("DELETE from leaderboard");
            
            ps.setString(1, Score);
            ps.executeUpdate();
        } catch (Exception ex) {
            Logger.getLogger(UserPanel.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error in insert Score");
        }
        return null;

    }

    public boolean SearchName(String name) {
        boolean usernameExists = false;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection cn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/wordle", userName, pass);
            
            PreparedStatement ps = cn.prepareStatement("SELECT Id,Name FROM `leaderboard` WHERE `Name` LIKE ?");
            ps.setString(1, name);
            ResultSet r1 = ps.executeQuery();
            if (r1.next()) {
                usernameExists = true;
                System.out.println("Name found GO update");
            }

        } catch (SQLException e) {
            System.out.println("SQL Exception: " + e.toString());
            System.out.println("FALSEEE");
        } catch (ClassNotFoundException cE) {
            System.out.println("Class Not Found Exception: " + cE.toString());
        }

        return usernameExists;

    }

    public void UpdateUser(String name, String score) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection cn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/wordle", userName, pass);
            PreparedStatement ps = cn.prepareStatement("UPDATE leaderboard set Score=? WHERE Name=? ");
            ps.setString(1, score);

            ps.setString(2, name);
            ps.executeUpdate();
            //System.out.println(name+" " + score);

        } catch (SQLException e) {
            System.out.println("SQL Exception: " + e.toString());
            System.out.println("FALSEEE");
        } catch (ClassNotFoundException cE) {
            System.out.println("Class Not Found Exception: " + cE.toString());
        }
    }
    
    public String PrintName(String query){
         try {
            // TODO add your handling code here:

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection cn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/wordle", userName, pass);
            Statement st = cn.createStatement();
            //PreparedStatement ps = cn.prepareStatement("SELECT * FROM leaderboard");
            //PreparedStatement ps = cn.prepareStatement("DELETE from leaderboard");
          
            ResultSet results = st.executeQuery(query);
            while (results.next()) {
                
                
                String name = results.getString("name");
                return ("." + name + ".. ");
                //return "success";

                //System.out.println("Id: " +id +" Name:"+name+""+": "+score);
            }

        } catch (Exception ex) {
            Logger.getLogger(UserPanel.class
                    .getName()).log(Level.SEVERE, null, ex);
            return "Error in PrintName Method";
        }
        return null;
    }

    public String PrintUsers(String query) {
        try {
            // TODO add your handling code here:

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection cn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/wordle", userName, pass);
            Statement st = cn.createStatement();
            //PreparedStatement ps = cn.prepareStatement("SELECT * FROM leaderboard");
            //PreparedStatement ps = cn.prepareStatement("DELETE from leaderboard");
          
            ResultSet results = st.executeQuery(query);
            while (results.next()) {
                int id = results.getInt("Id");
                String S = Integer.toString(id);
                int score = results.getInt("score");
                String s2 = Integer.toString(score);
                String name = results.getString("name");
                return (S + "." + name + "     " + s2);
                //return "success";

                //System.out.println("Id: " +id +" Name:"+name+""+": "+score);
            }

        } catch (Exception ex) {
            Logger.getLogger(UserPanel.class
                    .getName()).log(Level.SEVERE, null, ex);
            return "Error in PrintUsers Method";
        }
        return null;
    }

    public String getScore(String name ) {
        try {
            // TODO add your handling code here:

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection cn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/wordle", userName, pass);
            Statement st = cn.createStatement();
            
            ResultSet results = st.executeQuery("SELECT DISTINCT Score FROM leaderboard where Name='" + name + "'");
           
            while (results.next()) {

                int score = results.getInt("score");
                String s2 = Integer.toString(score);
                return s2;
            }

            
        } catch (Exception ex) {
            Logger.getLogger(UserPanel.class
                    .getName()).log(Level.SEVERE, null, ex);
            return "Error in PrintScores Method";
        }
        return null;
    }

    public void Deleteall() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection cn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/wordle", userName, pass);
            //PreparedStatement ps = cn.prepareStatement("insert into leaderboard(name)values(?)");
            PreparedStatement ps = cn.prepareStatement("DELETE from leaderboard");

           
            ps.executeUpdate();

        } catch (Exception ex) {
            Logger.getLogger(UserPanel.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void ResetId() {
        String s1 = "set @autoid :=0";
        String s4 = "update leaderboard set id = @autoid := (@autoid+1)";
        String s3 = "alter table leaderboard Auto_Increment = 1";
        try {
            // TODO add your handling code here:

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection cn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/wordle", userName, pass);
            //PreparedStatement ps = cn.prepareStatement("insert into leaderboard(name)values(?)");
            PreparedStatement ps1 = cn.prepareStatement(s1);
            PreparedStatement ps2 = cn.prepareStatement(s3);
            PreparedStatement ps3 = cn.prepareStatement(s4);

            
            ps1.executeUpdate();
            ps2.executeUpdate();
            ps3.executeUpdate();

        } catch (Exception ex) {
            Logger.getLogger(UserPanel.class
                    .getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error in ResetId Method");
        }

    }

    public void ResetRank() {
        String s1 = "set @curRank :=0";
        String s4 = "update leaderboard set Rank = @curRank := @curRank + 1";
        String s3 = "alter table leaderboard Auto_Increment = 1";

        try {
            // TODO add your handling code here:

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection cn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/wordle", userName, pass);
            PreparedStatement ps1 = cn.prepareStatement(s1);
            PreparedStatement ps3 = cn.prepareStatement(s4);
            
            ps1.executeUpdate();
            ps3.executeUpdate();

        } catch (Exception ex) {
            Logger.getLogger(UserPanel.class
                    .getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error in ResetId Method");
        }

    }

    public void RankUsers() {
        
        try {
            // TODO add your handling code here:

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection cn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/wordle", userName, pass);
            //PreparedStatement ps = cn.prepareStatement("insert into leaderboard(name)values(?)");

            PreparedStatement ps1 = cn.prepareStatement("SELECT Id,Name,Score,@curRank := @curRank + 1 AS Rank From leaderboard p, (SELECT @curRank := 0) r ORDER BY Score DESC");
            PreparedStatement ps2 = cn.prepareStatement("Alter Table leaderboard Order by Score DESC");
            
            ps2.executeUpdate();
        } catch (Exception ex) {
            Logger.getLogger(UserPanel.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }
}
