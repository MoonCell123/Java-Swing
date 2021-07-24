package com.fl.swing;

import javax.swing.*;
import java.awt.*;

//https://www.apiref.com/java11-zh/java.desktop/javax/swing/package-summary.html   API
//https://docs.oracle.com/javase/tutorial/uiswing/index.html
public class SwingDemo {
	static MyFrame frame = new MyFrame("初始界面");

	public static void createGUI() {
		//JFrame指一个窗口，构造方法的参数为窗口标题

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//设置窗口的其他参数，如窗口大小
		frame.setSize(400, 400);
		frame.setBackground(Color.BLACK);
		frame.setLocation(700, 300);
		//显示窗口
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createGUI();
			}
		});

	}
}
