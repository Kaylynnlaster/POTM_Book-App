package services;

import java.sql.SQLException;
import java.util.List;

import dao.books.Books;
import dao.books.BooksDaoImpl;
import dao.users.UsersDaoImpl;

//A service to get all of the books in the database
public class GetAllBooks {
    public static List<Books> getAll() {

		//Creates a booksdaoimpl object
        BooksDaoImpl bookserv = new BooksDaoImpl();
		//Tries to establic a connection
		try {
			bookserv.establishConnection();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//Creates an empty list to hold the books
        List<Books> allBooksList;
		//Calls the booksdaoimpl method getAll to get all the books in the database
        allBooksList = bookserv.getAll();
		//Returns that list
        return allBooksList;
    }
}
