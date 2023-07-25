package dao.books;

import connection.ConnectionManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
      } catch (SQLException var5) {
         var5.printStackTrace();
      }

      return books;
   }

   public Optional<Books> findById(int id) {
      return Optional.empty();
   }

   public boolean update(Books book) {
      return false;
   }

   public boolean delete(int id) {
      return false;
   }

   public Books add(Books book) {
      return null;
   }

   public List<Books> findByBook(String book) {
      return null;
   }
}
