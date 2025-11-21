package com.ed.springboot.cricketteam.repository;

import com.ed.springboot.cricketteam.entity.CricketTeam;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CricketTeamRepository extends JpaRepository<CricketTeam,Integer> {
}
