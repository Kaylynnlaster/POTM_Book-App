package services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Exceptions.BookIdNotFoundException;
import Exceptions.DuplicatedUserIdBookIdException;
import Exceptions.PageOverNumOfPagesException;
import Exceptions.UserIdBookIdNotFoundException;
import Exceptions.UserIdNotFoundException;
import dao.books.Books;
import dao.books.BooksDao;
import dao.books.BooksDaoImpl;
import dao.users.Users;
import dao.users.UsersDao;
import dao.users.UsersDaoImpl;
import dao.usersBooks.UsersBooksDao;
import dao.usersBooks.UsersBooksDaoImpl;
import dao.usersBooks.UsersBooks;

public class UserBooks {
	
	public static UsersBooks userbook;
	public static Users user;
	public static Books book;
	public static MasterTable master;
	public static MasterTable planning;
	public static MasterTable inProgress;
	public static MasterTable completed;

	public static UsersDao usersDao;
	public static BooksDao booksDao;
	public static UsersBooksDao usersbooksDao;
	
	
	public static List<MasterTable> getMaster(int userId) throws UserIdNotFoundException, BookIdNotFoundException {
		usersDao = new UsersDaoImpl();
		booksDao = new BooksDaoImpl();
		usersbooksDao = new UsersBooksDaoImpl();
		List<UsersBooks> userbooks = new ArrayList<UsersBooks>();
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
		for (UsersBooks book : userbooks) {
		Books current_book = booksDao.findById(book.getBookId());
		Users current_user = usersDao.findById(userId);
		masters.add(new MasterTable(current_book, current_user, book));
		}
		
		return masters;
	}
	public static List<MasterTable> getPlanning(int userId) throws BookIdNotFoundException, UserIdNotFoundException {
		usersDao = new UsersDaoImpl();
		booksDao = new BooksDaoImpl();
		usersbooksDao = new UsersBooksDaoImpl();
		List<UsersBooks> userbooks = new ArrayList<UsersBooks>();
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
		for (UsersBooks b : userbooks) {
		Books current_book = booksDao.findById(book.getBookId());
		Users current_user = usersDao.findById(userId);
		if (b.getPagesRead() == 0) {
			planning.add(new MasterTable(current_book, current_user, b)); }
		}
		
		return planning;
	}
	
	public static List<MasterTable> getProgress(int userId) throws UserIdNotFoundException, BookIdNotFoundException {
		usersDao = new UsersDaoImpl();
		booksDao = new BooksDaoImpl();
		usersbooksDao = new UsersBooksDaoImpl();
		List<UsersBooks> userbooks = new ArrayList<UsersBooks>();
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
		for (UsersBooks b : userbooks) {
		Books current_book = booksDao.findById(book.getBookId());
		Users current_user = usersDao.findById(userId);
		if (b.getPagesRead() < book.getNumOfPages()) {
			inProgress.add(new MasterTable(current_book, current_user, b)); }
		}
		
		return inProgress;
	}
	public static List<MasterTable> getCompleted(int userId) throws BookIdNotFoundException, UserIdNotFoundException {
		usersDao = new UsersDaoImpl();
		booksDao = new BooksDaoImpl();
		usersbooksDao = new UsersBooksDaoImpl();
		List<UsersBooks> userbooks = new ArrayList<UsersBooks>();
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
		for (UsersBooks b : userbooks) {
			Books current_book = booksDao.findById(book.getBookId());
			Users current_user = usersDao.findById(userId);
			if (b.getPagesRead() == book.getNumOfPages()) {
				completed.add(new MasterTable(current_book, current_user, b)); 
			}
		}
		
		return completed;
	}

	public static UsersBooks newBook(int userId, int bookId) throws DuplicatedUserIdBookIdException
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
	public static boolean addPagesRead(int userId, int bookId, int num) throws UserIdBookIdNotFoundException, BookIdNotFoundException, PageOverNumOfPagesException
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
	public static UsersBooks newPlanning(int userId, int bookId) throws DuplicatedUserIdBookIdException
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
