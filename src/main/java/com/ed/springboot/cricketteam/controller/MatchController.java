package com.ed.springboot.cricketteam.controller;

import com.ed.springboot.cricketteam.dto.MatchResponseDTO;
import com.ed.springboot.cricketteam.entity.Match;
import com.ed.springboot.cricketteam.service.MatchService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cricketteam/matches")
public class MatchController {

    private final MatchService matchService;

    public MatchController(MatchService matchService) {
        this.matchService = matchService;
    }

    @PostMapping
    public Match createMatch(@RequestBody Match match) {
        return matchService.createMatch(match);
    }

    @GetMapping("/{id}")
    public Match getMatchById(@PathVariable Integer id) {
        return matchService.getMatchById(id);
    }

    @GetMapping
    public List<Match> getAllMatches() {
        return matchService.getAllMatches();
    }

    @PostMapping("/{matchId}/addPlayer/{playerId}")
    public MatchResponseDTO addPlayerToMatch(@PathVariable Integer matchId, @PathVariable Integer playerId) {
        return matchService.addPlayerToMatch(matchId, playerId);
    }
}
