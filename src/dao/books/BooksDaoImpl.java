package dao.books;

import connection.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Exceptions.BookIdNotFoundException;
import Exceptions.BookTitleNotFoundException;

public class BooksDaoImpl implements BooksDao {
	private Connection connection = null;

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
			e.printStackTrace();
     	}
    	return books;
	}

   public Books findById(int id) throws BookIdNotFoundException {
		String sql = "SELECT * FROM books WHERE user_id = ?";
	    try (PreparedStatement pstmt = this.connection.prepareStatement(sql);){
	        pstmt.setInt(1, id);
	        ResultSet rs = pstmt.executeQuery();
	        if (!rs.next()) {
	            throw new BookIdNotFoundException(sql);
	        }
			Books book = new Books(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5));
	        return book;
	    } catch (SQLException e) {
			e.printStackTrace();
		}	   
    	return null;
	}
   

   public boolean update(Books book) throws BookIdNotFoundException {
	    String sql = "Update books SET title=?, author_first_name=?, author_last_name=?, num_of_pages=? WHERE book_id = ?;";
	    try (PreparedStatement pstmt = this.connection.prepareStatement(sql);) {
	        pstmt.setString(1, book.getTitle());
	        pstmt.setString(2, book.getAuthor_first_name());
	        pstmt.setString(3, book.getAuthor_last_name());
	        pstmt.setInt(4, book.getNum_of_pages());
	        pstmt.setInt(5, book.getBook_id());
	        int rows = pstmt.executeUpdate();
	        if (rows <= 0) {
	            throw new BookIdNotFoundException(sql);
	        }
	    } catch (SQLException e) {
			e.printStackTrace();
		}
	    return true;
    }

	public boolean delete(int id) throws BookIdNotFoundException {
		String sql = "DELETE FROM books WHERE book_id = ?;";  
	    try (PreparedStatement pstmt = this.connection.prepareStatement(sql);) {
	        pstmt.setInt(1, id);
	        int rows = pstmt.executeUpdate();
	        if (rows <= 0) {
	            throw new BookIdNotFoundException(sql);
	        }
	    } catch (SQLException e) {
			e.printStackTrace();
		} 
	    return true;
    }
	private boolean exists(String title) throws BookTitleNotFoundException{
	    String sql = "SELECT * FROM books WHERE title = ?"; 
	    try (PreparedStatement pstmt = this.connection.prepareStatement(sql);) {
	        pstmt.setString(1, title);
	        ResultSet rs = pstmt.executeQuery();
	        if (!rs.next()) {
	            throw new BookTitleNotFoundException();
	        }
	    } catch (SQLException e) {
			e.printStackTrace();
		} 
	    return true;
	}

	public Books add(Books book) throws BookTitleNotFoundException {
		if (this.exists(book.getTitle())) {
           throw new BookTitleNotFoundException();
		}
     	String sql = "INSERT INTO books (first_name, last_name, user_name, user_pswd) VALUES (?, ?, ?, ?);";
     
     	try (PreparedStatement pstmt = this.connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS );) {
            pstmt.setString(1, book.getTitle());
            pstmt.setString(2, book.getAuthor_first_name());
            pstmt.setString(3, book.getAuthor_last_name());
            pstmt.setInt(4, book.getNum_of_pages());
            int rowCount = pstmt.executeUpdate();
			System.out.println(rowCount + " rows added.");

        } catch (SQLException e) {
			e.printStackTrace();
		} 
     	return book;
   	}

    public List<Books> findByTitle(String title) throws BookTitleNotFoundException {
	    List<Books> books = new ArrayList<Books>();
	    String sql = "SELECT * FROM books WHERE title = ? ";
	    try (PreparedStatement pstmt = this.connection.prepareStatement(sql);) {
	    	pstmt.setString(1, title);
	        ResultSet rs = pstmt.executeQuery();
			if (!rs.next()){
				throw new BookTitleNotFoundException();
			}
	        while(rs.next()) {
	        	books.add(new Books(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5)));
	        }
	    } catch (SQLException e) {
			e.printStackTrace();
	    }
	    return books;
    }
}
