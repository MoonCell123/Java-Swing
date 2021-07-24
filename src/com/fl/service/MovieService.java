package com.fl.service;

import com.fl.dao.MovieDao;
import com.fl.entity.Movie;

import java.util.List;


public interface MovieService {
	void insert(Movie movie);

	void update(Movie movie);

	void delete(String name);

	List<Movie> findAll();

	Movie findByName(String name);

	Movie findById(int id);

	void setMovieDao(MovieDao moviedao);
}
