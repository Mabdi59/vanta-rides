package com.vantarides.dao;

import com.vantarides.model.RegisterUserDto;
import com.vantarides.model.User;

import java.util.List;

public interface UserDao {

    List<User> getUsers();

    User getUserById(int id);

    User getUserByUsername(String username);

    User createUser(RegisterUserDto user);
}
