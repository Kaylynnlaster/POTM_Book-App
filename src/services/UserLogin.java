package services;

import java.sql.*;

import connection.ConnectionManager;
import dao.users.*;

public class UserLogin {

	public static Users user;
	public static UsersDao userDao;
	
	public static Users login(String username, String password) {
		System.out.println("UserLogin method  ");
		System.out.println("LoginForm email:  " + username + "LoginForm password:  " + password);
		
		userDao = new UsersDaoImpl();
		try {
			userDao.establishConnection();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			user = userDao.findByUsername(username);
			
			System.out.println("Password from user " + password + " Password from object " + user.getUser_pswd());
			if (user == null)
			{
				return null;
			}

			if (password.equals(user.getUser_pswd())) {
				System.out.println("true");
				user.setAuthenticate(true);
				return user;
			}
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	public static Users logout(String username)
	{
		return null;
	}
	
		
    
}
