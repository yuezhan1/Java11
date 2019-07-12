package service;

import java.sql.SQLException;

import dao.UserDao;
import domain.User;

public class UserService {

	public boolean register(User user) {
		UserDao dao = new UserDao();
		int rows = 0;
		try {
			rows = dao.register(user);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rows > 0 ? true : false;
	}

	public User login(User user) {
		UserDao dao = new UserDao();
		User u=null;
		try {
			u = dao.findUser(user.getUser_name(),user.getUser_password());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return u;
	}

	public boolean checkUsername(String username) {
		UserDao dao = new UserDao();
		Long isExist = 0L;
		try {
			isExist = dao.checkUsername(username);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return isExist>0?true:false;
	}

}
