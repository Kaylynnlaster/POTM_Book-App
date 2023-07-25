package dao.users;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface UsersDao {
   void establishConnection() throws ClassNotFoundException, SQLException;

   void closeConnection() throws SQLException;

   List<Users> getAll();

   Optional<Users> findById(int var1) throws Throwable;

   boolean update(Users var1) throws Throwable;

   boolean delete(int var1) throws Throwable;

   Optional<Users> add(Users var1) throws Throwable;

   Optional<Users> findByUsername(String var1) throws Throwable;
}
