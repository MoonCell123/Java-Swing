package com.fl.entity;

public class Person {
    private String name;
    private String idnumber;//���֤��
    private int id;//��Ӱ��
    private Sex sex;
    private String seat;//��λ��
    private Movie movie;

    public Person() {
        super();
        // TODO Auto-generated constructor stub
    }

    public Person(String name, String idnumber, int id, Sex sex, String seat, Movie movie) {
        super();
        this.name = name;
        this.idnumber = idnumber;
        this.id = id;
        this.sex = sex;
        this.seat = seat;
        this.movie = movie;
    }

    @Override
    public String toString() {
        return "Person [name=" + name + ", idnumber=" + idnumber + ", id=" + id + ", sex=" + sex + ", seat=" + seat
                + ", movie=" + movie + "]";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdnumber() {
        return idnumber;
    }

    public void setIdnumber(String idnumber) {
        this.idnumber = idnumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public String getSeat() {
        return seat;
    }

    public void setSeat(String seat) {
        this.seat = seat;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }


}
