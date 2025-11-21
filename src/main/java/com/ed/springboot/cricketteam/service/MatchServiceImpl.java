package com.ed.springboot.cricketteam.service;

import com.ed.springboot.cricketteam.dto.MatchResponseDTO;
import com.ed.springboot.cricketteam.dto.PlayerResponseDTO;
import com.ed.springboot.cricketteam.entity.Match;
import com.ed.springboot.cricketteam.entity.Player;
import com.ed.springboot.cricketteam.exception.PlayerNotFoundException;
import com.ed.springboot.cricketteam.repository.MatchRepository;
import com.ed.springboot.cricketteam.repository.PlayerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MatchServiceImpl implements MatchService {

    private final MatchRepository matchRepository;
    private final PlayerRepository playerRepository;

    public MatchServiceImpl(MatchRepository matchRepository, PlayerRepository playerRepository) {
        this.matchRepository = matchRepository;
        this.playerRepository = playerRepository;
    }

    @Override
    public Match createMatch(Match match) {
        return matchRepository.save(match);
    }

    @Override
    public Match getMatchById(Integer id) {
        return matchRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Match not found with id: " + id));
    }

    @Override
    public List<Match> getAllMatches() {
        return matchRepository.findAll();
    }

    @Override
    public MatchResponseDTO addPlayerToMatch(Integer matchId, Integer playerId) {
        Match match = getMatchById(matchId);
        Player player = playerRepository.findById(playerId)
                .orElseThrow(() -> new PlayerNotFoundException("Player not found with id: " + playerId));
        System.out.println("Adding player " + player.getName() + " to match against " + match.getOpponent());

        match.getPlayers().add(player);
        player.getMatches().add(match);

        matchRepository.save(match);
        playerRepository.save(player);

        // Reload match with players eagerly fetched
        Match updatedMatch = matchRepository.findByIdWithPlayers(matchId);
        return new MatchResponseDTO(
            updatedMatch.getId(),
            updatedMatch.getOpponent(),
            updatedMatch.getVenue(),
            updatedMatch.getMatchDate(),
            updatedMatch.getPlayers().stream().map(p -> new PlayerResponseDTO(
                p.getId(),
                p.getName(),
                p.getJerseyNo(),
                p.getRole(),
                p.isCaptain(),
                p.getDebutDate() != null ? p.getDebutDate().toString() : null
            )).collect(Collectors.toList())
        );
    }
}
