package dao.users;

import java.sql.SQLException;
import java.util.List;

public interface UsersDao {
   void establishConnection() throws ClassNotFoundException, SQLException;

   void closeConnection() throws SQLException;

   List<Users> getAll();

   Users findById(int user_id) throws Throwable;

   boolean update(Users user) throws Throwable;

   boolean delete(int user_id) throws Throwable;

   Users add(Users user) throws Throwable;

   Users findByUsername(String username) throws Throwable;
}
