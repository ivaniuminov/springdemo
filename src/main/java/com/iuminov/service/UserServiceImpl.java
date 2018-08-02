package com.iuminov.service;

import com.iuminov.dao.UserDao;
import com.iuminov.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private MailService mailService;

    @Override
    public User addUser(User user) {
        user.setToken(this.getToken());
        user.setPassword(this.hsshPassword(user.getPassword()));
        User u = userDao.addUser(user);
        mailService.notify(user);
        return u;
    }

    @Override
    public User getByEmail(User user) {
        return userDao.getByEmail(user);
    }

    private String getToken() {
        return UUID.randomUUID().toString();
    }

    private String hsshPassword(String password) {

        return String.valueOf(Objects.hash(password));
    }
}
