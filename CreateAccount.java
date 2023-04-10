import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class CreateAccount {
    static void create(){
        String username = JOptionPane.showInputDialog(null, "Type the new account's username");
        String password = JOptionPane.showInputDialog(null, "Type the new account's password");

        try {
            String sql = "INSERT INTO user (username, password) VALUES (?, ?)";
            PreparedStatement stt = Connect.connection.prepareStatement(sql);

            stt.setString(1, username);
            stt.setString(2, password);
            int response = stt.executeUpdate();

            if(response > 0){
                String dialog = "User %s successfully created!";
                JOptionPane.showMessageDialog(null, String.format(dialog, username));
            }
            
        } catch (SQLException e) {
            if(e.getErrorCode() == 1062){
                JOptionPane.showMessageDialog(null, "This username has already been taken :(");
                create();
            } else {
                e.printStackTrace();
            }
        }

        Main.isLogged();
    }
}