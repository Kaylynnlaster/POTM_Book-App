package Exceptions;

public class UserIdNotFound extends Exception{
    //Exception to catch if a user does not exist or cannont be
    //found by the given username at login
    public UserIdNotFound(){
        super("User id not found. Try again.");
    }
    
}
