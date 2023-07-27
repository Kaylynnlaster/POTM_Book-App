package services;

import java.sql.*;

import dao.users.*;

public class UserLogin {

	public static Users user;
	public static UsersDao userDao;
	
	public static Users login(String username, String password) {
		
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
			if (user == null)
			{
				return null;
			}
			if (user.getUserPswd() != password) {
				return null;
			}
			if (user.getUserPswd() == password) {
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
