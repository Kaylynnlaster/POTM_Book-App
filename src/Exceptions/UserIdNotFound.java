package Exceptions;

public class UserIdNotFound extends Exception{

    public UserIdNotFound(){
        super("User id not found. Try again.");
    }
    
}
