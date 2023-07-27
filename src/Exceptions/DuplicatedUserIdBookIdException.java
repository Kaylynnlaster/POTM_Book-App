package Exceptions;

public class DuplicatedUserIdBookIdException extends Exception{

    public DuplicatedUserIdBookIdException() {
        super("Duplciate combination of user id book id found. Try again.");
    }
}
