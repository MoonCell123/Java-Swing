package com.fl.swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class MyFrame extends JFrame {

	JLabel label = new JLabel("欢迎来到初始界面！");

	public MyFrame(String title) {
		super(title);
		//内容面板
		Container contentPane = getContentPane();
		contentPane.setLayout(null);

		//向内容面板添加控件，如JLabel,JButton
		contentPane.add(label);
		label.setForeground(Color.blue);
		label.setBounds(new Rectangle(130, 50, 150, 100));
		JButton button = new JButton("登录");
		contentPane.add(button);
		button.setBounds(new Rectangle(80, 200, 100, 60));
		JButton B = new JButton("注册");
		contentPane.add(B);
		B.setBounds(new Rectangle(200, 200, 100, 60));

		//创建监听器对象
		MyButtonListener listener = new MyButtonListener();
		ButtonListener l = new ButtonListener();
		//把监听器注册给按钮
		button.addActionListener(listener);//给登录按钮
		B.addActionListener(l);
	}


	private class MyButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			LogIn.GUI();
			SwingDemo.frame.dispose();
		}

	}

	private class ButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			SignIn.GUI();
			SwingDemo.frame.dispose();

		}

	}


}
