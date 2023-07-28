package Main_function;

import java.sql.SQLException;

import dao.users.UsersDaoImpl;

public class main {
    public static void main(String[] args){
        System.out.println("hello");
        LoginForm loginForm = new LoginForm();
        loginForm.initialize();
    }
}