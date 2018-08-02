package com.iuminov.service;

import com.iuminov.model.User;

public interface UserService {

    User addUser(User user);

    User getByEmail(User user);
}
