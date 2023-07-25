// Source code is decompiled from a .class file using FernFlower decompiler.
package dao.users;

import connection.ConnectionManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
		// TODO Auto-generated catch block
		e.printStackTrace();
      }
      return users;
   }

   public Optional<Users> findById(int id) {
      
	  String sql = "SELECT * FROM users WHERE user_id = ?";
      try (PreparedStatement pstmt = this.connection.prepareStatement(sql);){
               pstmt.setInt(1, id);
               ResultSet rs = pstmt.executeQuery();
               if (!rs.next()) {
                  return Optional.empty();
               }

               Users user = new Users(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
               return Optional.of(user);
            } catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	return Optional.empty();
      
   }

   public Optional<Users> findByUsername(String username){
      String sql = "SELECT * FROM users WHERE user_name = ?";
       
      try (PreparedStatement pstmt = this.connection.prepareStatement(sql);) {
               pstmt.setString(4, username);
               ResultSet rs = pstmt.executeQuery();
               if (!rs.next()) {
                  return Optional.empty();
               }    
               Users user = new Users(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
               return Optional.of(user);
      } catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();}
      return Optional.empty();
   }

   public boolean update(Users user) {
      String sql = "Update users SET first_name=?, last_name=?, user_name=?, user_pswd=? WHERE user_id = ?;";
      
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

   public boolean delete(int id){
      String sql = "DELETE FROM users WHERE user_id = ?;";
      
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

   public boolean exists(String username){
      String sql = "SELECT * FROM users WHERE user_name = ?";
      
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


   public Optional<Users> add(Users user){

         if (this.exists(user.getUser_name())) {
            return Optional.empty();
         }

      String sql = "INSERT INTO users (first_name, last_name, user_name, user_pswd) VALUES (?, ?, ?, ?);";
      
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
      return Optional.of(user);
   }
}
