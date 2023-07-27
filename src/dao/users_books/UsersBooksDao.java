package dao.users_books;

import java.sql.SQLException;
import java.util.List;

import Exceptions.BookIdNotFoundException;
import Exceptions.UserIdBookIdNotFoundException;
import Exceptions.UserIdNotFoundException;

public interface UsersBooksDao {
	   void establishConnection() throws ClassNotFoundException, SQLException;

	   void closeConnection() throws SQLException;

	   List<Users_Books> getAll();

	   List<Users_Books> findBooksByUserId(int userId) throws UserIdNotFoundException;

	   List<Users_Books> findUsersByBookId(int bookId) throws BookIdNotFoundException;

	   Users_Books findByUserIdBookId(int userId, int bookId) throws UserIdBookIdNotFoundException;
	   
	   boolean updatePagesRead(int userId, int bookId, int pages) throws UserIdBookIdNotFoundException;

	   boolean delete(int userId, int bookId) throws UserIdBookIdNotFoundException;

	   Users_Books add(int user_id, int book_id) throws UserIdBookIdNotFoundException;

}
