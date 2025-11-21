package com.ed.springboot.cricketteam.service;

import com.ed.springboot.cricketteam.dto.MatchResponseDTO;
import com.ed.springboot.cricketteam.entity.Match;

import java.util.List;

public interface MatchService {
    Match createMatch(Match match);
    Match getMatchById(Integer id);
    List<Match> getAllMatches();
    MatchResponseDTO addPlayerToMatch(Integer matchId, Integer playerId);
}
