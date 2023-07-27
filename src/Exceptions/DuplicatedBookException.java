package Exceptions;

public class DuplicatedBookException extends Exception{

    public DuplicatedBookException() {
        super("Duplicated book found. Cannot add.");
    }
    
}
