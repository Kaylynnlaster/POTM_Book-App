package dao.users;

import java.sql.SQLException;
import java.util.List;

// Interface for the users DAO that hold the abstract methods to be
// implemented in the UsersDaoImpl
public interface UsersDao {
   void establishConnection() throws ClassNotFoundException, SQLException;

   void closeConnection() throws SQLException;

   List<Users> getAll();

   Users findById(int user_id);

   boolean update(Users user);

   boolean delete(int user_id);

   Users add(Users user);

   Users findByUsername(String username);
}
