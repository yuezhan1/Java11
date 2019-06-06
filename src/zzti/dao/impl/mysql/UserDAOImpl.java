package zzti.dao.impl.mysql;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import zzti.dao.UserDAO;
import zzti.entity.UserDO;
import zzti.util.tomcat.DBUtil;

import java.sql.SQLException;
import java.util.List;



public class UserDAOImpl implements UserDAO {
	public List<UserDO> getAll() throws SQLException {
		QueryRunner queryrunner = new QueryRunner(DBUtil.getDataSource());


		String sql="select * from user ";
		List<UserDO> list = queryrunner.query(sql, new BeanListHandler<UserDO>(UserDO.class));
		return list;
	}

	public UserDO findUser(String username, String password) throws SQLException {
		QueryRunner queryrunner = new QueryRunner(DBUtil.getDataSource());
		String sql="select * from user where username = ? and password=?";
		Object[] params = new Object[]{username,password};
		UserDO userDO = queryrunner.query(sql, new BeanHandler<UserDO>(UserDO.class), params);
		return userDO;
	}

	public int insertUser(UserDO u) throws SQLException {
		QueryRunner queryrunner = new QueryRunner(DBUtil.getDataSource());
		String sql="insert into user values(?,?)";
		Object[] params = new Object[]{u.getUsername(),u.getPassword()};
		return queryrunner.update(sql, params);
	}

	public int deleteUser(String id) throws SQLException {
		QueryRunner queryrunner = new QueryRunner(DBUtil.getDataSource());
		String sql="delete from user where username=?";
		return queryrunner.update(sql, id);
	}

	@Override
	public UserDO getUserByName(String username) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
}
