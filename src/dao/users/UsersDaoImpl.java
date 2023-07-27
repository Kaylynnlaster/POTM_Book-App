package dao.users;

import connection.ConnectionManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Exceptions.DuplicatedPasswordException;
import Exceptions.DuplicatedUserNameException;
import Exceptions.UserIdNotFoundException;
import Exceptions.UserNotFoundException;
import Exceptions.UserPswdNotFoundException;
import Exceptions.UsernameNotFoundException;

public class UsersDaoImpl implements UsersDao {
   private Connection connection = null;

   public void establishConnection() throws ClassNotFoundException, SQLException {
      if (this.connection == null) {
         this.connection = ConnectionManager.getConnection();
      }
   }

   public void closeConnection() throws SQLException {
      this.connection.close();
   }

   public List<Users> getAll() {
      List<Users> users = new ArrayList<Users>();
      String sql = "SELECT * FROM users";
      try {
         Statement stmt = this.connection.createStatement();
         ResultSet rs = stmt.executeQuery(sql);
         while(rs.next()) {
            users.add(new Users(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)));
         }
      } catch (SQLException e) {
		   e.printStackTrace();
      }
      return users;
   }

   public Users findById(int id) throws UserIdNotFoundException {
	   String sql = "SELECT * FROM users WHERE user_id = ?";
      try (PreparedStatement pstmt = this.connection.prepareStatement(sql);){
         pstmt.setInt(1, id);
         ResultSet rs = pstmt.executeQuery();
         if (!rs.next()) {
            throw new UserIdNotFoundException();
         }   
         Users user = new Users(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
         return user;
      } catch (SQLException e) {
			e.printStackTrace();
		}
	   return null;  
   }

   public Users findByUsername(String username) throws UsernameNotFoundException{
      String sql = "SELECT * FROM users WHERE user_name = ?";
      try (PreparedStatement pstmt = this.connection.prepareStatement(sql);) {
         pstmt.setString(4, username);
         ResultSet rs = pstmt.executeQuery();
         if (!rs.next()) {
            throw new UsernameNotFoundException();
         }    
         Users user = new Users(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
         return user;
      } catch (SQLException e) {
		   e.printStackTrace();}
      return null;
   }

   public boolean update(Users user) throws UserNotFoundException {
      String sql = "Update users SET first_name=?, last_name=?, user_name=?, user_pswd=? WHERE user_id = ?;";
      try (PreparedStatement pstmt = this.connection.prepareStatement(sql);) {
         pstmt.setString(1, user.getFirst_name());
         pstmt.setString(2, user.getLast_name());
         pstmt.setString(3, user.getUser_name());
         pstmt.setString(4, user.getUser_pswd());
         pstmt.setInt(5, user.getUser_id());
         int rows = pstmt.executeUpdate();
         if (rows <= 0) {
            throw new UserNotFoundException();
         }         
      } catch (SQLException e) {
		   e.printStackTrace();
   	}
      return true;
   }

   public boolean delete(int id) throws UserIdNotFoundException{
      String sql = "DELETE FROM users WHERE user_id = ?;";
      try (PreparedStatement pstmt = this.connection.prepareStatement(sql);) {
         pstmt.setInt(1, id);
         int rows = pstmt.executeUpdate();
         if (rows <= 0) {
            throw new UserIdNotFoundException(sql);
         }
      } catch (SQLException e) {
			e.printStackTrace();
	   } 
      return true;
   }

   private boolean userNameExists(String username) throws UsernameNotFoundException {
      String sql = "SELECT * FROM users WHERE user_name = ?";
      try (PreparedStatement pstmt = this.connection.prepareStatement(sql);) {
         pstmt.setString(1, username);
         ResultSet rs = pstmt.executeQuery();
         if (rs.next()){
            return true;
         } else {
            throw new UsernameNotFoundException(sql);
         }
      } catch (SQLException e) {
			e.printStackTrace();
		} 
      return false;
    }

   private boolean userPasswordExists(String pswd) throws UserPswdNotFoundException {
      String sql = "SELECT * FROM users WHERE user_pswd = ?";
      try (PreparedStatement pstmt = this.connection.prepareStatement(sql);) {
            pstmt.setString(1, pswd);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()){
               return true;
            } else {
               throw new UserPswdNotFoundException(sql);
            }
      } catch (SQLException e) {
			e.printStackTrace();
		} 
      return false;
   }


   public Users add(Users user) throws UsernameNotFoundException, UserPswdNotFoundException, DuplicatedUserNameException, DuplicatedPasswordException {
      
      if (this.userNameExists(user.getUser_name())) {
         throw new DuplicatedUserNameException();
      }

      if (this.userPasswordExists(user.getUser_pswd())) {
         throw new DuplicatedPasswordException();
      }

      String sql = "INSERT INTO users (first_name, last_name, user_name, user_pswd) VALUES (?, ?, ?, ?);";
      
      try (PreparedStatement pstmt = this.connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS );) {
         pstmt.setString(1, user.getFirst_name());
         pstmt.setString(2, user.getLast_name());
         pstmt.setString(3, user.getUser_name());
         pstmt.setString(4, user.getUser_pswd());
         int rowCount = pstmt.executeUpdate();
         System.out.println(rowCount + " rows added.");        
      } catch (SQLException e) {
				e.printStackTrace();
		} 
      return user;
   }
}
