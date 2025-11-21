package com.ed.springboot.cricketteam.repository;

import com.ed.springboot.cricketteam.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Integer> {
    Player findByJerseyNo(int jerseyNo);
}
