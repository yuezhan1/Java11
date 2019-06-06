package zzti.dao.impl.constance;


import zzti.dao.PersonalDAO;
import zzti.entity.PersonalInfoDO;
import zzti.entity.UserDO;

/**
 * @author 作者 E-mail: 
 * @version 创建时间：2017年5月14日 下午9:20:18 
 * 类说明 
 */
public class PersonalDAOImpl implements PersonalDAO {

	
	@Override
	public PersonalInfoDO getPersonalInfo(String username) {
		if(username!=null&&!"".equals(username)){
			return UserDAOImpl.userList.get(username).getPi();
		}
		return null;
	}

	@Override
	public void setPersonalInfo(String username,PersonalInfoDO p) {
		UserDO u = UserDAOImpl.userList.get(username);
		if(u!=null){
			u.setPi(p);
		}
		
	}

}
