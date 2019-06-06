package zzti.dao.impl.constance;


import zzti.dao.UserDAO;
import zzti.entity.PersonalInfoDO;
import zzti.entity.UserDO;

import java.util.*;

public class UserDAOImpl implements UserDAO {
	static Map<String, UserDO> userList = new HashMap<String,UserDO>();

	static{
		for(int i=0;i<5;i++){
			UserDO user = new UserDO("admin"+i,"admin"+i);
			if(i==0){
				user.setPi(new PersonalInfoDO(20, "女", "河南省中原区", "67698021", "test.@163.com", "中原工学院", "硕士", "计算机应用"));
			}else{
				user.setPi(null);
			}
			userList.put("admin"+i,user);
		}
	}

	@Override
	public List<UserDO> getAll() {
		return new ArrayList<UserDO>(userList.values());
	}

	@Override
	public UserDO findUser(String username, String password) {
		Collection<UserDO> c = userList.values();
		for(UserDO u: c){
			if(u.getUsername().equals(username)&&u.getPassword().equals(password)){
				return u;
			}
		}
		return null;
	}

	@Override
	public int insertUser(UserDO u) {
		userList.put(u.getUsername(), u);
		return 1;
	}

	@Override
	public int deleteUser(String id) {
		userList.remove(id);
		return 1;
	}

	@Override
	public UserDO getUserByName(String username) {
		return userList.get(username);
	}
}
