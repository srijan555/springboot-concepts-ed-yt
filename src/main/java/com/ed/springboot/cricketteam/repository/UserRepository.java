package com.ed.springboot.cricketteam.repository;

import com.ed.springboot.cricketteam.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<AppUser,Integer> {
    Optional<AppUser> findByUsername(String username);
}
