import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

import javax.swing.JOptionPane;

public class Login {
    static boolean userFound = false;
    static void login(){
        String username = JOptionPane.showInputDialog(null, "Type your username");
        String password = JOptionPane.showInputDialog(null, "Type your password");

        try {

            String sql = "SELECT * FROM user";
            Statement stt = Connect.connection.createStatement();
            ResultSet res = stt.executeQuery(sql);
            
            while(res.next()){
                String usernameFromDB = res.getString("username");
                String passwordFromDB = res.getString("password");

                if(username.equals(usernameFromDB) && password.equals(passwordFromDB)){
                    userFound = true;
                    Main.logged = true;
                    Main.username = usernameFromDB;
                    break;
                }

            }
            
            if(userFound == false){
                JOptionPane.showMessageDialog(null, "User not found");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        userFound = false;
        Main.isLogged();
    }
}
