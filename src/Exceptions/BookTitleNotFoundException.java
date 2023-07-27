package Exceptions;

public class BookTitleNotFoundException extends Exception{

    public BookTitleNotFoundException(){
        super("Book title not found. Try again.");
    }
    
}
