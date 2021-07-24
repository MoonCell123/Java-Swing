package com.fl.swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MovieMain {
    static JLabel l = new JLabel("牛牛影院购票系统");
    static JButton mo = new JButton("订票");
    static JButton BO = new JButton("查看个人订单");
    static JButton re = new JButton("返回个人界面");
    static JFrame j = new JFrame("电影购票窗口");

    public static void GUI() {

        j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container c = j.getContentPane();
        c.setLayout(null);

        //添加控件
        c.add(l);
        c.add(mo);

        c.add(BO);
        c.add(re);

        //设置位置
        l.setBounds(new Rectangle(200, 20, 150, 60));
        mo.setBounds(new Rectangle(50, 100, 150, 60));
        BO.setBounds(new Rectangle(300, 100, 150, 60));
        re.setBounds(new Rectangle(200, 200, 150, 60));
        //点击事件触发

        One one = new One();
        mo.addActionListener(one);

        Three three = new Three();
        BO.addActionListener(three);


        Six six = new Six();
        re.addActionListener(six);

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
        public void actionPerformed(ActionEvent e) {
            l.setText("勇敢牛牛，不怕困难！");
            Movie m = new Movie();
            Movie.GUI();
            j.dispose();
        }

    }

    public static class Three implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {//查看个人订单
            // TODO Auto-generated method stub
            Order o = new Order();
            Order.GUI();
            j.dispose();
        }

    }

    public static class Six implements ActionListener {//用户界面

        @Override
        public void actionPerformed(ActionEvent e) {
            User.GUI();
            j.dispose();
        }

    }
}
