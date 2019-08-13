package com.myproject.service.impl;

import com.myproject.entity.User;
import com.myproject.entity.enums.UserRole;
import com.myproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

public class UserDetailsServiceImp implements UserDetailsService {

  @Autowired
  private UserService userService;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return userService.getUserByLogin(username).get();
  }

  private User findUserbyUername(String username) {
    Optional<User> optional = userService.getUserByLogin(username);
    return optional.get();
  }
}
