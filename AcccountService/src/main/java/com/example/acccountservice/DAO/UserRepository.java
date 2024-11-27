package com.example.acccountservice.DAO;

import com.example.acccountservice.Model.Role;
import com.example.acccountservice.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
    List<User> findAllByRole(Role role);
}