package services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.books.Books;
import dao.books.BooksDao;
import dao.books.BooksDaoImpl;
import dao.users.Users;
import dao.users.UsersDao;
import dao.users.UsersDaoImpl;
import dao.users_books.UsersBooksDao;
import dao.users_books.UsersBooksDaoImpl;
import dao.users_books.Users_Books;

public class UserBooks {
	
	public static Users_Books userbook;
	public static Users user;
	public static Books book;
	public static MasterTable master;
	public static MasterTable planning;
	public static MasterTable inProgress;
	public static MasterTable completed;

	public static UsersDao usersDao;
	public static BooksDao booksDao;
	public static UsersBooksDao usersbooksDao;
	
	
	public static List<MasterTable> getMaster(int userId) {
		usersDao = new UsersDaoImpl();
		booksDao = new BooksDaoImpl();
		usersbooksDao = new UsersBooksDaoImpl();
		List<Users_Books> userbooks = new ArrayList<Users_Books>();
		List<MasterTable> masters = new ArrayList<MasterTable>();
		
		try {
			usersDao.establishConnection();
			booksDao.establishConnection();
			usersbooksDao.establishConnection();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		userbooks = usersbooksDao.findBooksByUserId(userId);
		for (Users_Books book : userbooks) {
		Books current_book = booksDao.findById(book.getBook_id());
		Users current_user = usersDao.findById(userId);
		masters.add(new MasterTable(current_book, current_user, book));
		}
		
		return masters;
	}
	public static List<MasterTable> getPlanning(int userId) {
		usersDao = new UsersDaoImpl();
		booksDao = new BooksDaoImpl();
		usersbooksDao = new UsersBooksDaoImpl();
		List<Users_Books> userbooks = new ArrayList<Users_Books>();
		List<MasterTable> planning = new ArrayList<MasterTable>();
		
		try {
			usersDao.establishConnection();
			booksDao.establishConnection();
			usersbooksDao.establishConnection();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		userbooks = usersbooksDao.findBooksByUserId(userId);
		for (Users_Books b : userbooks) {
		Books current_book = booksDao.findById(book.getBook_id());
		Users current_user = usersDao.findById(userId);
		if (b.getPages_read() == 0) {
			planning.add(new MasterTable(current_book, current_user, b)); }
		}
		
		return planning;
	}
	public static List<MasterTable> getProgress(int userId) {
		usersDao = new UsersDaoImpl();
		booksDao = new BooksDaoImpl();
		usersbooksDao = new UsersBooksDaoImpl();
		List<Users_Books> userbooks = new ArrayList<Users_Books>();
		List<MasterTable> inProgress = new ArrayList<MasterTable>();
		
		try {
			usersDao.establishConnection();
			booksDao.establishConnection();
			usersbooksDao.establishConnection();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		userbooks = usersbooksDao.findBooksByUserId(userId);
		for (Users_Books b : userbooks) {
		Books current_book = booksDao.findById(book.getBook_id());
		Users current_user = usersDao.findById(userId);
		if (b.getPages_read() < book.getNum_of_pages()) {
			inProgress.add(new MasterTable(current_book, current_user, b)); }
		}
		
		return inProgress;
	}
	public static List<MasterTable> getCompleted(int userId) {
		usersDao = new UsersDaoImpl();
		booksDao = new BooksDaoImpl();
		usersbooksDao = new UsersBooksDaoImpl();
		List<Users_Books> userbooks = new ArrayList<Users_Books>();
		List<MasterTable> completed = new ArrayList<MasterTable>();
		
		try {
			usersDao.establishConnection();
			booksDao.establishConnection();
			usersbooksDao.establishConnection();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		userbooks = usersbooksDao.findBooksByUserId(userId);
		for (Users_Books b : userbooks) {
		Books current_book = booksDao.findById(book.getBook_id());
		Users current_user = usersDao.findById(userId);
		if (b.getPages_read() == book.getNum_of_pages()) {
			completed.add(new MasterTable(current_book, current_user, b)); }
		}
		
		return completed;
	}
	public static Users_Books newBook(int userId, int bookId)
	{
		usersbooksDao = new UsersBooksDaoImpl();
		try {
			usersbooksDao.establishConnection();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return usersbooksDao.add(userId, bookId);
	}
	public static boolean addPagesRead(int userId, int bookId, int num)
	{
		usersbooksDao = new UsersBooksDaoImpl();
		try {
			usersbooksDao.establishConnection();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return usersbooksDao.updatePagesRead(userId, bookId, num);
	}
	public static Users_Books newPlanning(int userId, int bookId)
	{
		usersbooksDao = new UsersBooksDaoImpl();
		try {
			usersbooksDao.establishConnection();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return usersbooksDao.add(userId, bookId);
	}
	
	
	
	

}
