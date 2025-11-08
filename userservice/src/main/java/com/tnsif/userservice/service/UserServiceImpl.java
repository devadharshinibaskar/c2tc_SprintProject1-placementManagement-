package com.tnsif.userservice.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tnsif.userservice.entity.User;
import com.tnsif.userservice.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repo;

    @Override
    public User addUser(User user) {
        return repo.save(user);
    }

    @Override
    public User updateUser(int id, User user) {
        return repo.findById(id).map(existingUser -> {
            existingUser.setUsername(user.getUsername());
            if (!existingUser.getEmail().equals(user.getEmail())) {
                existingUser.setEmail(user.getEmail());
            }
            existingUser.setPassword(user.getPassword());
            existingUser.setRole(user.getRole());
            existingUser.setDepartment(user.getDepartment());
            existingUser.setStatus(user.getStatus());
            return repo.save(existingUser);
        }).orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    }

    @Override
    public void deleteUser(int id) {
        repo.deleteById(id);
    }

    @Override
    public List<User> getAllUsers() {
        return repo.findAll();
    }

    @Override
    public User getUserById(int id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public User login(String email, String password) {
        User u = repo.findByEmail(email);
        if (u != null && u.getPassword().equals(password)) {
            return u;
        }
        return null;
    }
}
