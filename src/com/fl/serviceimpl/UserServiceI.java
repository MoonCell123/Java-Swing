package com.fl.serviceimpl;

import com.fl.dao.UserDao;
import com.fl.entity.User;
import com.fl.service.UserService;
import com.fl.util.JdbcUtil;

import java.sql.Connection;
import java.sql.SQLException;

public class UserServiceI implements UserService {
	private UserDao userdao;

	@Override
	public void insert(User user) {
		Connection c = null;
		try {
			c = JdbcUtil.getConnection();
			this.userdao.insert(user);
			c.commit();
		} catch (Exception e) {
			e.getMessage();
			try {
				c.rollback();
			} catch (SQLException e1) {
				e1.getStackTrace();
			}
			throw new RuntimeException(e.getMessage());
		} finally {
			JdbcUtil.release(null, null, c);
		}

	}

	@Override
	public void delete(String i) {
		Connection c = null;
		try {
			c = JdbcUtil.getConnection();
			this.userdao.delete(i);
			c.commit();
		} catch (Exception e) {
			e.getMessage();
			try {
				c.rollback();
			} catch (SQLException e1) {
				e1.getMessage();
			}
			throw new RuntimeException(e.getMessage());
		} finally {
			JdbcUtil.release(null, null, c);
		}

	}

	@Override
	public void update(User user) {
		Connection c = null;
		try {
			c = JdbcUtil.getConnection();
			this.userdao.update(user);
			c.commit();
		} catch (Exception e) {
			e.getMessage();
			try {
				c.rollback();
			} catch (SQLException e1) {
				e1.getMessage();
			}
			throw new RuntimeException(e.getMessage());
		} finally {
			JdbcUtil.release(null, null, c);
		}

	}

	@Override
	public User find(String username) {
		Connection c = null;
		User user = null;
		try {
			c = JdbcUtil.getConnection();
			user = this.userdao.find(username);
		} catch (Exception e) {
			e.getMessage();
			throw new RuntimeException(e.getMessage());
		} finally {
			JdbcUtil.release(null, null, c);
		}
		return user;
	}

	public void SetUserDao(UserDao userdao) {
		this.userdao = userdao;
	}

	@Override
	public void update1(User user, String i) {
		Connection c = null;
		try {
			c = JdbcUtil.getConnection();
			this.userdao.update1(user, i);
			c.commit();
		} catch (Exception e) {
			e.getMessage();
			try {
				c.rollback();
			} catch (SQLException e1) {
				e1.getMessage();
			}
			throw new RuntimeException(e.getMessage());
		} finally {
			JdbcUtil.release(null, null, c);
		}

	}

}
