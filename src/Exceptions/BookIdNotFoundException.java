package Exceptions;

public class BookIdNotFoundException extends Exception{

    public BookIdNotFoundException() {
        super("Book Id not found. Try again.");
    }

    public BookIdNotFoundException(String str) {
        super(str);
    }

}
