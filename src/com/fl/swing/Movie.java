package com.fl.swing;

import com.fl.dao.MovieDao;
import com.fl.daoimpl.MovieDaoImp;
import com.fl.service.MovieService;
import com.fl.serviceimpl.MovieServiceI;
import com.fl.view.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Movie {
	static JFrame f = new JFrame("电影信息");
	static JComboBox<String> list = new JComboBox<>();
	static JLabel text = new JLabel("票价是：40元");
	static JCheckBox box = new JCheckBox("购买");
	static JTextField jf = new JTextField(16);
	static JLabel m = new JLabel("");
	static JLabel m1 = new JLabel("电影名字：");
	static JLabel m2 = new JLabel("座位：");
	static JLabel m3 = new JLabel("行：");
	static JLabel m4 = new JLabel("列：");
	static JTextField l3 = new JTextField(10);
	static JTextField l4 = new JTextField(10);

	public static void GUI() {
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JButton R = new JButton("确定");
		JButton r = new JButton("返回");

		Container contentPane = f.getContentPane();
		contentPane.setLayout(null);

		contentPane.add(m1);
		contentPane.add(m2);
		contentPane.add(m3);
		contentPane.add(m4);
		contentPane.add(l3);
		contentPane.add(l4);
		contentPane.add(list);
		contentPane.add(text);
		contentPane.add(box);
		contentPane.add(m);
		contentPane.add(jf);
		contentPane.add(R);
		contentPane.add(r);

		list.addItem("战狼");
		list.addItem("真爱无敌");
		list.addItem("开国大典");
		list.addItem("将爱");


		m1.setBounds(new Rectangle(50, 50, 100, 30));
		list.setBounds(new Rectangle(130, 50, 100, 30));
		text.setBounds(new Rectangle(250, 50, 150, 30));
		box.setBounds(new Rectangle(50, 100, 100, 30));
		m.setBounds(new Rectangle(50, 150, 150, 30));
		jf.setBounds(new Rectangle(180, 150, 150, 30));
		m2.setBounds(new Rectangle(50, 200, 100, 20));
		m3.setBounds(new Rectangle(50, 220, 100, 30));
		l3.setBounds(new Rectangle(130, 220, 100, 30));
		m4.setBounds(new Rectangle(50, 250, 100, 30));
		l4.setBounds(new Rectangle(130, 250, 100, 30));
		R.setBounds(new Rectangle(80, 320, 100, 30));
		r.setBounds(new Rectangle(230, 320, 100, 30));

		list.addActionListener(new ActionListener() {//下拉点击事件(更新票价)

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String item = (String) list.getSelectedItem();
				if (item.equals("战狼"))
					text.setText("票价是： 40元");
				if (item.equals("真爱无敌"))
					text.setText("票价是：20元");
				if (item.equals("开国大典"))
					text.setText("票价是：35元");
				if (item.equals("将爱"))
					text.setText("票价是：50元");
			}

		});

		box.setSelected(false);//默认选中

		box.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (box.isSelected()) {
					m.setText("请输入您支付的金额：");
					jf.setEnabled(true);
				} else
					jf.setEnabled(false);

			}

		});

		One one = new One();
		R.addActionListener(one);

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

	private static class One implements ActionListener {//确定按钮事件

		@Override
		public void actionPerformed(ActionEvent e) {

			String item = (String) list.getSelectedItem();
			String price = jf.getText();
			String p = text.getText().substring(4, 6);
			if (p.equals(price))//加入个人订单
			{
				View v = new View();
				MovieDao movieDao = new MovieDaoImp();
				MovieService movieService = new MovieServiceI();
				movieService.setMovieDao(movieDao);
				v.SetMovieService(movieService);

				com.fl.entity.Movie m = movieService.findByName(item);
				int id = m.getId();//电影ID
				String a = l3.getText();
				String b = l3.getText();
				String seat = a + "x" + b;//座位
				UserInf i = new UserInf();
				UserInf.GUI(id, seat, movieService.findByName(item));
				f.dispose();
			} else
				JOptionPane.showConfirmDialog(null, "金额不符！", "警告", JOptionPane.OK_OPTION, JOptionPane.QUESTION_MESSAGE);
		}

	}

	private static class Two implements ActionListener {//返回

		@Override
		public void actionPerformed(ActionEvent e) {//返回影院主界面
			MovieMain.GUI();
			f.dispose();
		}
	}
}
