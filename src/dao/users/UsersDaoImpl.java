package dao.users;

import connection.ConnectionManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UsersDaoImpl implements UsersDao {
   private Connection connection = null;

   public UsersDaoImpl() {
   }

   public void establishConnection() throws ClassNotFoundException, SQLException {
      if (this.connection == null) {
         this.connection = ConnectionManager.getConnection();
      }

   }

   public void closeConnection() throws SQLException {
      this.connection.close();
   }

   // Get all method for the users table 
   // Returns list of all the users inside the user table
   public List<Users> getAll() {
      List<Users> users = new ArrayList<Users>();
      // SQL statement to get all users from users table
      String sql = "SELECT * FROM users";
      // Try and catch to catch SQL exception.
      try {
         Statement stmt = this.connection.createStatement();
         ResultSet rs = stmt.executeQuery(sql);

         while(rs.next()) {
            users.add(new Users(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)));
         }
       } catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
      }
      return users;
   }

   // Method to find a users by ID
   public Users findById(int id) {
      // Query to find user by ID
	   String sql = "SELECT * FROM users WHERE user_id = ?";
      // Try and catch to catch sql exception
      try (PreparedStatement pstmt = this.connection.prepareStatement(sql);){
               pstmt.setInt(1, id);
               ResultSet rs = pstmt.executeQuery();
               if (!rs.next()) {
                  return null;
               }

               Users user = new Users(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
               return user;
            } catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	return null;
      
   }

   // Method to find user by user name
   public Users findByUsername(String username){
      // Sql query to find user by user name
      String sql = "SELECT * FROM users WHERE user_name = ?";
       // Try and catch to catch SQL exception
      try (PreparedStatement pstmt = this.connection.prepareStatement(sql);) {
               pstmt.setString(1, username);
               ResultSet rs = pstmt.executeQuery();
               if (!rs.next()) {
                  return null;
               }    
               Users user = new Users(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
               return user;
      } catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();}
      return null;
   }

   // Method to update users return true if updated
   public boolean update(Users user) {
      // Sql query to update users 
      String sql = "Update users SET first_name=?, last_name=?, user_name=?, user_pswd=? WHERE user_id = ?;";
      
      // Try and catch block to catch sql exception
      try (PreparedStatement pstmt = this.connection.prepareStatement(sql);) {
               pstmt.setString(1, user.getFirst_name());
               pstmt.setString(2, user.getLast_name());
               pstmt.setString(3, user.getUser_name());
               pstmt.setString(4, user.getUser_pswd());
               pstmt.setInt(5, user.getUser_id());
               int rows = pstmt.executeUpdate();
               if (rows <= 0) {
                  return false;
               	}         
       } catch (SQLException e) {
		e.printStackTrace();
	}
      return true;
   }

   // Method to delete by id returns true if deleted
   public boolean delete(int id){
      // Sql qeury to delete user by id
      String sql = "DELETE FROM users WHERE user_id = ?;";
      // Try and catch to catch sql exception
      try (PreparedStatement pstmt = this.connection.prepareStatement(sql);) {
               pstmt.setInt(1, id);
               int rows = pstmt.executeUpdate();
               if (rows <= 0) {
                  return false;
               }
      } catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
	 } 
            return true;
   }
   // Method to check if a user exists searched by username doesn't override from the interface
   public boolean exists(String username){
      // Sql qeury to find the user where user name is given by the method parameter
      String sql = "SELECT * FROM users WHERE user_name = ?";
      // Try and catch to catch SQL exception
      try (PreparedStatement pstmt = this.connection.prepareStatement(sql);) {
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();
            if (!rs.next()) {
               return false;
            }
         } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
         return true;
    }

    // Method to add users to the databse
   public Users add(Users user){
      // if the username already exists return null since duplicate username is not possible
         if (this.exists(user.getUser_name())) {
            return null;
         }
         // SQL statment to insert into users table
      String sql = "INSERT INTO users (first_name, last_name, user_name, user_pswd) VALUES (?, ?, ?, ?);";
      
      // Try and catch to catch sql exception
      try (PreparedStatement pstmt = this.connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS );) {
               pstmt.setString(1, user.getFirst_name());
               pstmt.setString(2, user.getLast_name());
               pstmt.setString(3, user.getUser_name());
               pstmt.setString(4, user.getUser_pswd());
               pstmt.executeUpdate();
               ResultSet rs = pstmt.getGeneratedKeys();
               rs.next();
               user.setUser_id(rs.getInt(1));
            } catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
      return user;
   }
}
