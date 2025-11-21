package com.ed.springboot.cricketteam.service;

import com.ed.springboot.cricketteam.entity.CricketTeam;
import com.ed.springboot.cricketteam.repository.CricketTeamRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CricketTeamService {
    private final CricketTeamRepository cricketTeamRepository;
    public CricketTeamService(CricketTeamRepository cricketTeamRepository) {
        this.cricketTeamRepository = cricketTeamRepository;
    }

    //addTeam
    public CricketTeam addTeam(CricketTeam cricketTeam){
        return cricketTeamRepository.save(cricketTeam);
    }
    //getAllTeams
    public List<CricketTeam> getAllTeams() {
        return cricketTeamRepository.findAll();
    }
    //getTeamById
    public CricketTeam getTeamById(Integer id) {
        return cricketTeamRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Team not found with id: " + id));
    }
}
