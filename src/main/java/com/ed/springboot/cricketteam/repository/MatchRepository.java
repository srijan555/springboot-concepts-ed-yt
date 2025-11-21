package com.ed.springboot.cricketteam.repository;

import com.ed.springboot.cricketteam.dto.MatchResponseDTO;
import com.ed.springboot.cricketteam.entity.Match;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MatchRepository extends JpaRepository<Match,Integer> {
    @EntityGraph(attributePaths = "players")
    @Query("SELECT m FROM Match m WHERE m.id = :id")
    Match findByIdWithPlayers(@Param("id") Integer id);
}
