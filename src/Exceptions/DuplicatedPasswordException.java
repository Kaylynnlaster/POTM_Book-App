package Exceptions;

public class DuplicatedPasswordException extends Exception{

    public DuplicatedPasswordException() {
        super("Choose different password. It already exists.");
    }

}