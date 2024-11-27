package com.example.acccountservice.Controllers;

import com.example.acccountservice.Model.User;
import com.example.acccountservice.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;


    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody User user) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        User foundUser = userService.findByEmail(user.getEmail());
        if (foundUser != null && passwordEncoder.matches(user.getPassword(), foundUser.getPassword())) {
            // Returnera användarens roll och annan relevant information
            return ResponseEntity.ok(foundUser);
        } else {
            return ResponseEntity.status(401).body("Inloggning misslyckades: Felaktig e-post eller lösenord");
        }
    }
    @PostMapping("/register")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        userService.SaveUser(user);
        return ResponseEntity.ok(user);
    }

}
