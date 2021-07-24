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

public class DeleteUser {
	static JFrame j = new JFrame("注销");
	static JButton i = new JButton("确定");
	static JButton b = new JButton("返回");

	public static void GUI(String name) {

		j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Container c = j.getContentPane();
		c.setLayout(null);

		//添加控件
		c.add(i);
		c.add(b);

		//位置
		i.setBounds(new Rectangle(50, 100, 100, 60));
		b.setBounds(new Rectangle(200, 100, 100, 60));

		//点击事件
		One one = new One(name);
		i.addActionListener(one);
		Two two = new Two();
		b.addActionListener(two);

		j.setSize(400, 400);
		j.setLocation(700, 300);
		j.setVisible(true);
		JOptionPane.showConfirmDialog(null, "确定注销账号吗？", "警告", JOptionPane.OK_OPTION, JOptionPane.QUESTION_MESSAGE);
	}

	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				String i = "";
				GUI(i);
			}
		});
	}

	public static class One implements ActionListener {
		public String n = "";

		public One(String name) {
			n = name;
		}

		@Override
		public void actionPerformed(ActionEvent e) {//删除账号
			// TODO Auto-generated method stub
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

			v.LogOut(n);
			User.GUI();
			j.dispose();
		}

	}

	public static class Two implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			User.GUI();
			j.dispose();
		}

	}
}
