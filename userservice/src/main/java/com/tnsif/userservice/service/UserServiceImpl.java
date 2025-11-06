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
        User existing = repo.findById(id).orElseThrow();
        existing.setUsername(user.getUsername());
        existing.setEmail(user.getEmail());
        existing.setPassword(user.getPassword());
        existing.setRole(user.getRole());
        existing.setDepartment(user.getDepartment());
        existing.setStatus(user.getStatus());
        return repo.save(existing);
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
