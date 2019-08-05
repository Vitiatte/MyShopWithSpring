package com.myproject.dao;

import com.myproject.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserDao {

    Optional<Long> add(User user);

    Optional<User> getUserById(Long id);

    Optional<User> getUserByLoginAndPass(String login, String pass);

    Optional<User> getUserByLogin(String login);

    List<User> getAllUsers();

    void update(User user);

    void delete(User user);

}
