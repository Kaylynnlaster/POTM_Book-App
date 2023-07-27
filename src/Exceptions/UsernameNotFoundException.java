package Exceptions;

public class UsernameNotFoundException extends Exception {

    public UsernameNotFoundException(){
        super("User name not found. Try again.");
    }
    
}
