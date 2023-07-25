package dao.users;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface UsersDao {
   void establishConnection() throws ClassNotFoundException, SQLException;

   void closeConnection() throws SQLException;

   List<Users> getAll();

   Optional<Users> findById(int user_id) throws Throwable;

   boolean update(Users user) throws Throwable;

   boolean delete(int user_id) throws Throwable;

   Optional<Users> add(Users user) throws Throwable;

   Optional<Users> findByUsername(String username) throws Throwable;
}
