/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Database;
import com.mycompany.gui.Database2;
import java.sql.SQLException;
/**
 *
 * @author Dell G3
 */
public class Main {
    public static void main(String[] args) throws SQLException{
        Database db=new Database();
        db.openConnection();
        Database2 obj=new Database2();
        //obj.PrintUsers();
    }
    
}
