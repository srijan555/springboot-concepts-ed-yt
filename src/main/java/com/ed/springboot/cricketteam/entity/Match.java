package com.ed.springboot.cricketteam.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "matches")
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String opponent;
    private String venue;
    private String matchDate;
    @ManyToMany(mappedBy = "matches", fetch = FetchType.EAGER)
    @JsonIgnoreProperties("matches")
    private Set<Player> players = new HashSet<>();
    public Match() {
    }
    public Match(Integer id, String opponent, String venue, String matchDate, Set<Player> players) {
        this.id = id;
        this.opponent = opponent;
        this.venue = venue;
        this.matchDate = matchDate;
        this.players = players;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOpponent() {
        return opponent;
    }

    public void setOpponent(String opponent) {
        this.opponent = opponent;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public String getMatchDate() {
        return matchDate;
    }

    public void setMatchDate(String matchDate) {
        this.matchDate = matchDate;
    }

    public Set<Player> getPlayers() {
        return players;
    }

    public void setPlayers(Set<Player> players) {
        this.players = players;
    }
}
