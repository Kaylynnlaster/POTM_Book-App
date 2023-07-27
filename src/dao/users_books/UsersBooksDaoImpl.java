package dao.users_books;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Exceptions.BookIdNotFoundException;
import Exceptions.UserIdBookIdNotFoundException;
import Exceptions.UserIdNotFoundException;
import connection.ConnectionManager;

public class UsersBooksDaoImpl implements  UsersBooksDao {
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
	public List<Users_Books> getAll() {
	    List<Users_Books> users_books = new ArrayList<Users_Books>();
	    String sql = "SELECT * FROM users_books";
	    try {
	        Statement stmt = this.connection.createStatement();
	        ResultSet rs = stmt.executeQuery(sql);
	        while(rs.next()) {
	        	users_books.add(new Users_Books(rs.getInt(1), rs.getInt(2), rs.getInt(3)));
	        }
	    } catch (SQLException e) {
			e.printStackTrace();
	    }
	    return users_books;
	}

	@Override
	public List<Users_Books> findBooksByUserId(int userId) throws UserIdNotFoundException {
		List<Users_Books> users_books = new ArrayList<Users_Books>();
		String sql = "SELECT * FROM users_books WHERE user_id = ?";
	    try (PreparedStatement pstmt = this.connection.prepareStatement(sql);){
	        pstmt.setInt(1, userId);
	        ResultSet rs = pstmt.executeQuery();
			if (!rs.next()){
				throw new UserIdNotFoundException(sql);
			}
	        while(rs.next()) {
	            users_books.add(new Users_Books(rs.getInt(1), rs.getInt(2), rs.getInt(3)));
	        }	
	    } catch (SQLException e) {
			e.printStackTrace();
		}
	    return users_books;
	}

	@Override
	public List<Users_Books> findUsersByBookId(int bookId) throws BookIdNotFoundException {
		List<Users_Books> users_books = new ArrayList<Users_Books>();
		String sql = "SELECT * FROM users_books WHERE book_id = ?";
	    try (PreparedStatement pstmt = this.connection.prepareStatement(sql);){
	        pstmt.setInt(1, bookId);
	        ResultSet rs = pstmt.executeQuery();
			if (!rs.next()){
				throw new BookIdNotFoundException(sql);
			}
	        while(rs.next()) {
	            users_books.add(new Users_Books(rs.getInt(1), rs.getInt(2), rs.getInt(3)));
	        }	
		} catch (SQLException e) {
			e.printStackTrace();
		}
	    return users_books;
	}

	@Override
	public Users_Books findByUserIdBookId(int userId, int bookId) throws UserIdBookIdNotFoundException {
		Users_Books users_books;
		String sql = "SELECT * FROM users_books WHERE user_id = ? and book_id = ?";
	    try (PreparedStatement pstmt = this.connection.prepareStatement(sql);){
            pstmt.setInt(1, bookId);
            ResultSet rs = pstmt.executeQuery();
            if (!rs.next()){
				throw new UserIdBookIdNotFoundException();
			}
            while(rs.next()) {
           		users_books = (new Users_Books(rs.getInt(1), rs.getInt(2), rs.getInt(3)));
           		return users_books;
           	}	
        } catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean updatePagesRead(int userId, int bookId, int pages) throws UserIdBookIdNotFoundException {
		String sql = String.format("Update users_books SET pages_read = pages_read + %d where user_Id = ? AND book_Id = ?", pages);
	    try (PreparedStatement pstmt = this.connection.prepareStatement(sql);){
            pstmt.setInt(1, userId);	
            pstmt.setInt(2, bookId);
            int rows = pstmt.executeUpdate();
            if (rows <= 0) {
                throw new UserIdBookIdNotFoundException();
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
	        int rows = pstmt.executeUpdate();
	        if (rows <= 0) {
	            throw new UserIdBookIdNotFoundException();
	        }
	    } catch (SQLException e) {
			e.printStackTrace();
		} 
	    return true;
	}

	private boolean exists(int userId, int bookId) throws UserIdBookIdNotFoundException{
		String sql = "SELECT * FROM users_books WHERE user_id = ? and book_id = ?";
		try (PreparedStatement pstmt = this.connection.prepareStatement(sql);) {
		    pstmt.setInt(1, userId);
		    pstmt.setInt(2, bookId);
		    ResultSet rs = pstmt.executeQuery();
		    if (rs.next()) {
				return true;
		    } else {
				throw new UserIdBookIdNotFoundException();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Users_Books add(int userId, int bookId) throws UserIdBookIdNotFoundException {
        if (this.exists(userId, bookId)) {
            throw new UserIdBookIdNotFoundException();
        }
		String sql = "INSERT INTO users_books (user_id, book_id, pages_read) VALUES (?, ?, ?);";
		try (PreparedStatement pstmt = this.connection.prepareStatement(sql);) {
			pstmt.setInt(1, userId);
	        pstmt.setInt(2, bookId);
	        pstmt.setInt(2, 0);
	        pstmt.executeUpdate();  
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ( new Users_Books(userId, bookId, 0));
	}

}
