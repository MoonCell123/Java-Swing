package com.fl.daoimpl;

import com.fl.dao.MovieDao;
import com.fl.entity.Movie;
import com.fl.util.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MovieDaoImp implements MovieDao {
	private MovieDao moviedao;

	@Override
	public void insert(Movie e) {
		Connection c = null;
		PreparedStatement s = null;
		ResultSet r = null;
		try {
			c = JdbcUtil.getConnection();
			s = c.prepareStatement("insert into movie values(null,?,?)", Statement.RETURN_GENERATED_KEYS);
			s.setString(1, e.getName());
			s.setInt(2, e.getPrice());
			s.executeUpdate();
			r = s.getGeneratedKeys();
			if (r.next()) {
				e.setId(r.getInt(1));
			}
		} catch (Exception e1) {
			e1.getMessage();
			throw new RuntimeException(e1.getMessage());
		} finally {
			JdbcUtil.release(r, s, null);
		}
	}

	@Override
	public void update(Movie e) {
		Connection c = null;
		PreparedStatement s = null;
		try {
			c = JdbcUtil.getConnection();
			s = c.prepareStatement("update movie set name=?,price=? where id=?");
			s.setString(1, e.getName());
			s.setInt(2, e.getPrice());
			s.setInt(4, e.getId());
			s.executeUpdate();
		} catch (Exception e1) {
			e1.getMessage();
			throw new RuntimeException(e1.getMessage());
		} finally {
			JdbcUtil.release(null, s, null);
		}

	}

	@Override
	public void delete(String name) {
		Connection c = null;
		PreparedStatement s = null;
		try {
			c = JdbcUtil.getConnection();
			s = c.prepareStatement("delete from movie where name=?");
			s.setString(1, name);
			s.executeUpdate();
		} catch (Exception e1) {
			e1.getMessage();
			throw new RuntimeException(e1.getMessage());
		} finally {
			JdbcUtil.release(null, s, null);
		}

	}

	@Override
	public List<Movie> findAll() {
		Connection c = null;
		PreparedStatement s = null;
		ResultSet r = null;
		List<Movie> list = new ArrayList<>();
		try {
			c = JdbcUtil.getConnection();
			s = c.prepareStatement("select id,name,price from movie");
			r = s.executeQuery();
			while (r.next()) {
				list.add(new Movie(r.getInt("id"), r.getString("name"), r.getInt("price")));
			}
		} catch (Exception e1) {
			e1.getMessage();
			throw new RuntimeException(e1.getMessage());
		} finally {
			JdbcUtil.release(r, s, null);
		}
		return list;
	}

	@Override
	public Movie findByName(String name) {
		Connection c = null;
		PreparedStatement s = null;
		ResultSet r = null;
		Movie movie = null;
		try {
			c = JdbcUtil.getConnection();
			s = c.prepareStatement("select id,name,price from movie where name=?");
			s.setString(1, name);
			r = s.executeQuery();
			if (r.next()) {
				movie = new Movie(r.getInt("id"), r.getString("name"), r.getInt("price"));
			}
		} catch (Exception e1) {
			e1.getMessage();
			throw new RuntimeException(e1.getMessage());
		} finally {
			JdbcUtil.release(r, s, null);
		}
		return movie;
	}

	@Override
	public Movie findById(int id) {
		Connection c = null;
		PreparedStatement s = null;
		ResultSet r = null;
		Movie movie = null;
		try {
			c = JdbcUtil.getConnection();
			s = c.prepareStatement("select id,name,price from movie where id=?");
			s.setInt(1, id);
			r = s.executeQuery();
			if (r.next()) {
				movie = new Movie(r.getInt("id"), r.getString("name"), r.getInt("price"));
			}
		} catch (Exception e1) {
			e1.getMessage();
			throw new RuntimeException(e1.getMessage());
		} finally {
			JdbcUtil.release(r, s, null);
		}
		return movie;
	}

}
