package dao.users_books;

import java.awt.print.Book;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import connection.ConnectionManager;
import dao.books.Books;
import dao.books.BooksDaoImpl;
import dao.users.Users;

// Class that implementes UsersBooksDao
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

	// Method to get all users books data inside the Users books table in the database
	@Override
	public List<Users_Books> getAll() {
	      List<Users_Books> users_books = new ArrayList<Users_Books>();
		  // Sql qeury to select all data from users books table
	      String sql = "SELECT * FROM users_books";
		  // Try and catch to catch sql exceptions
	      try {
	         Statement stmt = this.connection.createStatement();
	         ResultSet rs = stmt.executeQuery(sql);

	         while(rs.next()) {
	            users_books.add(new Users_Books(rs.getInt(1), rs.getInt(2), rs.getInt(3)));
	         }
	       } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
	      }
	      return users_books;
	}
	// Method to find books by user id returns list gets userid as argument
	@Override
	public List<Users_Books> findBooksByUserId(int userId) {
		  List<Users_Books> users_books = new ArrayList<Users_Books>();
		  // Sql query to get all data from users books table where user id is userId
		  String sql = "SELECT * FROM users_books WHERE user_id = ?";
		  // Try and catch to catch sql exception
	      try (PreparedStatement pstmt = this.connection.prepareStatement(sql);){
	               pstmt.setInt(1, userId);
	               ResultSet rs = pstmt.executeQuery();
	               while(rs.next()) {
	            	   users_books.add(new Users_Books(rs.getInt(1), rs.getInt(2), rs.getInt(3)));
	            	   }	
	               } catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	      return users_books;
	      }
	
	// MEthod to find users by book id returns list of User_Books and get bookId as parameter
	@Override
	 public List<Users_Books> findUsersByBookId(int bookId) {
		  List<Users_Books> users_books = new ArrayList<Users_Books>();
		  // Sql query to get all data from users books table where book id is bookId
		  String sql = "SELECT * FROM users_books WHERE book_id = ?";
		  // Try and catch to catch sqlexception
	      try (PreparedStatement pstmt = this.connection.prepareStatement(sql);){
	               pstmt.setInt(1, bookId);
	               ResultSet rs = pstmt.executeQuery();
	               while(rs.next()) {
	            	   users_books.add(new Users_Books(rs.getInt(1), rs.getInt(2), rs.getInt(3)));
	            	   }	
	               } catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	      return users_books;
	}

	// Method to find by unique combination of user id book id which is compositve primary key in the database
	@Override
	public Users_Books findByUserIdBookId(int userId, int bookId) {
		Users_Books users_books;
		// Sql query to select data from users books table where user id is userId and book id is bookID
		String sql = "SELECT * FROM users_books WHERE user_id = ? and book_id = ?";
		// Try and catch block to catch sql exception
	      try (PreparedStatement pstmt = this.connection.prepareStatement(sql);){
	    	  pstmt.setInt(1, userId);
              pstmt.setInt(2, bookId);
              ResultSet rs = pstmt.executeQuery();
              
              while(rs.next()) {
           	   users_books = (new Users_Books(rs.getInt(1), rs.getInt(2), rs.getInt(3)));
           	   return users_books;
           	   }	
              
              } catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
     return null;
	}

	// Method to update pages read column inside the user books table to change progress
	@Override
	public boolean updatePagesRead(int userId, int bookId, int pages) {
		// Sql query to update a column inside a users_books table
		String sql = "Update users_books SET pages_read = pages_read + ? where user_Id = ? AND book_Id = ?";
		// Try and catch to catch sql exception
	     try (PreparedStatement pstmt = this.connection.prepareStatement(sql);){
	    	  pstmt.setInt(1, pages);	
	    	  pstmt.setInt(2, userId);	
              pstmt.setInt(3, bookId);
              int rows = pstmt.executeUpdate();
              if (rows <= 0) {
                  return false;
               	} 
         } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	     return true;
	}
	// Method to delete a column inside a user books table takes in a unique combination of userId and bookId
	@Override
	public boolean delete(int userId, int bookId) {
		// SQl query to delete a specfic column inside user books talbe
	      String sql = "DELETE FROM users_books WHERE user_id = ? AND book_id = ?;";
	      // Try and catch block to catch sql exception.
	      try (PreparedStatement pstmt = this.connection.prepareStatement(sql);) {
	               pstmt.setInt(1, userId);
	               pstmt.setInt(2, bookId);
	               int rows = pstmt.executeUpdate();
	               if (rows <= 0) {
	                  return false;
	               }
	      } catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
		 } 
	            return true;
	}
	// Method that is not overriden that is used only inside this class that checks whether a userbooks column
	// of unique combination of userID and bookId exists
	   public boolean exists(int userId, int bookId){
			// SQL query to check unique combination
		      String sql = "SELECT * FROM users_books WHERE user_id = ? and book_id = ?";
		      // Try and catch block that checks for SQL exception
		      try (PreparedStatement pstmt = this.connection.prepareStatement(sql);) {
		            pstmt.setInt(1, userId);
		            pstmt.setInt(2, bookId);
		            ResultSet rs = pstmt.executeQuery();
		            if (!rs.next()) {
		               return false;
		            }
		         } catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
		         return true;
		    }
	// Method that adds column to users_books table by userID and book id
	@Override
	public Users_Books add(int userId, int bookId) {
		// Checks if a unique combination of userid and bookid exisits return null
        if (this.exists(userId, bookId)) {
            return null;
        }
		// Query that adds a specfiic combination of userid and bookid
		 String sql = "INSERT INTO users_books (user_id, book_id, pages_read) VALUES (?, ?, ?);";
		 System.out.println("insert users books");
		 // Try and catch block that catches sql exception
		 try (PreparedStatement pstmt = this.connection.prepareStatement(sql);) {
			 pstmt.setInt(1, userId);
	         pstmt.setInt(2, bookId);
	         pstmt.setInt(3, 0);
	         pstmt.executeUpdate();
	         
		 } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			return ( new Users_Books(userId, bookId, 0));
	}

}
