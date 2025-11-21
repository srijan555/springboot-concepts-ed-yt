package com.ed.springboot.cricketteam.repository;

import com.ed.springboot.cricketteam.entity.PlayerSalary;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerSalaryRepository extends JpaRepository<PlayerSalary,Integer> {
}
