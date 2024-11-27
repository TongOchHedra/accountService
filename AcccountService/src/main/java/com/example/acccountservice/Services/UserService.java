package com.example.acccountservice.Services;

import com.example.acccountservice.DAO.UserRepository;
import com.example.acccountservice.Model.Role;
import com.example.acccountservice.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userDAO;
    public User GetUserById(long id) {
        return userDAO.findById(id).orElse(null);
    }
    public Boolean SaveUser(User user) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userDAO.save(user);
        return true;
    }
    public boolean DeleteUser(long id) {
        userDAO.deleteById(id);
        return true;
    }
    public ArrayList<User> GetAllUsers() {
        ArrayList<User> users = new ArrayList<>();
        userDAO.findAll().forEach(users::add);
        return users;
    }
    public User updateUser(Long id, User user) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        Optional<User> existingUserOptional = userDAO.findById(id);
        if (existingUserOptional.isPresent()) {
            User existingUser = existingUserOptional.get();
            existingUser.setName(user.getName());
            existingUser.setEmail(user.getEmail());
            existingUser.setRole(user.getRole());
            existingUser.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            User updatedUser = userDAO.save(existingUser);
            return updatedUser;
        } else {
            return null;
        }
    }

    public User findByEmail(String email) {
        return userDAO.findByEmail(email);
    }
    public List<User> findAllByRole(Role role) {
        return userDAO.findAllByRole(role);
    }
}
