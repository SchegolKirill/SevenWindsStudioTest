package com.testtask.SevenWindsStudio.services;

import com.testtask.SevenWindsStudio.entities.User;

import java.util.List;

public interface UserService {
    User getUser(Integer id);
    User addUser(User user);
    List<User> getAllUsers();
    void resetDB();
}
