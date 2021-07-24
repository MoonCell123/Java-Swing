package com.fl.serviceimpl;

import com.fl.dao.MovieDao;
import com.fl.entity.Movie;
import com.fl.service.MovieService;
import com.fl.util.JdbcUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;


public class MovieServiceI implements MovieService {

	private MovieDao movieDao;

	@Override
	public void insert(Movie movie) {
		// TODO Auto-generated method stub
		Connection c = null;
		try {
			c = JdbcUtil.getConnection();
			this.movieDao.insert(movie);
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
	public void update(Movie movie) {
		// TODO Auto-generated method stub
		Connection c = null;
		try {
			c = JdbcUtil.getConnection();
			this.movieDao.update(movie);
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
	public void delete(String name) {
		// TODO Auto-generated method stub
		Connection c = null;
		try {
			c = JdbcUtil.getConnection();
			this.movieDao.delete(name);
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
	public List<Movie> findAll() {
		// TODO Auto-generated method stub
		Connection c = null;
		List<Movie> list = null;
		try {
			c = JdbcUtil.getConnection();
			list = this.movieDao.findAll();
		} catch (Exception e) {
			e.getMessage();
			throw new RuntimeException(e.getMessage());
		} finally {
			JdbcUtil.release(null, null, c);
		}
		return list;
	}

	@Override
	public Movie findByName(String name) {
		// TODO Auto-generated method stub
		Connection c = null;
		Movie movie = null;
		try {
			c = JdbcUtil.getConnection();
			movie = this.movieDao.findByName(name);
		} catch (Exception e) {
			e.getMessage();
			throw new RuntimeException(e.getMessage());
		} finally {
			JdbcUtil.release(null, null, c);
		}
		return movie;
	}

	@Override
	public void setMovieDao(MovieDao movieDao) {
		// TODO Auto-generated method stub
		this.movieDao = movieDao;
	}

	@Override
	public Movie findById(int id) {
		Connection c = null;
		Movie movie = null;
		try {
			c = JdbcUtil.getConnection();
			movie = this.movieDao.findById(id);
		} catch (Exception e) {
			e.getMessage();
			throw new RuntimeException(e.getMessage());
		} finally {
			JdbcUtil.release(null, null, c);
		}
		return movie;
	}

}
