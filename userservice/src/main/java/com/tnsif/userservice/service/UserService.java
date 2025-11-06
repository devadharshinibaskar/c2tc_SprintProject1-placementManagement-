package com.tnsif.userservice.service;

import java.util.List;
import com.tnsif.userservice.entity.User;

public interface UserService {
    User addUser(User user);
    User updateUser(int id, User user);
    void deleteUser(int id);
    List<User> getAllUsers();
    User getUserById(int id);
    User login(String email, String password);
}
