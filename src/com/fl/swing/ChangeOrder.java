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

public class ChangeOrder {//座位表如何处理

	static JFrame f = new JFrame("更改个人订单");
	static JComboBox<String> list = new JComboBox<>();
	static JTextField t = new JTextField(16);
	static JTextField t1 = new JTextField(16);

	public static void GUI(String name) {

		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JLabel l = new JLabel("电影：");
		JLabel l1 = new JLabel("座位：");
		JLabel l2 = new JLabel("行：");
		JLabel l3 = new JLabel("列：");
		JButton b = new JButton("确定");
		JButton r = new JButton("返回");

		Container contentPane = f.getContentPane();
		contentPane.setLayout(null);

		contentPane.add(l);
		contentPane.add(l1);
		contentPane.add(l2);
		contentPane.add(l3);
		contentPane.add(t);
		contentPane.add(t1);
		contentPane.add(list);
		contentPane.add(b);
		contentPane.add(r);

		list.addItem("战狼");
		list.addItem("真爱无敌");
		list.addItem("开国大典");
		list.addItem("将爱");

		l.setBounds(new Rectangle(50, 50, 100, 30));
		list.setBounds(new Rectangle(100, 50, 100, 30));
		l1.setBounds(new Rectangle(50, 100, 100, 30));
		l2.setBounds(new Rectangle(50, 130, 100, 30));
		l3.setBounds(new Rectangle(50, 170, 100, 30));
		t.setBounds(new Rectangle(100, 130, 100, 30));
		t1.setBounds(new Rectangle(100, 170, 100, 30));
		b.setBounds(new Rectangle(80, 250, 100, 30));
		r.setBounds(new Rectangle(200, 250, 100, 30));

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

		//确定
		@Override
		public void actionPerformed(ActionEvent e) {//update个人订单
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

			String mn = (String) list.getSelectedItem();
			String a = t.getText();
			String b = t.getText();
			String seat = a + "x" + b;

			v.Update(n, mn, seat);
			com.fl.swing.MovieMain.GUI();
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
