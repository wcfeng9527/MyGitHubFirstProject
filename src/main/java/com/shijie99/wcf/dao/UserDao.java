package com.shijie99.wcf.dao;

import com.shijie99.wcf.model.User;

public interface UserDao {
	/**
	 * 添加新用户
	 * @param user
	 * @return
	 */
	public int insertUser(User user);
}
