package com.fl.dao;

import com.fl.entity.Movie;

public interface MovieDao extends BaseDao<Movie, String> {
	Movie findByName(String name);

	Movie findById(int id);
}
