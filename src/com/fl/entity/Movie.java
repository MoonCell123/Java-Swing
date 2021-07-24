package com.fl.entity;

public class Movie {
    static private Integer id;
    static private String name;
    static private int price;

    public Movie() {
        super();
        // TODO Auto-generated constructor stub
    }

    public Movie(Integer id, String name, int price) {
        super();
        Movie.id = id;
        Movie.name = name;
        Movie.price = price;
    }

    @Override
    public String toString() {
        return "Movie [id=" + id + ", name=" + name + ", price=" + price + "]";
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        Movie.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        Movie.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        Movie.price = price;
    }


}
