package com.ed.springboot.cricketteam.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class CricketTeam {
    //one to many relationship with Player entity
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String teamName;
    private String country;
    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Player> players=new ArrayList<>();

    public CricketTeam() {
    }
    public CricketTeam(Integer id, String teamName, String country, List<Player> players) {
        this.id = id;
        this.teamName = teamName;
        this.country = country;
        this.players = players;
    }
    //add helper methods to manage bi-directional relationship
    public void addPlayer(Player player) {
        players.add(player);
        player.setTeam(this);
    }
    public void removePlayer(Player player) {
        players.remove(player);
        player.setTeam(null);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players.clear();
        if (players != null) {
            for (Player player : players) {
                addPlayer(player);
            }
        }
    }
}
