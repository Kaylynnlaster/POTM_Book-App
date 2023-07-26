package dao.users_books;

import java.sql.SQLException;
import java.util.List;

public interface UsersBooksDao {
	   void establishConnection() throws ClassNotFoundException, SQLException;

	   void closeConnection() throws SQLException;

	   List<Users_Books> getAll();

	   List<Users_Books> findBooksByUserId(int userId);
	   List<Users_Books> findUsersByBookId(int bookId);
	   Users_Books findByUserIdBookId(int userId, int bookId);
	   
	   boolean updatePagesRead(int userId, int bookId, int pages);

	   boolean delete(int userId, int bookId);

	   Users_Books add(int user_id, int book_id);

}
