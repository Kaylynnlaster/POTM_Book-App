package Exceptions;

public class PagesOverException extends Exception {

    public PagesOverException(){
        super("Pages over Number of pages. Try again.");
    }

    public PagesOverException(String str){
        super(str);
    }
    
}
