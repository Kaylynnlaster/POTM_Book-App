package dao;

import dao.books.BooksDao;
import dao.books.BooksDaoImpl;
import dao.users.UsersDao;
import dao.users.UsersDaoImpl;
//import dao.users_books.Users_BooksDao;
//import dao.users_books.Users_BooksDaoImpl;
import java.sql.SQLException;

public class TestDAO {
	
   /**
 * @param args
 */
public static void main(String[] args) {
      UsersDao users = new UsersDaoImpl();
      BooksDao books = new BooksDaoImpl();      
      try {
		users.establishConnection();
		books.establishConnection();
		
		
//		//Test Users getAll
//		System.out.println("Get all Users"); 
//		System.out.println(users.getAll()); 
//		
//		Test findByUsername
		System.out.println();
		System.out.println("Find by Id  username = John122"); 
		System.out.println(users.findByUsername("John123")); 
		
		
		//Test Books findByTitle
//		System.out.println("Books");
//		System.out.println("Find by title The Great Gatsby"); 
//		System.out.println(books.findByTitle("The Great Gatsby")); 
		
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (Throwable e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
      }
  
   
   

}