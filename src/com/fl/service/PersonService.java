package com.fl.service;

import com.fl.dao.PersonDao;
import com.fl.entity.Person;

import java.util.List;


public interface PersonService {
	void insert(Person person);

	void delete(String seat);

	void update(Person person);

	List<Person> findAll();

	List<Person> findByMovieId(Integer id);//�鿴һ����Ӱ������Щ��

	Person findBySeat(String seat);//ͨ����λ�ҵ�Ψһ��Ӧ���ˣ�����������֣�����������

	void setpersonDao(PersonDao personDao);

}
