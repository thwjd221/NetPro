package Client;

import Client.Login;

public class Login_test {
        public static void main(String args[]) {
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Login login = new Login();
                login.setVisible(true);
            }
        });
    }
}
