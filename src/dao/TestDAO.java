package dao;

import dao.books.Books;
import dao.books.BooksDao;
import dao.books.BooksDaoImpl;
import dao.users.Users;
import dao.users.UsersDao;
import dao.users.UsersDaoImpl;
import dao.usersBooks.UsersBooks;
import dao.usersBooks.UsersBooksDao;
import dao.usersBooks.UsersBooksDaoImpl;
import java.sql.SQLException;

public class TestDAO {
	
	public static void main(String[] args) {
    	UsersDao users = new UsersDaoImpl();
    	BooksDao books = new BooksDaoImpl(); 
		UsersBooksDao usersDao = new UsersBooksDaoImpl();     
    	try {
			users.establishConnection();
			books.establishConnection();
			usersDao.establishConnection();
		
			//Test Users
			// System.out.println();
			// System.out.println(users.getAll());
			// System.out.println(users.findById(1));
			// users.update(new Users(11, "John", "Smith", "john123", "changed123"));
			// users.delete(11);
			// users.add(new Users("Ethan", "Shin", "john123", "dae"));


			// Test books
			// System.out.println();
			// System.out.println(books.getAll());
			// System.out.println(books.findById(1));
			// System.out.println(books.findByTitle("The Great").toString());
			// books.update(new Books(11, "The power of habit", "Etan", "Shin", 300));
			// books.delete(11);
			// books.add(new Books("The Great Gatsby", "F. Scott", "Fitzgerald" , 180));

			// Test Users Books
			// System.out.println(usersDao.getAll());
			// System.out.println(usersDao.findBooksByUserId(11));
			// System.out.println(usersDao.findUsersByBookId(11));
			// System.out.println(usersDao.findByUserIdBookId(1,3));
			// usersDao.updatePagesRead(1, 2, 281);
			// usersDao.delete(1,3);
			// usersDao.add(1, 3);

		
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
