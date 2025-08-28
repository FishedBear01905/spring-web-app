package com.example.springwebapp.repository;

import com.example.springwebapp.model.GambleUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GambleUserRepository extends JpaRepository<GambleUser, Long> {
    GambleUser findByUserNameAndPassword(String userName, String password);
}