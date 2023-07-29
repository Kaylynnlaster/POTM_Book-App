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

   //establishes a connection for the booksDAO
   public void establishConnection() throws ClassNotFoundException, SQLException {
      if (this.connection == null) {
         this.connection = ConnectionManager.getConnection();
      }

   }

   //closes a connection for the books DAO
   public void closeConnection() throws SQLException {
      this.connection.close();
   }

   //Gets a complete list of all the books in the books table on MySQL
   public List<Books> getAll() {
	  //Creates an empty list
      List<Books> books = new ArrayList<Books>();
	  //Creates an sql string to put in later
      String sql = "SELECT * FROM books";
	  //try and catch to execute the sql command above. Each book found is added to the list.
	  //Throws an sql exception	
      try {
         Statement stmt = connection.createStatement();
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

   //Function to find the book by the given id
   public Books findById(int id) {
		  //SQL string to execute using a prepared statement to fill in the missing value 
		  //with the given data
		  String sql = "SELECT * FROM books WHERE book_id = ?";

		  //Try and catch statement to execute the prepared statement to call the SQL string
	      try (PreparedStatement pstmt = this.connection.prepareStatement(sql);){
	               pstmt.setInt(1, id);
	               ResultSet rs = pstmt.executeQuery();
	               if (!rs.next()) {
	                  return null;
	               }
				   //add the information of the new book
	               Books book = new Books(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5));
	               return book;
	            } catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	   
	   
      return null;
   }
   

   //Boolean to update the books as part of the mvp
   public boolean update(Books book) {
		//Creating an sql string to reset the book information
	      String sql = "Update users SET title=?, author_first_name=?, author_last_name=?, num_of_pages=? WHERE book_id = ?;";
	      //Try and catch to fill in and execute the preparedStatement to return the boolean if the 
		  //Book was updated or not. Throws an SQL Exception
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

   //Method to delete a book from the database
   public boolean delete(int id) {
		  //Creating an SQL string to later use with the Prepared statement
	      String sql = "DELETE FROM users WHERE book_id = ?;";
	      
		  //Try and catch that creates the Prepared statement, fills it in, and returns the bool
		  // of if the deleation was successful or not. Throws an Sql exception
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

   //Method to check if a book is in the database by using the title string
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

	//String to add the book to the database
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

   //Method to find the book by the title. Like above method but returns a book obj instead
   public Books findByTitle(String title) {
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

	      return books.get(0);
   }
}
