package Exceptions;

public class UserNotFound extends Exception{
    
    public UserNotFound() {
        super("User not found. Try again.");
    }

    public UserNotFound(String str) {
        super(str);
    }
}
