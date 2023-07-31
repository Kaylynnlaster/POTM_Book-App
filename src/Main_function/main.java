//Main function of the Program

package Main_function;

import java.sql.SQLException;

import dao.users.UsersDaoImpl;

public class main {
    public static void main(String[] args){
        //Calls the log in GUI for the User
        LoginForm loginForm = new LoginForm();
        loginForm.initialize();
    }
}