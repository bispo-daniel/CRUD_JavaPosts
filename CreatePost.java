import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class CreatePost {
    static void create(String username){
        String title = JOptionPane.showInputDialog(null, "What is your post's title?");
        String content = JOptionPane.showInputDialog(null, "Type your post's content");

        try {
            String getUserId = "SELECT * FROM user WHERE username = ?";
            PreparedStatement statement = Connect.connection.prepareStatement(getUserId);
            statement.setString(1, username);
            ResultSet res = statement.executeQuery();

            while (res.next()) {
                int userIdFromDB = res.getInt(1);

                String createNewPost = "INSERT INTO post (title, content, fk_user_id) VALUES (?, ?, ?)";
                PreparedStatement preparedStatement = Connect.connection.prepareStatement(createNewPost);
    
                preparedStatement.setString(1, title);
                preparedStatement.setString(2, content);
                preparedStatement.setInt(3, userIdFromDB);
                
                int response = preparedStatement.executeUpdate();
                if(response > 0){
                    String dialog = "Post '%s' successfully created!";
                    String formatedString2 = String.format(dialog, title);
                    JOptionPane.showMessageDialog(null, formatedString2);
                }       
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        Main.isLogged();
    }
}