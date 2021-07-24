package com.fl.view;

import com.fl.entity.Movie;
import com.fl.entity.Person;
import com.fl.entity.Sex;
import com.fl.entity.User;
import com.fl.service.MovieService;
import com.fl.service.PersonService;
import com.fl.service.UserService;
import com.fl.util.Keyboard;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.Iterator;


public class View {//电影购票系统
	private MovieService movieservice;
	private PersonService personservice;
	private UserService userservice;


	public void ChangeName(String name) {//改用户名
		User user = null;    //先登录再改密码,传用户进去，用户名、密码
		try {
			this.userservice.update1(user, name);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}


	public void ChangeP(String name, String password) {//改密码
		password.replaceAll(" ", "");             //密码去除空格
		User u = new User(name, password);    //传用户进去，用户名、密码
		try {
			this.userservice.update(u);
			JOptionPane.showConfirmDialog(null, "密码更改成功！", "提示", JOptionPane.OK_OPTION, JOptionPane.QUESTION_MESSAGE);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void LogOut(String name) {//注销
		this.userservice.delete(name);
		JOptionPane.showConfirmDialog(null, "更改成功！", "提示", JOptionPane.OK_OPTION, JOptionPane.QUESTION_MESSAGE);
	}

	public boolean SignIn(String username, String password) {//注册
		boolean p = true;
		User user = null;
		User u = this.userservice.find(username);
		if (u != null) {
			JOptionPane.showConfirmDialog(null, "用户名已存在！", "警告", JOptionPane.OK_OPTION, JOptionPane.QUESTION_MESSAGE);
			p = false;
		} else {
			password.replaceAll(" ", "");             //密码去除空格
			user = new User(username, password);
			try {
				this.userservice.insert(user);

			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return p;
	}

	public boolean LogIn(String username, String password) {//登录
		boolean p = true;
		User user = null;
		User u = this.userservice.find(username);
		if (u == null) {
			JOptionPane.showConfirmDialog(null, "用户不存在！", "警告", JOptionPane.OK_OPTION, JOptionPane.QUESTION_MESSAGE);
			p = false;
		} else {
			String pa = this.userservice.find(username).getPassword();
			if (!password.equals(pa)) {
				JOptionPane.showConfirmDialog(null, "密码错误！", "警告", JOptionPane.OK_OPTION, JOptionPane.QUESTION_MESSAGE);
				p = false;
			} else {
				user = new User(username, password);
			}
		}
		return p;
	}


	private String checkPassword(String passwordStr) {//密码强度判断函数
		String regexZ = "\\d*";
		String regexS = "[a-zA-Z]+";
		String regexT = "\\W+$";
		String regexZT = "\\D*";
		String regexST = "[\\d\\W]*";
		String regexZS = "\\w*";
		String regexZST = "[\\w\\W]*";

		if (passwordStr.matches(regexZ)) {
			return "弱";
		}
		if (passwordStr.matches(regexS)) {
			return "弱";
		}
		if (passwordStr.matches(regexT)) {
			return "弱";
		}
		if (passwordStr.matches(regexZT)) {
			return "中";
		}
		if (passwordStr.matches(regexST)) {
			return "中";
		}
		if (passwordStr.matches(regexZS)) {
			return "中";
		}
		if (passwordStr.matches(regexZST)) {
			return "强";
		}
		return passwordStr;

	}


	public int getMovie(String name) {//获得电影得票价
		Movie movie = this.movieservice.findByName(name);
		return movie.getPrice();
	}

	public java.util.List<Movie> GetMovie() {//展示所有电影
		java.util.List<Movie> l = this.movieservice.findAll();
		return l;
	}

	private String BookSeat(String Mn, int MovieId) {//购票选座函数
		int id = MovieId;
		String MovieName = Mn;
		java.util.List<Person> list = this.personservice.findByMovieId(id);//获取看对应电影的人
		int maxn = 10;
		String[][] S = new String[maxn][maxn];
		for (int i = 1; i < maxn; i++) {//初始化座位表
			for (int j = 1; j < maxn; j++) {
				S[i][j] = "*";
			}
		}
		for (int i = 1; i < maxn; i++) {//放置行列数
			S[i][0] = i + "";
			S[0][i] = i + "";
		}
		//找到电影下的人的座位，将电影座位替换
		Iterator<Person> it = list.iterator();
		while (it.hasNext()) {
			Person p = it.next();
			String seat = p.getSeat();
			String x1 = "";
			String y1 = "";
			int i = seat.indexOf("x");
			x1 = seat.substring(0, i);//行
			y1 = seat.substring(i + 1, seat.length());//列
			S[Integer.valueOf(x1)][Integer.valueOf(y1)] = "#";//替换
		}
		//打印座位表
		System.out.println("《" + MovieName + "》" + "的座位表是：");
		S[0][0] = " ";
		for (int i = 0; i < maxn; i++) {
			for (int j = 0; j < maxn; j++) {
				System.out.print(S[i][j] + " ");
			}
			System.out.println();
		}
		int sum = (maxn - 1) * (maxn - 1);
		int sp = list.size();
		System.out.println("‘*’座位暂时无人订购，‘#’座位已经有人了");
		System.out.println("座位总数是：" + sum + " 已选座位：" + sp + " 剩余座位：" + (sum - sp));
		//输入选择的座位号
		boolean p = true;
		int h = 0, l = 0;
		while (p) {
			h = Keyboard.getInt("请输入您选择的行号：");
			l = Keyboard.getInt("请输入您选择的列号：");
			if (S[h][l].equals("#")) {
				System.out.println("此座位已经有人了，请重新选择！");
			} else {
				System.out.println("您确定选择" + h + "行" + l + "列的座位吗？输入是or否");
				String pan = Keyboard.getString("输入是否");
				switch (pan) {
					case "否":
						break;
					case "是":
						System.out.println("OK");
						p = false;
						break;
					default:
						System.out.println("输入错误，请重新输入！");
						break;
				}
			}
		}
		String Newseat = h + "x" + l;
		return Newseat;
	}

	public void Buy(String name, String s, int id, String sex, String seat, com.fl.entity.Movie m) {//购票（判断身份证号） 输入购票金额对照电影
		Sex se = Sex.FEMALE;
		if (sex.equals("男"))
			se = Sex.MALE;
		Person pe = new Person(name, s, id, se, seat, m);
		try {
			this.personservice.insert(pe);
			JOptionPane.showConfirmDialog(null, "购买成功！", "提示", JOptionPane.OK_OPTION, JOptionPane.QUESTION_MESSAGE);
		} catch (Exception e) {
			//System.out.println(e.getStackTrace());
			throw new RuntimeException(e.getMessage());
		}

	}


	public void Delete(String name) {//退票
		Person p = personservice.findBySeat(name);
		try {
			this.personservice.delete(name);
			JOptionPane.showConfirmDialog(null, "退票成功", "提示", JOptionPane.OK_OPTION, JOptionPane.QUESTION_MESSAGE);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}


	private void Show() {//查看本电影有几个人看
		while (true) {
			System.out.println("输入您想查看的电影：");
			String mn = Keyboard.getString("");
			Movie movie = this.movieservice.findByName(mn);
			if (movie == null)
				System.out.println("电影不存在，请重新输入！");
			else {
				int id = movie.getId();
				java.util.List<Person> l = this.personservice.findByMovieId(id);
				int sum = l.size();
				System.out.println("您所查看的" + mn + "电影，总共有" + sum + "人观看");
				break;
			}
		}
	}

	private void Show1() {//所有电影总共有几个人看(本影院的上座率是)
		java.util.List<Person> l = this.personservice.findAll();
		int sum = l.size();
		System.out.println("本影院的观影人数是：" + sum);
	}

	private void buy() {//查询个人订单，买票了显示个人信息，没买显示没买
		while (true) {
			String name = Keyboard.getString("输入您的名字：");
			Person p = personservice.findBySeat(name);
			if (p == null) {
				System.out.println("您没有订购任何电影");
			} else {
				System.out.println("您的姓名是：" + p.getName() + " 您的身份证号是：" + p.getIdnumber() + " 您所观看的电影是：" + p.getMovie().getName() + " 您所选择的座位是： " + p.getSeat());
				break;
			}
		}
	}

	public void Update(String name, String mn, String seat) {//更改座位、电影、更改身份证号 （判断身份证号有效）
		Person p = personservice.findBySeat(name);
		Sex s = p.getSex();
		Movie movie = this.movieservice.findByName(mn);
		int id = movie.getId();
		String n2 = p.getIdnumber();
		Person p1 = new Person(name, n2, id, s, seat, movieservice.findByName(mn));
		try {
			this.personservice.update(p1);
			JOptionPane.showConfirmDialog(null, "更改成功！", "提示", JOptionPane.OK_OPTION, JOptionPane.QUESTION_MESSAGE);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}


	public void SetMovieService(MovieService movieservice) {
		this.movieservice = movieservice;
	}

	public void SetPersonService(PersonService personservice) {
		this.personservice = personservice;
	}

	public void SetUserService(UserService userservice) {
		this.userservice = userservice;
	}

}
