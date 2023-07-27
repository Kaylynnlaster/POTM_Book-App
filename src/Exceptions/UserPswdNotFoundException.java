package Exceptions;

public class UserPswdNotFound extends Exception{

    public UserPswdNotFound(String str){
        super(str);

    }

    public UserPswdNotFound(){
        super("User password not found. Try again.");

    }
    
}
