package com.fl.dao;

import com.fl.entity.Person;

import java.util.List;

public interface PersonDao extends BaseDao<Person, String> {
	Person findBySeat(String seat);

	List<Person> findByMovieId(Integer id);
}
