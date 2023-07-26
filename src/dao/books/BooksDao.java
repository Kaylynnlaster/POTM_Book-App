package dao.books;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import dao.users.Users;

public interface BooksDao {
   void establishConnection() throws ClassNotFoundException, SQLException;

   void closeConnection() throws SQLException;

   List<Books> getAll();

   Books findById(int book_id);

   boolean update(Books book);

   boolean delete(int book_id);

   Books add(Books book);

   List<Books> findByTitle(String title);
}
