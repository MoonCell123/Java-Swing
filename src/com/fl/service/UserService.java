package com.fl.service;

import com.fl.dao.UserDao;
import com.fl.entity.User;

public interface UserService {
    void insert(User user);

    void delete(String i);

    void update(User user);

    void update1(User user, String i);

    User find(String i);

    void SetUserDao(UserDao userdao);
}
