package Exceptions;

public class DuplicateIdException extends Exception{

    public DuplicateIdException() {
        super("User Id already exists. Try again.");
    }


}
