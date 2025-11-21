package com.ed.springboot.cricketteam.dto;

import java.util.List;

public class MatchResponseDTO {
    private Integer id;
    private String opponent;
    private String venue;
    private String matchDate;
    private List<PlayerResponseDTO> players;

    public MatchResponseDTO() {}

    public MatchResponseDTO(Integer id, String opponent, String venue, String matchDate, List<PlayerResponseDTO> players) {
        this.id = id;
        this.opponent = opponent;
        this.venue = venue;
        this.matchDate = matchDate;
        this.players = players;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getOpponent() { return opponent; }
    public void setOpponent(String opponent) { this.opponent = opponent; }
    public String getVenue() { return venue; }
    public void setVenue(String venue) { this.venue = venue; }
    public String getMatchDate() { return matchDate; }
    public void setMatchDate(String matchDate) { this.matchDate = matchDate; }
    public List<PlayerResponseDTO> getPlayers() { return players; }
    public void setPlayers(List<PlayerResponseDTO> players) { this.players = players; }
}

