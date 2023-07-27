package Exceptions;

public class UserIdNotFoundException extends Exception{

    public UserIdNotFoundException(){
        super("User id not found. Try again.");
    }

    public UserIdNotFoundException(String str){
        super(str);
    }
    
}
