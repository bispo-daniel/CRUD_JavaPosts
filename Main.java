import javax.swing.JOptionPane;

public class Main {
    static boolean logged = false;
    static String username;

    static void landingPage(){
        String options = "Welcome!\n\n What do you want to do?\n 1) Login\n 2) Create Account\n 0) Exit application";
        String optionString = JOptionPane.showInputDialog(null, options);
        int option = Integer.parseInt(optionString);

        switch(option){
            case 0:
                System.exit(0);
                break;
            case 1:
                Login.login();
                break;
            case 2:
                CreateAccount.create();
                break;
            default:
                JOptionPane.showMessageDialog(null, "Type a valid option");
                landingPage();
        }
    }

    static void isLogged(){
        if(logged == false){
            landingPage();
        } else {
            UserMenu.menu(username);
        }
    }
    public static void main(String[] args) {
        try {
            Connect.getConnection();
            isLogged();
        } catch (NumberFormatException e){
            logged = false;
            JOptionPane.showMessageDialog(null, "You probably typed a number where a number is expected!");
        }
    }
}