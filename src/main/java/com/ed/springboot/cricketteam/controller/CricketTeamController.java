package com.ed.springboot.cricketteam.controller;

import com.ed.springboot.cricketteam.entity.CricketTeam;
import com.ed.springboot.cricketteam.service.CricketTeamService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cricketteam/teams")
public class CricketTeamController {

    private final CricketTeamService cricketTeamService;
    public CricketTeamController(CricketTeamService cricketTeamService) {
        this.cricketTeamService = cricketTeamService;
    }

    @PostMapping
    public CricketTeam addTeam(@RequestBody CricketTeam cricketTeam){
        return cricketTeamService.addTeam(cricketTeam);
    }

    @GetMapping
    public List<CricketTeam> getAllTeams() {
        return cricketTeamService.getAllTeams();
    }

    @GetMapping("/{id}")
    public CricketTeam getTeamById(@PathVariable Integer id) {
        return cricketTeamService.getTeamById(id);
    }
}
