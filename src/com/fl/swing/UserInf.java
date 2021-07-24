package com.fl.swing;

import com.fl.dao.MovieDao;
import com.fl.dao.PersonDao;
import com.fl.dao.UserDao;
import com.fl.daoimpl.MovieDaoImp;
import com.fl.daoimpl.PersonDaoImp;
import com.fl.daoimpl.UserDaoImp;
import com.fl.entity.Person;
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

public class UserInf {
	static JTextField t = new JTextField(16);
	static JTextField t1 = new JTextField(16);
	static JFrame f = new JFrame("完善个人信息");
	static JComboBox<String> list = new JComboBox<>();

	public static void GUI(int id, String seat, com.fl.entity.Movie m) {
		View v = new View();
		PersonDao personDao = new PersonDaoImp();
		PersonService personService = new PersonServicel();
		personService.setpersonDao(personDao);
		v.SetPersonService(personService);

		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JLabel l = new JLabel("姓名：");
		JLabel l2 = new JLabel("身份证号：");
		JLabel l3 = new JLabel("性别：");
		JButton b = new JButton("确定");
		JButton r = new JButton("返回");

		Container contentPane = f.getContentPane();
		contentPane.setLayout(null);

		contentPane.add(l);
		contentPane.add(t);
		contentPane.add(l2);
		contentPane.add(t1);
		contentPane.add(l3);
		contentPane.add(list);
		contentPane.add(b);
		contentPane.add(r);

		list.addItem("男");
		list.addItem("女");

		l.setBounds(new Rectangle(50, 50, 100, 30));
		t.setBounds(new Rectangle(110, 50, 150, 30));
		l2.setBounds(new Rectangle(50, 100, 100, 30));
		t1.setBounds(new Rectangle(110, 100, 150, 30));
		l3.setBounds(new Rectangle(50, 150, 100, 30));
		list.setBounds(new Rectangle(110, 150, 100, 30));
		b.setBounds(new Rectangle(80, 230, 100, 30));
		r.setBounds(new Rectangle(200, 230, 100, 30));

		One one = new One(id, seat, m);
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
				GUI(0, null, null);
			}
		});
	}

	private static class One implements ActionListener {//确定
		int id1 = 0;
		String seat1 = "";
		com.fl.entity.Movie m1 = null;
		private MovieService movieservice;
		private PersonService personservice;
		private UserService userservice;

		public One(int id, String seat, com.fl.entity.Movie m) {
			id1 = id;
			seat1 = seat;
			m1 = m;
		}

		@Override
		public void actionPerformed(ActionEvent e) {//包装，返回
			String name = t.getText();//名字
			String sf = t1.getText();//身份证号
			String sex = (String) list.getSelectedItem();//性别
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

			Person p = personService.findBySeat(name);
			if (p != null)
				JOptionPane.showConfirmDialog(null, name + " 您已购票！", "警告", JOptionPane.OK_OPTION, JOptionPane.QUESTION_MESSAGE);
			else {
				v.Buy(name, sf, id1, sex, seat1, m1);
				MovieMain.GUI();
				f.dispose();
			}
		}
	}

	private static class Two implements ActionListener {//返回

		@Override
		public void actionPerformed(ActionEvent e) {
			MovieMain.GUI();
			f.dispose();
		}
	}
}
