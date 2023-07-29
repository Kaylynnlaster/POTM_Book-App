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

public class UserBooks {//helper methods to use DAO methods
	
	public static Users_Books userbook;
	public static MasterTable master;
	public static MasterTable planning;
	public static MasterTable inProgress;
	public static MasterTable completed;

	public static UsersDao usersDao;
	public static BooksDao booksDao;
	public static UsersBooksDao usersbooksDao;
	
//returns book after finding title in books table	
	public static Books getBookIdbyTitle(String title) {
		booksDao = new BooksDaoImpl();
		try {
			booksDao.establishConnection();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return booksDao.findByTitle(title);
	}
	//returns List of all books that has a matching userID in users_books table
	//MasterList contains all information from users table, books table and composite usersbooks table
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
	//returns List of all books that has a matching userID in users_books table where pages read = 0
	//MasterList contains all information from users table, books table and composite usersbooks table

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
		Books current_book = booksDao.findById(b.getBook_id());
		Users current_user = usersDao.findById(userId);
		if (b.getPages_read() == 0) {
			planning.add(new MasterTable(current_book, current_user, b)); }
		}
		
		return planning;
	}
	//Returns List of all books that has a matching userID in users_books table 
	//     where pages read = 0 and less than total number of pages in book
	//MasterList contains all information from users table, books table and composite usersbooks table

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
		Books current_book = booksDao.findById(b.getBook_id());
		Users current_user = usersDao.findById(userId);
		if ((b.getPages_read() < current_book.getNum_of_pages())&&(b.getPages_read() >0)) {
			inProgress.add(new MasterTable(current_book, current_user, b)); }
		}
		
		return inProgress;
	}
	//Returns List of all books that has a matching userID in users_books table 
		//     where pages read =  total number of pages in book
		//MasterList contains all information from users table, books table and composite usersbooks table

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
		Books current_book = booksDao.findById(b.getBook_id());
		Users current_user = usersDao.findById(userId);
		if (b.getPages_read() == current_book.getNum_of_pages()) {
			completed.add(new MasterTable(current_book, current_user, b)); }
		}
		
		return completed;
	}
	//Return a single Book object that matching title in book table
	public static Books findbookbytitle (String title) {
		booksDao = new BooksDaoImpl();

		List<Books> books = new ArrayList<Books>();
		List<MasterTable> completed = new ArrayList<MasterTable>();
		
		try {
			booksDao.establishConnection();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return booksDao.findByTitle(title);
	}

	//Add num to pages_read  in usersbooks table for matching userId and bookId/
	//returns false if num + pages_read is greater than the number of books

	public static boolean addPagesRead(int userId, int bookId, int num)
	{
		boolean success = false;
		usersbooksDao = new UsersBooksDaoImpl();
		booksDao = new BooksDaoImpl();
		
		Users_Books usersBook;
		try {
			usersbooksDao.establishConnection();
			booksDao.establishConnection();
			usersBook = usersbooksDao.findByUserIdBookId(userId, bookId);
			Books book = booksDao.findById(bookId);
			int newPages = num + usersBook.getPages_read();
			if (newPages > book.getNum_of_pages())
			{
				return false;
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		success = usersbooksDao.updatePagesRead(userId, bookId, num);

		return success;
	}
	//inserts userId, employee id, and pages read = 0 into usersbooks table	
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
