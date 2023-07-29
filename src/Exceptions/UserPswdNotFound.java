package Exceptions;

public class UserPswdNotFound extends Exception{
    //Exception to catch if the password cannot be found in the database
    //for that username
    public UserPswdNotFound(){
        super("User password not found. Try again.");

    }
    
}
