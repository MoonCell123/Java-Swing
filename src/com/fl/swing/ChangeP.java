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

public class ChangeP {
	static JTextField t = new JTextField(16);
	static JTextField t1 = new JTextField(16);
	static JTextField t2 = new JTextField(16);
	static JTextField t3 = new JTextField(16);
	static JFrame f = new JFrame("更改密码");

	public static void GUI() {

		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JLabel l = new JLabel("用户名：");
		JLabel l2 = new JLabel("密码：");
		JLabel l3 = new JLabel("新密码:");
		JLabel l4 = new JLabel("再次输入新密码:");
		JButton b = new JButton("确定");
		JButton r = new JButton("返回");

		Container contentPane = f.getContentPane();
		contentPane.setLayout(null);

		contentPane.add(l);
		contentPane.add(t);
		contentPane.add(l2);
		contentPane.add(t1);
		contentPane.add(l3);
		contentPane.add(t2);
		contentPane.add(l4);
		contentPane.add(t3);
		contentPane.add(b);
		contentPane.add(r);

		l.setBounds(new Rectangle(50, 50, 100, 30));
		t.setBounds(new Rectangle(110, 50, 150, 30));
		l2.setBounds(new Rectangle(50, 100, 100, 30));
		t1.setBounds(new Rectangle(110, 100, 150, 30));
		l3.setBounds(new Rectangle(50, 150, 100, 30));
		t2.setBounds(new Rectangle(110, 150, 150, 30));
		l4.setBounds(new Rectangle(10, 200, 100, 30));
		t3.setBounds(new Rectangle(110, 200, 150, 30));
		b.setBounds(new Rectangle(80, 250, 100, 30));
		r.setBounds(new Rectangle(200, 250, 100, 30));

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
			String name = t.getText();
			String password = t1.getText();

			View v = new View();
			UserDao userdao = new UserDaoImp();
			UserService userservice = new UserServiceI();
			userservice.SetUserDao(userdao);
			v.SetUserService(userservice);

			boolean p = v.LogIn(name, password);

			if (p) {//登录成功，对比新密码
				String n = t2.getText();
				String n1 = t2.getText();
				boolean q = true;
				if (!n.equals(n1)) {
					JOptionPane.showConfirmDialog(null, "两次密码不一致！", "警告", JOptionPane.OK_OPTION, JOptionPane.QUESTION_MESSAGE);
				} else {//相同，更改密码
					String np = t2.getText();
					v.ChangeP(name, np);
					User.GUI();
					f.dispose();
				}
			}
		}
	}

	private static class Two implements ActionListener {//返回

		@Override
		public void actionPerformed(ActionEvent e) {
			User.GUI();
			f.dispose();
		}
	}
}
