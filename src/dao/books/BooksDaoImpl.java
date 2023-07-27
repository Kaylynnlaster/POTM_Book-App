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
import Exceptions.DuplicatedBookException;

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
		String sql = "SELECT * FROM books WHERE book_id = ?";
	    try (PreparedStatement pstmt = this.connection.prepareStatement(sql);){
	        pstmt.setInt(1, id);
	        ResultSet rs = pstmt.executeQuery();
	        if (!rs.next()) {
	            throw new BookIdNotFoundException();
	        }
			Books book = new Books(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5));
	        return book;
	    } catch (SQLException e) {
			e.printStackTrace();
		}	   
    	return null;
	}

	public List<Books> findByTitle(String title) throws BookTitleNotFoundException {
	    List<Books> books = new ArrayList<Books>();
	    String sql = "SELECT * FROM books WHERE title = ? ";
	    try (PreparedStatement pstmt = this.connection.prepareStatement(sql);) {
	    	pstmt.setString(1, title);
	        ResultSet rs = pstmt.executeQuery();
			if (!rs.isBeforeFirst()) {
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
   

    public boolean update(Books book) throws BookIdNotFoundException {
	    String sql = "Update books SET title=?, author_first_name=?, author_last_name=?, num_of_pages=? WHERE book_id = ?;";
	    try (PreparedStatement pstmt = this.connection.prepareStatement(sql);) {
	        pstmt.setString(1, book.getTitle());
	        pstmt.setString(2, book.getAuthorFirstName());
	        pstmt.setString(3, book.getAuthorLastName());
	        pstmt.setInt(4, book.getNumOfPages());
	        pstmt.setInt(5, book.getBookId());
	        int rowCount = pstmt.executeUpdate();
	        if (rowCount <= 0) {
	            throw new BookIdNotFoundException();
	        } else {
				System.out.println(rowCount + " rows updated.");
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
	        int rowCount = pstmt.executeUpdate();
	        if (rowCount <= 0) {
	            throw new BookIdNotFoundException();
	        } else {
				System.out.println(rowCount + " rows deleted.");
			}
	    } catch (SQLException e) {
			e.printStackTrace();
		} 
	    return true;
    }

	private boolean exists(Books book) {
	    String sql = "SELECT * FROM books WHERE title = ? AND author_first_name = ? AND author_last_name = ? AND num_of_pages = ?"; 
	    try (PreparedStatement pstmt = this.connection.prepareStatement(sql);) {
	        pstmt.setString(1, book.getTitle());
			pstmt.setString(2, book.getAuthorFirstName());
			pstmt.setString(3, book.getAuthorLastName());
			pstmt.setInt(4, book.getNumOfPages());
	        ResultSet rs = pstmt.executeQuery();
	        if (rs.next()) {
	            return true;
	        }
	    } catch (SQLException e) {
			e.printStackTrace();
		} 
	    return false;
	}

	public Books add(Books book) throws DuplicatedBookException {
		if (this.exists(book)) {
           throw new DuplicatedBookException();
		}
     	String sql = "INSERT INTO books (title, author_first_name, author_last_name, num_of_pages) VALUES (?, ?, ?, ?);";
     
     	try (PreparedStatement pstmt = this.connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS );) {
            pstmt.setString(1, book.getTitle());
            pstmt.setString(2, book.getAuthorFirstName());
            pstmt.setString(3, book.getAuthorLastName());
            pstmt.setInt(4, book.getNumOfPages());
            int rowCount = pstmt.executeUpdate();
			System.out.println(rowCount + " rows added.");

        } catch (SQLException e) {
			e.printStackTrace();
		} 
     	return book;
   	}


}
