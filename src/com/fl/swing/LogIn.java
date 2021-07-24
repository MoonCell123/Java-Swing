package com.fl.swing;

import com.fl.dao.UserDao;
import com.fl.daoimpl.UserDaoImp;
import com.fl.service.UserService;
import com.fl.serviceimpl.UserServiceI;
import com.fl.view.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class LogIn {
	static JTextField t = new JTextField(16);
	static JTextField t1 = new JTextField(16);
	static JFrame f = new JFrame("登录");

	public static void GUI() {

		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JLabel l = new JLabel("用户名：");

		JLabel l2 = new JLabel("密码：");

		JButton b = new JButton("确定");
		JButton r = new JButton("返回");

		Container contentPane = f.getContentPane();
		contentPane.setLayout(null);

		contentPane.add(l);
		contentPane.add(t);
		contentPane.add(l2);
		contentPane.add(t1);
		contentPane.add(b);
		contentPane.add(r);

		l.setBounds(new Rectangle(50, 50, 100, 30));
		t.setBounds(new Rectangle(110, 50, 150, 30));
		l2.setBounds(new Rectangle(50, 100, 50, 30));
		t1.setBounds(new Rectangle(110, 100, 150, 30));
		b.setBounds(new Rectangle(80, 200, 100, 30));
		r.setBounds(new Rectangle(200, 200, 100, 30));

		One one = new One();
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
				GUI();
			}
		});
	}

	private static class One implements ActionListener {//确定

		@Override
		public void actionPerformed(ActionEvent e) {
			String name = t.getText();//用户名
			String password = t1.getText();//密码

			View v = new View();
			UserDao userdao = new UserDaoImp();
			UserService userservice = new UserServiceI();
			userservice.SetUserDao(userdao);
			v.SetUserService(userservice);

			boolean p = v.LogIn(name, password);
			if (p) {//密码正确（跳出弹窗，登录成功，进入下一步）
				JOptionPane.showConfirmDialog(null, "登录成功！", "提示", JOptionPane.OK_OPTION, JOptionPane.QUESTION_MESSAGE);
				User.GUI();
				f.dispose();
			}
		}
	}

	private static class Two implements ActionListener {//返回

		@Override
		public void actionPerformed(ActionEvent e) {
			SwingDemo.createGUI();
			f.dispose();
		}
	}

}
