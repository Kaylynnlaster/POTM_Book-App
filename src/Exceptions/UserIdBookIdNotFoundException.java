package Exceptions;

public class UserIdBookIdNotFoundException extends Exception{
    
    public UserIdBookIdNotFoundException (){
        super("User id, book id combination not found. Try again.");
    }

}
