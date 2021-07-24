package com.fl.swing;

import com.fl.dao.MovieDao;
import com.fl.dao.PersonDao;
import com.fl.daoimpl.MovieDaoImp;
import com.fl.daoimpl.PersonDaoImp;
import com.fl.entity.Person;
import com.fl.entity.Sex;
import com.fl.service.MovieService;
import com.fl.service.PersonService;
import com.fl.serviceimpl.MovieServiceI;
import com.fl.serviceimpl.PersonServicel;
import com.fl.view.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Order {

    static JTextField t = new JTextField(16);
    static JFrame f = new JFrame("个人订单");

    public static void GUI() {

        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel l = new JLabel("姓名：");
        JButton a = new JButton("退票");
        JButton b = new JButton("更改个人订单");
        JButton r = new JButton("查询个人订单");
        JButton p = new JButton("返回");

        Container contentPane = f.getContentPane();
        contentPane.setLayout(null);

        contentPane.add(l);
        contentPane.add(p);
        contentPane.add(t);
        contentPane.add(b);
        contentPane.add(a);
        contentPane.add(r);

        l.setBounds(new Rectangle(50, 50, 100, 30));
        p.setBounds(new Rectangle(100, 270, 150, 40));
        t.setBounds(new Rectangle(110, 50, 150, 30));
        a.setBounds(new Rectangle(40, 120, 100, 40));
        b.setBounds(new Rectangle(170, 120, 150, 40));
        r.setBounds(new Rectangle(100, 200, 150, 40));

        One one = new One();
        a.addActionListener(one);
        Two two = new Two();
        r.addActionListener(two);
        Three three = new Three();
        b.addActionListener(three);
        Four four = new Four();
        p.addActionListener(four);

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

    private static class One implements ActionListener {//退票

        @Override
        public void actionPerformed(ActionEvent e) {
            View v = new View();

            MovieDao movieDao = new MovieDaoImp();
            MovieService movieService = new MovieServiceI();
            movieService.setMovieDao(movieDao);
            v.SetMovieService(movieService);

            PersonDao personDao = new PersonDaoImp();
            PersonService personService = new PersonServicel();
            personService.setpersonDao(personDao);
            v.SetPersonService(personService);

            String n = t.getText();
            Person p = personService.findBySeat(n);
            if (p == null)
                JOptionPane.showConfirmDialog(null, "姓名错误！", "警告", JOptionPane.OK_OPTION, JOptionPane.QUESTION_MESSAGE);
            else {
                ReturnTicket rt = new ReturnTicket();
                ReturnTicket.GUI(n);
                f.dispose();
            }
        }
    }

    private static class Two implements ActionListener {//查询个人订单

        @Override
        public void actionPerformed(ActionEvent e) {

            View v = new View();

            MovieDao movieDao = new MovieDaoImp();
            MovieService movieService = new MovieServiceI();
            movieService.setMovieDao(movieDao);
            v.SetMovieService(movieService);

            PersonDao personDao = new PersonDaoImp();
            PersonService personService = new PersonServicel();
            personService.setpersonDao(personDao);
            v.SetPersonService(personService);

            String n = t.getText();
            Person p = personService.findBySeat(n);
            if (p == null)
                JOptionPane.showConfirmDialog(null, "姓名错误！", "警告", JOptionPane.OK_OPTION, JOptionPane.QUESTION_MESSAGE);
            else {
                String name = p.getName();    //姓名
                String sf = p.getIdnumber();    //身份证
                int id = p.getId();
                com.fl.entity.Movie m = movieService.findById(id);
                String mn = m.getName();//电影名
                Sex se = p.getSex();
                String sex = "";    //性别
                if (se == Sex.FEMALE)
                    sex = "女";
                else
                    sex = "男";
                String seat = p.getSeat();
                int w = seat.indexOf("x");
                String hang = seat.substring(0, w);//行
                String lie = seat.substring(w + 1, seat.length());//列
                JOptionPane.showConfirmDialog(null, "姓名是：" + name + "\n" + "身份证号是：" + sf + "\n" + "性别是：" + sex + "\n" + "观看的电影名字是：" + mn + "\n" + "座位号是：" + hang + " 行 " + lie + " 列" + "\n", "提示", JOptionPane.OK_OPTION, JOptionPane.QUESTION_MESSAGE);

            }
        }
    }

    private static class Three implements ActionListener {//更改个人订单

        @Override
        public void actionPerformed(ActionEvent e) {
            View v = new View();

            MovieDao movieDao = new MovieDaoImp();
            MovieService movieService = new MovieServiceI();
            movieService.setMovieDao(movieDao);
            v.SetMovieService(movieService);

            PersonDao personDao = new PersonDaoImp();
            PersonService personService = new PersonServicel();
            personService.setpersonDao(personDao);
            v.SetPersonService(personService);

            String n = t.getText();
            Person p = personService.findBySeat(n);
            if (p == null)
                JOptionPane.showConfirmDialog(null, "姓名错误！", "警告", JOptionPane.OK_OPTION, JOptionPane.QUESTION_MESSAGE);
            else {
                ChangeOrder co = new ChangeOrder();
                ChangeOrder.GUI(n);
                f.dispose();
            }
        }

    }

    private static class Four implements ActionListener {//返回

        @Override
        public void actionPerformed(ActionEvent e) {
            MovieMain.GUI();
            f.dispose();

        }

    }
}
