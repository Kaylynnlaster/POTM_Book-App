package Exceptions;

public class UsernameNotFound extends Exception {

    public UsernameNotFound(){
        super("User name not found. Try again.");
    }

    public UsernameNotFound(String str){
        super(str);
    }
    
}
