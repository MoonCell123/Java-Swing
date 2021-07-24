package com.fl.swing;

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

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ReturnTicket {

	static JFrame f = new JFrame("退票");

	public static void GUI(String name) {

		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


		JButton b = new JButton("确定");
		JButton r = new JButton("返回");

		Container contentPane = f.getContentPane();
		contentPane.setLayout(null);


		contentPane.add(b);
		contentPane.add(r);


		b.setBounds(new Rectangle(80, 100, 100, 30));
		r.setBounds(new Rectangle(200, 100, 100, 30));

		One one = new One(name);
		b.addActionListener(one);
		Two two = new Two();
		r.addActionListener(two);

		f.setSize(400, 400);
		f.setLocation(700, 300);
		f.setVisible(true);
	}

	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				GUI("");
			}
		});
	}

	private static class One implements ActionListener {
		String n = "";
		private MovieService movieservice;
		private PersonService personservice;
		private UserService userservice;

		public One(String name) {
			n = name;
		}

		//确定(退票)
		@Override
		public void actionPerformed(ActionEvent e) {

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

			v.Delete(n);
			MovieMain.GUI();
			f.dispose();
		}
	}

	private static class Two implements ActionListener {//返回

		@Override
		public void actionPerformed(ActionEvent e) {
			Order.GUI();
			f.dispose();
		}
	}
}
