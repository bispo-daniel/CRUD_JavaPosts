import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class ReadPosts {
    static String everyRow;
    static void read(){
        everyRow = "";
        try{
            String sql = "SELECT u.username, p.posted, p.title, p.content FROM post p JOIN user u ON p.fk_user_id = u.id";
            Statement stt = Connect.connection.createStatement();
            ResultSet res = stt.executeQuery(sql);

            while(res.next()){
                String username = res.getString(1);
                String date = res.getString(2);
                String title = res.getString(3);
                String content = res.getString(4);

                String row = "    %s Posted %s\n\n        Title: %s\n        %s \n\n";
                everyRow += String.format(row, username, date, title, content);
            }

            JTextArea text = new JTextArea(30, 30);
            text.setText(everyRow);
            text.setWrapStyleWord(true);
            text.setLineWrap(true);
            text.setEditable(false);
            text.setFocusable(false);
            text.setOpaque(false);
            JScrollPane pane = new JScrollPane(text);
            JOptionPane.showMessageDialog(null, pane);
        } catch(SQLException e){
            e.printStackTrace();
        }
        
        Main.isLogged();
    }

}