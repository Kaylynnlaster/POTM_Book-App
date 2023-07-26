package Services;

import java.sql.*;

import connection.ConnectionManager;
import dao.users.*;

public class UserLogin {

    public static Users getAuthenticatedUser(String email, String password) {
        Users currUser = null;
        
        final String DB_URL = "jdbc:mysql://localhost:3306/book_progress";
        final String USERNAME = "root";
        final String PASSWORD = "JavaDev23!#%";
        try{
            Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            String sql = "SELECT * FROM users WHERE user_name=? AND user_pswd=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, email);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int id = rs.getInt("user_id");
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                String userName = rs.getString("user_name");
                String pass = rs.getString("user_pswd");
                currUser = new Users(id, firstName, lastName, userName, pass);
                
            }
            ps.close();
            conn.close();
        }
        catch (Exception e) {
            System.out.println("Db con failed");
        }
        return null;
    }
    
}
