package Exceptions;

public class PageOverNumOfPagesException extends Exception{

    public PageOverNumOfPagesException(){
        super("Page number inputed exceed possible number of pages for the book. Try again.");
    }
    
}
