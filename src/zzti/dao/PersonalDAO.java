package zzti.dao;


import zzti.entity.PersonalInfoDO;

import java.sql.SQLException;

/**
 * @author 作者 E-mail: 
 * @version 创建时间：2017年5月14日 下午9:18:20 
 * 类说明 
 */
public interface PersonalDAO extends BaseDAO{
	/**
	 * 通过用户名查询当前用户的个人信息
	 * @param username
	 * @return
	 * @throws SQLException
	 */
	public PersonalInfoDO getPersonalInfo(String username) throws SQLException;
	/**
	 * 保存一个用户的个人信息
	 * @param username
	 * @param p
	 * @throws SQLException
	 */
	public void setPersonalInfo(String username, PersonalInfoDO p) throws SQLException;

}
