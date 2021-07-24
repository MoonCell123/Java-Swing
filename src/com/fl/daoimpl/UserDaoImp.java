package com.fl.daoimpl;

import com.fl.dao.UserDao;
import com.fl.entity.User;
import com.fl.util.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

public class UserDaoImp implements UserDao {
	private UserDao userdao;

	@Override
	public void insert(User e) {
		Connection c = null;
		PreparedStatement s = null;
		ResultSet r = null;
		try {
			c = JdbcUtil.getConnection();
			s = c.prepareStatement("insert into user values(?,?)", Statement.RETURN_GENERATED_KEYS);
			s.setString(1, e.getUsername());
			s.setString(2, e.getPassword());
			s.executeUpdate();
			r = s.getGeneratedKeys();
		} catch (Exception e1) {
			e1.getMessage();
			throw new RuntimeException(e1.getMessage());
		} finally {
			JdbcUtil.release(r, s, null);
		}

	}

	@Override
	public void update1(User e, String i) {
		Connection c = null;
		PreparedStatement s = null;
		try {
			c = JdbcUtil.getConnection();
			s = c.prepareStatement("update user set username=?,password=? where username=?");
			s.setString(1, i);
			s.setString(2, e.getPassword());
			s.setString(3, e.getUsername());
			s.executeUpdate();
		} catch (Exception e1) {
			e1.getMessage();
			throw new RuntimeException(e1.getMessage());
		} finally {
			JdbcUtil.release(null, s, null);
		}

	}

	@Override
	public void delete(String i) {
		Connection c = null;
		PreparedStatement s = null;
		try {
			c = JdbcUtil.getConnection();
			s = c.prepareStatement("delete from user where username=?");
			s.setString(1, i);
			s.executeUpdate();
		} catch (Exception e1) {
			e1.getMessage();
			throw new RuntimeException(e1.getMessage());
		} finally {
			JdbcUtil.release(null, s, null);
		}

	}

	@Override
	public List<User> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User find(String username) {
		Connection c = null;
		User user = null;
		PreparedStatement s = null;
		ResultSet r = null;
		try {
			c = JdbcUtil.getConnection();
			s = c.prepareStatement("select username,password from user where username=?");
			s.setString(1, username);
			r = s.executeQuery();
			if (r.next()) {
				user = new User(r.getString("username"), r.getString("password"));
			}
		} catch (Exception e) {
			e.getMessage();
			throw new RuntimeException(e.getMessage());
		} finally {
			JdbcUtil.release(null, null, c);
		}
		return user;
	}

	@Override
	public void update(User e) {
		Connection c = null;
		PreparedStatement s = null;
		try {
			c = JdbcUtil.getConnection();
			s = c.prepareStatement("update user set password=? where username=?");
			s.setString(1, e.getPassword());
			s.setString(2, e.getUsername());
			s.executeUpdate();
		} catch (Exception e1) {
			e1.getMessage();
			throw new RuntimeException(e1.getMessage());
		} finally {
			JdbcUtil.release(null, s, null);
		}

	}

}
