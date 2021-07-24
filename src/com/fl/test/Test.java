package com.fl.test;

import com.fl.dao.MovieDao;
import com.fl.dao.PersonDao;
import com.fl.dao.UserDao;
import com.fl.daoimpl.MovieDaoImp;
import com.fl.daoimpl.PersonDaoImp;
import com.fl.daoimpl.UserDaoImp;
import com.fl.service.MovieService;
import com.fl.service.PersonService;
import com.fl.service.UserService;
import com.fl.serviceimpl.MovieServiceI;
import com.fl.serviceimpl.PersonServicel;
import com.fl.serviceimpl.UserServiceI;
import com.fl.view.View;

import java.sql.SQLException;

public class Test {
	public static void main(String[] args) throws SQLException {
		View v = new View();

		MovieDao movieDao = new MovieDaoImp();
		MovieService movieService = new MovieServiceI();
		movieService.setMovieDao(movieDao);
		v.SetMovieService(movieService);

		PersonDao personDao = new PersonDaoImp();
		PersonService personService = new PersonServicel();
		personService.setpersonDao(personDao);
		v.SetPersonService(personService);

		UserDao userdao = new UserDaoImp();
		UserService userservice = new UserServiceI();
		userservice.SetUserDao(userdao);
		v.SetUserService(userservice);


		System.out.println(userdao);


	}
}
