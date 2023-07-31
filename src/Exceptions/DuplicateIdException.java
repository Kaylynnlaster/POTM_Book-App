package Exceptions;

public class DuplicateIdException extends Exception{
    //exception to check if there is a dupicated user
    public DuplicateIdException() {
        super("User Id already exists. Try again.");
    }


}
