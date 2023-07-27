package dao.usersBooks;

import java.sql.SQLException;
import java.util.List;

import Exceptions.BookIdNotFoundException;
import Exceptions.DuplicatedUserIdBookIdException;
import Exceptions.PageOverNumOfPagesException;
import Exceptions.UserIdBookIdNotFoundException;
import Exceptions.UserIdNotFoundException;

public interface UsersBooksDao {
	   void establishConnection() throws ClassNotFoundException, SQLException;

	   void closeConnection() throws SQLException;

	   List<UsersBooks> getAll();

	   List<UsersBooks> findBooksByUserId(int userId) throws UserIdNotFoundException;

	   List<UsersBooks> findUsersByBookId(int bookId) throws BookIdNotFoundException;

	   UsersBooks findByUserIdBookId(int userId, int bookId) throws UserIdBookIdNotFoundException;
	   
	   boolean updatePagesRead(int userId, int bookId, int pages) throws UserIdBookIdNotFoundException, BookIdNotFoundException, PageOverNumOfPagesException;

	   boolean delete(int userId, int bookId) throws UserIdBookIdNotFoundException;

	   UsersBooks add(int user_id, int book_id) throws DuplicatedUserIdBookIdException;

}
