package com.iuminov.dao;

import com.iuminov.model.User;

public interface UserDao {

    User addUser(User user);

    User getByEmail(User user);
}
