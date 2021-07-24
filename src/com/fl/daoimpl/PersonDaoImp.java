package com.fl.daoimpl;

import com.fl.dao.PersonDao;
import com.fl.entity.Movie;
import com.fl.entity.Person;
import com.fl.entity.Sex;
import com.fl.util.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class PersonDaoImp implements PersonDao {
	private PersonDao persondao;

	@Override
	public void insert(Person e) {
		Connection c = null;
		PreparedStatement s = null;
		try {
			c = JdbcUtil.getConnection();
			s = c.prepareStatement("insert into person values(?,?,?,?,?)");
			s.setString(1, e.getName());
			s.setString(2, e.getIdnumber());
			s.setInt(3, e.getMovie().getId());
			s.setInt(4, e.getSex().ordinal());
			s.setString(5, e.getSeat());//��ȡ��λ
			s.executeUpdate();
		} catch (Exception e1) {
			e1.getMessage();
			throw new RuntimeException(e1.getMessage());
		} finally {
			JdbcUtil.release(null, s, null);
		}

	}

	@Override
	public void update(Person e) {
		Connection c = null;
		PreparedStatement s = null;
		try {
			c = JdbcUtil.getConnection();
			s = c.prepareStatement(
					"update person set idnumber=?,seat=?,did=? where name=?");
			s.setString(1, e.getIdnumber());
			s.setString(2, e.getSeat());
			s.setInt(3, e.getId());
			s.setString(4, e.getName());
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
			s = c.prepareStatement(
					"delete from person where name=?");
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
	public Person findBySeat(String seat) {
		Connection c = null;
		PreparedStatement s = null;
		ResultSet r = null;
		Person person = null;
		try {
			c = JdbcUtil.getConnection();
			s = c.prepareStatement(
					"select p.name,p.idnumber,p.did,p.sex,p.seat,m.id,m.name as mname,m.price from person p left join movie m on p.did=m.id where p.name=?");
			s.setString(1, seat);
			r = s.executeQuery();
			if (r.next()) {
				Movie movie = new Movie(r.getInt("id"), r.getString("mname"), r.getInt("price"));
				Sex sex = Sex.values()[r.getInt("sex")];
				person = new Person(r.getString("name"), r.getString("idnumber"), r.getInt("did"), sex, r.getString("seat"), movie);
			}
		} catch (Exception e1) {
			e1.getMessage();
			throw new RuntimeException(e1.getMessage());
		} finally {
			JdbcUtil.release(r, s, null);
		}
		return person;
	}

	@Override
	public List<Person> findAll() {
		Connection c = null;
		PreparedStatement s = null;
		ResultSet r = null;
		List<Person> l = new ArrayList();
		try {
			c = JdbcUtil.getConnection();
			s = c.prepareStatement(
					"select p.name,p.idnumber,p.did,p.sex,p.seat,m.id,m.name as mname,m.price from person p left join movie m on p.did=m.id");
			r = s.executeQuery();
			while (r.next()) {
				Movie movie = new Movie(r.getInt("id"), r.getString("mname"), r.getInt("price"));
				Sex sex = Sex.values()[r.getInt("sex")];
				Person person = new Person(r.getString("name"), r.getString("idnumber"), r.getInt("did"), sex, r.getString("seat"), movie);
				l.add(person);
			}
		} catch (Exception e1) {
			e1.getMessage();
			throw new RuntimeException(e1.getMessage());
		} finally {
			JdbcUtil.release(r, s, null);
		}
		return l;
	}

	@Override
	public List<Person> findByMovieId(Integer id) {//��һ����Ӱ�µ���
		Connection c = null;
		PreparedStatement s = null;
		ResultSet r = null;
		List<Person> l = new ArrayList();
		try {
			c = JdbcUtil.getConnection();
			s = c.prepareStatement(
					"select p.name,p.idnumber,p.did,p.sex,p.seat,m.id,m.name as mname,m.price from person p left join movie m on p.did=m.id where m.id=?");
			s.setInt(1, id);
			r = s.executeQuery();
			while (r.next()) {
				Movie movie = new Movie(r.getInt("id"), r.getString("mname"), r.getInt("price"));
				Sex sex = Sex.values()[r.getInt("sex")];
				Person person = new Person(r.getString("name"), r.getString("idnumber"), r.getInt("did"), sex, r.getString("seat"), movie);
				l.add(person);
			}
		} catch (Exception e1) {
			e1.printStackTrace();
			throw new RuntimeException(e1.getMessage());
		} finally {
			JdbcUtil.release(r, s, null);
		}
		return l;
	}

}
