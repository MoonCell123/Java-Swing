package com.fl.serviceimpl;

import com.fl.dao.PersonDao;
import com.fl.entity.Person;
import com.fl.service.PersonService;
import com.fl.util.JdbcUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;


public class PersonServicel implements PersonService {
	private PersonDao personDao;

	@Override
	public void insert(Person person) {
		// TODO Auto-generated method stub
		Connection c = null;
		try {
			c = JdbcUtil.getConnection();
			this.personDao.insert(person);
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
	public void delete(String seat) {
		// TODO Auto-generated method stub
		Connection c = null;
		try {
			c = JdbcUtil.getConnection();
			this.personDao.delete(seat);
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
	public void update(Person person) {
		// TODO Auto-generated method stub
		Connection c = null;
		try {
			c = JdbcUtil.getConnection();
			this.personDao.update(person);
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
	public List<Person> findAll() {
		// TODO Auto-generated method stub
		Connection c = null;
		List<Person> list = null;
		try {
			c = JdbcUtil.getConnection();
			list = this.personDao.findAll();
		} catch (Exception e) {
			e.getMessage();
			throw new RuntimeException(e.getMessage());
		} finally {
			JdbcUtil.release(null, null, c);
		}
		return list;
	}

	@Override
	public List<Person> findByMovieId(Integer id) {
		// TODO Auto-generated method stub
		Connection c = null;
		List<Person> list = null;
		try {
			c = JdbcUtil.getConnection();
			list = this.personDao.findByMovieId(id);
		} catch (Exception e) {
			e.getMessage();
			throw new RuntimeException(e.getMessage());
		} finally {
			JdbcUtil.release(null, null, c);
		}
		return list;
	}

	@Override
	public Person findBySeat(String seat) {
		// TODO Auto-generated method stub
		Connection c = null;
		Person person = null;
		try {
			c = JdbcUtil.getConnection();
			person = this.personDao.findBySeat(seat);
		} catch (Exception e) {
			e.getMessage();
			throw new RuntimeException(e.getMessage());
		} finally {
			JdbcUtil.release(null, null, c);
		}
		return person;
	}

	@Override
	public void setpersonDao(PersonDao personDao) {
		// TODO Auto-generated method stub
		this.personDao = personDao;
	}

}
