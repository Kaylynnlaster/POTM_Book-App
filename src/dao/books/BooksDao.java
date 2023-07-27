package dao.books;

import java.sql.SQLException;
import java.util.List;

import Exceptions.BookIdNotFoundException;
import Exceptions.BookTitleNotFoundException;
import Exceptions.DuplicatedBookException;

public interface BooksDao {
   void establishConnection() throws ClassNotFoundException, SQLException;

   void closeConnection() throws SQLException;

   List<Books> getAll();

   Books findById(int book_id) throws BookIdNotFoundException;

   List<Books> findByTitle(String title) throws BookTitleNotFoundException;

   boolean update(Books book) throws BookIdNotFoundException;

   boolean delete(int book_id) throws BookIdNotFoundException;

   Books add(Books book) throws BookTitleNotFoundException, DuplicatedBookException;

}
