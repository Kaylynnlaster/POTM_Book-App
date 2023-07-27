package dao.users;

import java.sql.SQLException;
import java.util.List;

import Exceptions.DuplicatedPasswordException;
import Exceptions.DuplicatedUserNameException;
import Exceptions.UserIdNotFoundException;
import Exceptions.UserNotFoundException;
import Exceptions.UsernameNotFoundException;

public interface UsersDao {
   void establishConnection() throws ClassNotFoundException, SQLException;

   void closeConnection() throws SQLException;

   List<Users> getAll();

   Users findById(int user_id) throws UserIdNotFoundException;

   Users findByUsername(String username) throws UsernameNotFoundException;

   boolean update(Users user) throws UserNotFoundException;

   boolean delete(int user_id) throws UserIdNotFoundException;

   Users add(Users user) throws DuplicatedUserNameException, DuplicatedPasswordException;

}
