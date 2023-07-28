package dao.users_books;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import connection.ConnectionManager;
import dao.users.Users;

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
			// TODO Auto-generated catch block
			e.printStackTrace();
	      }
	      return users_books;
	}

	@Override
	public List<Users_Books> findBooksByUserId(int userId) {
		  List<Users_Books> users_books = new ArrayList<Users_Books>();
		  String sql = "SELECT * FROM users_books WHERE user_id = ?";
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

	@Override
	 public List<Users_Books> findUsersByBookId(int bookId) {
		  List<Users_Books> users_books = new ArrayList<Users_Books>();
		  String sql = "SELECT * FROM users_books WHERE book_id = ?";
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

	@Override
	public Users_Books findByUserIdBookId(int userId, int bookId) {
		Users_Books users_books;
		String sql = "SELECT * FROM users_books WHERE user_id = ? and book_id = ?";
	      try (PreparedStatement pstmt = this.connection.prepareStatement(sql);){
              pstmt.setInt(1, bookId);
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

	@Override
	public boolean updatePagesRead(int userId, int bookId, int pages) {
		
		String sql = "Update users_books SET pages_read = pages_read + ? where user_Id = ? AND book_Id = ?";
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

	@Override
	public boolean delete(int userId, int bookId) {
	      String sql = "DELETE FROM users_books WHERE user_id = ? AND book_id = ?;";
	      
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
	   public boolean exists(int userId, int bookId){
		      String sql = "SELECT * FROM users_books WHERE user_id = ? and book_id = ?";
		      
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
	@Override
	public Users_Books add(int userId, int bookId) {
	
        if (this.exists(userId, bookId)) {
            return null;
        }
		 String sql = "INSERT INTO users_books (user_id, book_id, pages_read) VALUES (?, ?, ?);";
		 System.out.println("insert users books");
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
