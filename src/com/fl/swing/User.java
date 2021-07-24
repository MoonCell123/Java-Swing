package com.fl.swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class User {
	//控件
	static JFrame j = new JFrame("用户界面");
	static String x = LogIn.t.getText();
	static JLabel l = new JLabel(x + " 欢迎您！");
	static JButton mo = new JButton("进入电影购票系统");
	static JButton cn = new JButton("更改用户名");
	static JButton cp = new JButton("更改密码");
	static JButton de = new JButton("注销账号");
	static JButton ba = new JButton("返回初始界面");
	static JLabel l1 = new JLabel("请选择您要使用的功能：");
	LogIn li = new LogIn();

	public static void GUI() {

		j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Container c = j.getContentPane();
		c.setLayout(null);

		//控件添加进面板

		c.add(l);
		c.add(mo);
		c.add(cn);
		c.add(cp);
		c.add(de);
		c.add(ba);
		c.add(l1);

		//控件位置

		l.setBounds(new Rectangle(20, 20, 100, 30));
		l1.setBounds(new Rectangle(180, 45, 150, 30));
		mo.setBounds(new Rectangle(50, 100, 150, 60));
		cn.setBounds(new Rectangle(230, 100, 100, 60));
		cp.setBounds(new Rectangle(370, 100, 100, 60));
		de.setBounds(new Rectangle(50, 200, 100, 60));
		ba.setBounds(new Rectangle(230, 200, 150, 60));
		//控件事件触发

		One one = new One();
		mo.addActionListener(one);
		Two two = new Two();
		cn.addActionListener(two);
		Three three = new Three();
		cp.addActionListener(three);
		Four four = new Four();
		de.addActionListener(four);
		Six six = new Six();
		ba.addActionListener(six);

		j.setSize(600, 400);
		j.setLocation(700, 300);
		j.setVisible(true);
	}

	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				GUI();
			}
		});
	}

	public static class One implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {//进入影院界面
			MovieMain.GUI();
			j.dispose();
		}

	}

	public static class Two implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {//更改用户名
			ChangeU.GUI();
			j.dispose();
		}

	}

	public static class Three implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {//改密码
			ChangeP.GUI();
			j.dispose();
		}

	}

	public static class Four implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {//注销账号
			DeleteUser.GUI(x);
			j.dispose();

		}

	}

	public static class Six implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			SwingDemo.createGUI();
			j.dispose();
		}

	}
}
