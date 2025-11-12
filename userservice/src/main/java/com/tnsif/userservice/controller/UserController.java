package com.tnsif.userservice.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.tnsif.userservice.entity.User;
import com.tnsif.userservice.service.UserService;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:3000") // ✅ Allow frontend access
public class UserController {

    @Autowired
    private UserService service;

    // ✅ Add new user
    @PostMapping("/add")
    public User addUser(@RequestBody User user) {
        return service.addUser(user);
    }

    // ✅ Update existing user (keeps same ID)
    @PutMapping("/update/{id}")
    public User updateUser(@PathVariable int id, @RequestBody User user) {
        user.setUserId(id);  // ✅ ensures the same userId is updated, not a new one
        return service.updateUser(id, user);
    }

    // ✅ Delete user by ID
    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable int id) {
        service.deleteUser(id);
        return "User deleted successfully";
    }

    // ✅ Get all users in ascending order by ID
    @GetMapping("/all")
    public List<User> getAllUsers() {
        List<User> users = service.getAllUsers();
        users.sort((u1, u2) -> Integer.compare(u1.getUserId(), u2.getUserId())); // sort ascending
        return users;
    }

    // ✅ Get a single user by ID
    @GetMapping("/{id}")
    public User getUserById(@PathVariable int id) {
        return service.getUserById(id);
    }

    // ✅ Login endpoint
    @PostMapping("/login")
    public String login(@RequestBody User user) {
        User valid = service.login(user.getEmail(), user.getPassword());
        return (valid != null) ? "Login Successful" : "Invalid Credentials";
    }
}
