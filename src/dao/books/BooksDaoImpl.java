package dao.books;

import connection.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BooksDaoImpl implements BooksDao {
   private Connection connection = null;

   public BooksDaoImpl() {
   }

   public void establishConnection() throws ClassNotFoundException, SQLException {
      if (this.connection == null) {
         this.connection = ConnectionManager.getConnection();
      }

   }

   public void closeConnection() throws SQLException {
      this.connection.close();
   }

   public List<Books> getAll() {
      List<Books> books = new ArrayList<Books>();
      String sql = "SELECT * FROM books";

      try {
         Statement stmt = this.connection.createStatement();
         ResultSet rs = stmt.executeQuery(sql);

         while(rs.next()) {
            books.add(new Books(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5)));
         }
      } catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
     }

      return books;
   }

   public Books findById(int id) {
		  String sql = "SELECT * FROM books WHERE user_id = ?";
	      try (PreparedStatement pstmt = this.connection.prepareStatement(sql);){
	               pstmt.setInt(1, id);
	               ResultSet rs = pstmt.executeQuery();
	               if (!rs.next()) {
	                  return null;
	               }

	               Books book = new Books(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5));
	               return book;
	            } catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	   
	   
      return null;
   }
   

   public boolean update(Books book) {
	      String sql = "Update users SET title=?, author_first_name=?, author_last_name=?, num_of_pages=? WHERE book_id = ?;";
	      
	      try (PreparedStatement pstmt = this.connection.prepareStatement(sql);) {
	               pstmt.setString(1, book.getTitle());
	               pstmt.setString(2, book.getAuthor_first_name());
	               pstmt.setString(3, book.getAuthor_last_name());
	               pstmt.setInt(4, book.getNum_of_pages());
	               pstmt.setInt(5, book.getBook_id());
	               int rows = pstmt.executeUpdate();
	               if (rows <= 0) {
	                  return false;
	               	}         
	       } catch (SQLException e) {
			e.printStackTrace();
		}
	      return true;
   }

   public boolean delete(int id) {
	      String sql = "DELETE FROM users WHERE book_id = ?;";
	      
	      try (PreparedStatement pstmt = this.connection.prepareStatement(sql);) {
	               pstmt.setInt(1, id);
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
   public boolean exists(String title){
	      String sql = "SELECT * FROM books WHERE title = ?";
	      
	      try (PreparedStatement pstmt = this.connection.prepareStatement(sql);) {
	            pstmt.setString(1, title);
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

   public Books add(Books book) {
	   
       if (this.exists(book.getTitle())) {
           return null;
        }

     String sql = "INSERT INTO users (first_name, last_name, user_name, user_pswd) VALUES (?, ?, ?, ?);";
     
     try (PreparedStatement pstmt = this.connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS );) {
              pstmt.setString(1, book.getTitle());
              pstmt.setString(2, book.getAuthor_first_name());
              pstmt.setString(3, book.getAuthor_last_name());
              pstmt.setInt(4, book.getNum_of_pages());
              pstmt.executeUpdate();
              ResultSet rs = pstmt.getGeneratedKeys();
              rs.next();
              book.setBook_id(rs.getInt(1));
           } catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
     return null;
   }

   public List<Books> findByTitle(String title) {
	      List<Books> books = new ArrayList<Books>();
	      String sql = "SELECT * FROM books WHERE title = ? ";

	      try (PreparedStatement pstmt = this.connection.prepareStatement(sql);) {
	    	 pstmt.setString(1, title);
	         ResultSet rs = pstmt.executeQuery();

	         while(rs.next()) {
	            books.add(new Books(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5)));
	         }
	      } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
	     }

	      return books;
   }
}
