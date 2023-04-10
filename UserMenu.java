import javax.swing.JOptionPane;

public class UserMenu {
    static void menu(String username){
        String dialog = "Welcome %s!\n\n What do you want to do?\n 0) Exit account\n 1) Create post\n 2) Read posts\n 3) Update post\n 4) Delete post";
        //Functions to delete account, update username, password...
        String optionString = JOptionPane.showInputDialog(null, String.format(dialog, username));
        int option = Integer.parseInt(optionString);

        switch(option){
            case 0:
                Main.logged = false;
                Main.isLogged();
                break;
            case 1:
                CreatePost.create(username);
                break;
            case 2:
                ReadPosts.read();
                break;
            default:
                JOptionPane.showMessageDialog(null, "Type a valid option");
                menu(username);
        }
    }
}
