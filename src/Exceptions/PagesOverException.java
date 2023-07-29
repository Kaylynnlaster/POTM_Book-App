package Exceptions;

public class PagesOverException extends Exception {
    //Exception to check if the pages read enter in the update function is over the bages in the book
    public PagesOverException(){
        super("Pages over the limit");
    }

}
