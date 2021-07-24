package com.fl.dao;

import com.fl.entity.User;

public interface UserDao extends BaseDao<User, String> {
    User find(String username);

    void update1(User user, String i);
}
