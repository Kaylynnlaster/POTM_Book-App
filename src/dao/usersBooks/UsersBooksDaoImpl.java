package dao.usersBooks;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Exceptions.BookIdNotFoundException;
import Exceptions.DuplicatedUserIdBookIdException;
import Exceptions.PageOverNumOfPagesException;
import Exceptions.UserIdBookIdNotFoundException;
import Exceptions.UserIdNotFoundException;
import connection.ConnectionManager;

public class UsersBooksDaoImpl implements UsersBooksDao {
	private Connection connection = null;

	@Override
	public void establishConnection() throws ClassNotFoundException, SQLException {
	    if (this.connection == null) {
	        this.connection = ConnectionManager.getConnection();
	    }
	}

	@Override
	public void closeConnection() throws SQLException {
	    this.connection.close();
	}

	@Override
	public List<UsersBooks> getAll() {
	    List<UsersBooks> usersBooks = new ArrayList<UsersBooks>();
	    String sql = "SELECT * FROM users_books";
	    try {
	    	Statement stmt = this.connection.createStatement();
	        ResultSet rs = stmt.executeQuery(sql);
	        while(rs.next()) {
	        	usersBooks.add(new UsersBooks(rs.getInt(1), rs.getInt(2), rs.getInt(3)));
	        }
	    } catch (SQLException e) {
			e.printStackTrace();
	    }
	    return usersBooks;
	}

	@Override
	public List<UsersBooks> findBooksByUserId(int userId) throws UserIdNotFoundException {
		List<UsersBooks> users_books = new ArrayList<UsersBooks>();
		String sql = "SELECT * FROM users_books WHERE user_id = ?";
	    try (PreparedStatement pstmt = this.connection.prepareStatement(sql);){
	        pstmt.setInt(1, userId);
	        ResultSet rs = pstmt.executeQuery();
			if (!rs.isBeforeFirst()) {
				throw new UserIdNotFoundException();
			}
	        while(rs.next()) {
	            users_books.add(new UsersBooks(rs.getInt(1), rs.getInt(2), rs.getInt(3)));
	        }	
	    } catch (SQLException e) {
			e.printStackTrace();
		}
	    return users_books;
	}

	@Override
	public List<UsersBooks> findUsersByBookId(int bookId) throws BookIdNotFoundException {
		List<UsersBooks> users_books = new ArrayList<UsersBooks>();
		String sql = "SELECT * FROM users_books WHERE book_id = ?";
	    try (PreparedStatement pstmt = this.connection.prepareStatement(sql);){
	        pstmt.setInt(1, bookId);
	        ResultSet rs = pstmt.executeQuery();
			if (!rs.isBeforeFirst()) {
				throw new BookIdNotFoundException();
			}
	        while(rs.next()) {
	            users_books.add(new UsersBooks(rs.getInt(1), rs.getInt(2), rs.getInt(3)));
	        }	
		} catch (SQLException e) {
			e.printStackTrace();
		}
	    return users_books;
	}

	@Override
	public UsersBooks findByUserIdBookId(int userId, int bookId) throws UserIdBookIdNotFoundException {
		UsersBooks users_books;
		String sql = "SELECT * FROM users_books WHERE user_id = ? and book_id = ?";
	    try (PreparedStatement pstmt = this.connection.prepareStatement(sql);){
            pstmt.setInt(1, userId);
			pstmt.setInt(2, bookId);
            ResultSet rs = pstmt.executeQuery();
            if (!rs.isBeforeFirst()) {
				throw new UserIdBookIdNotFoundException();
			}
            while(rs.next()) {
           		users_books = (new UsersBooks(rs.getInt(1), rs.getInt(2), rs.getInt(3)));
           		return users_books;
           	}	
        } catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	// Private method that gets the num of pages for specific book Id used inside update Pages read.
	private int getNumOfPagesByBookId(int bookId) throws BookIdNotFoundException {
		String sql = String.format("SELECT num_of_pages FROM books where book_id = %s", bookId);
		 try {
	    	Statement stmt = this.connection.createStatement();
	        ResultSet rs = stmt.executeQuery(sql);
	        if (rs.next()) {
				return rs.getInt(1);
			} else {
				throw new BookIdNotFoundException();
			}
	    } catch (SQLException e) {
			e.printStackTrace();
	    }
	    return -1;
	}


	@Override
	public boolean updatePagesRead(int userId, int bookId, int pages) throws UserIdBookIdNotFoundException, BookIdNotFoundException, PageOverNumOfPagesException {
		// Check if pages inputed exceeds numeber of pages for the book.
		if (pages > getNumOfPagesByBookId(bookId)) {
			throw new PageOverNumOfPagesException();
		}
		String sql = String.format("UPDATE users_books SET pages_read = %d WHERE user_Id = ? AND book_Id = ?", pages);
	    try (PreparedStatement pstmt = this.connection.prepareStatement(sql);){
            pstmt.setInt(1, userId);	
            pstmt.setInt(2, bookId);
            int rowCount = pstmt.executeUpdate();
            if (rowCount <= 0) {
                throw new UserIdBookIdNotFoundException();
            } else {
				System.out.println(rowCount + " rows updated.");
			}     
        } catch (SQLException e) {
			e.printStackTrace();
		}
	    return true;
	}

	@Override
	public boolean delete(int userId, int bookId) throws UserIdBookIdNotFoundException {
	    String sql = "DELETE FROM users_books WHERE user_id = ? AND book_id = ?;";
	    try (PreparedStatement pstmt = this.connection.prepareStatement(sql);) {
	        pstmt.setInt(1, userId);
	        pstmt.setInt(2, bookId);
	        int rowCount = pstmt.executeUpdate();
	        if (rowCount <= 0) {
	            throw new UserIdBookIdNotFoundException();
	        } else {
				System.out.println(rowCount + " rows deleted.");
			}
	    } catch (SQLException e) {
			e.printStackTrace();
		} 
	    return true;
	}

	// If users_books combination exists return true
	private boolean exists(int userId, int bookId){
		String sql = "SELECT * FROM users_books WHERE user_id = ? and book_id = ?";
		try (PreparedStatement pstmt = this.connection.prepareStatement(sql);) {
		    pstmt.setInt(1, userId);
		    pstmt.setInt(2, bookId);
		    ResultSet rs = pstmt.executeQuery();
		    if (rs.next()) {
				return true;
		    }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	// Calls when user add books to planning to read section in the book progress track app.
	@Override
	public UsersBooks add(int userId, int bookId) throws DuplicatedUserIdBookIdException {
        if (this.exists(userId, bookId)) {
            throw new DuplicatedUserIdBookIdException();
        }
		String sql = "INSERT INTO users_books (user_id, book_id, pages_read) VALUES (?, ?, 0);";
		try (PreparedStatement pstmt = this.connection.prepareStatement(sql);) {
			pstmt.setInt(1, userId);
	        pstmt.setInt(2, bookId);
			int rowCount = pstmt.executeUpdate();
			System.out.println(rowCount + " rows added.");  
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (new UsersBooks(userId, bookId, 0));
	}

}
