package Exceptions;

public class DuplicatedUserNameException extends Exception{

    public DuplicatedUserNameException() {
        super("Choose different user name. It already exists.");
    }

    public DuplicatedUserNameException(String str) {
        super(str);
    }
    
}
