package dao.books;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface BooksDao {
   void establishConnection() throws ClassNotFoundException, SQLException;

   void closeConnection() throws SQLException;

   List<Books> getAll();

   Optional<Books> findById(int var1);

   boolean update(Books var1);

   boolean delete(int var1);

   Books add(Books var1);

   List<Books> findByBook(String var1);
}
