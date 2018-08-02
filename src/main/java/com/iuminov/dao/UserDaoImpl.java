package com.iuminov.dao;

import com.iuminov.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public class UserDaoImpl implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    /*private JdbcTemplate jdbcTemplate;*/

    /*@Autowired
    public UserDaoImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }*/

    /*@Override
    public User addUser(User user) {
        this.jdbcTemplate.update("INSERT INTO USERS (FIRST_NAME, LAST_NAME, EMAIL, PASSWORD, TOKEN) VALUES (?, ?, ?, ?, ?)",
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getPassword(),
                user.getToken());
        return getByEmail(user);
    }*/

    /*@Override
    public User getByEmail(User user) {
        return this.jdbcTemplate.queryForObject(
                "SELECT U.ID, U.FIRST_NAME, U.LAST_NAME, U.EMAIL, U.PASSWORD, U.TOKEN FROM USERS U WHERE U.EMAIL = ?",
                new Object[]{user.getEmail()},
                ((resultSet, i) -> {
                    User u = new User();
                    u.setId(resultSet.getLong("ID"));
                    u.setEmail(resultSet.getString("EMAIL"));
                    u.setPassword(resultSet.getString("PASSWORD"));
                    u.setFirstName(resultSet.getString("FIRST_NAME"));
                    u.setLastName(resultSet.getString("LAST_NAME"));
                    u.setToken(resultSet.getString("TOKEN"));
                    return u;
                })
        );
    }*/


    @Override
    public User addUser(User user) {
        sessionFactory.getCurrentSession().save(user);
        return getByEmail(user);
    }

/*    @Override
    public User getByEmail(User user) {
        return   sessionFactory.getCurrentSession()
                .createQuery("from User where email =: email", User.class)
                .setParameter("email", user.getEmail())
                .uniqueResult();

    }*/

    @Override
    public User getByEmail(User user) {
        return   sessionFactory.getCurrentSession()
                .createQuery("select u from User u " +
                        "join fetch u.roles r " +
                        "where email =: email", User.class)
                .setParameter("email", user.getEmail())
                .uniqueResult();

    }
}

