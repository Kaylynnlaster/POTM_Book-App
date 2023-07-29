package services;

import java.sql.SQLException;
import java.util.List;

import dao.books.Books;
import dao.books.BooksDaoImpl;
import dao.users.UsersDaoImpl;

public class GetAllBooks {
    public static List<Books> getAll() {

        BooksDaoImpl bookserv = new BooksDaoImpl();
		try {
			bookserv.establishConnection();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        List<Books> allBooksList;
        allBooksList = bookserv.getAll();
        return allBooksList;
    }
}
