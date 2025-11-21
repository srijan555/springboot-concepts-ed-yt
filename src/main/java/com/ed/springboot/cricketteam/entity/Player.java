package com.ed.springboot.cricketteam.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotBlank(message="Player name cannot be blank")
    @Size(min = 3,max = 50,message = "Player name must be between 3 and 50 characters")
    private String name;
    @Column(name="jersey_no")
    @Min(value = 1,message = "Jersey number must be greater than 0")
    @Max(value = 100, message = "Jersey number must be less than or equal to 100")
    private int jerseyNo;
    @NotBlank(message = "Role must not be blank")
    private String role;
    @Column(name="captain")
    private boolean captain;
    @Column(name = "debut_date")
    @Past(message = "Debut date must be in the past")
    private LocalDate debutDate;
    @ManyToOne
    @JoinColumn(name = "team_id")
    private CricketTeam team;
    private Double strikeRate;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "player_match",
            joinColumns = @JoinColumn(name = "player_id"),
            inverseJoinColumns = @JoinColumn(name = "match_id")
    )
    @JsonIgnoreProperties("players")
    private Set<Match> matches = new HashSet<>();

    //No arg constructor
    public Player() {
    }
    //All args constructor
    public Player(Integer id, String name, int jerseyNo, String role, boolean captain, LocalDate debutDate, CricketTeam team) {
        this.id = id;
        this.name = name;
        this.jerseyNo = jerseyNo;
        this.role = role;
        this.captain = captain;
        this.debutDate = debutDate;
        this.team = team;
    }

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getJerseyNo() {
        return jerseyNo;
    }

    public void setJerseyNo(int jerseyNo) {
        this.jerseyNo = jerseyNo;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isCaptain() {
        return captain;
    }

    public void setCaptain(boolean captain) {
        this.captain = captain;
    }

    public LocalDate getDebutDate() {
        return debutDate;
    }

    public void setDebutDate(LocalDate debutDate) {
        this.debutDate = debutDate;
    }

    public Double getStrikeRate() {
        return strikeRate;
    }

    public void setStrikeRate(Double strikeRate) {
        this.strikeRate = strikeRate;
    }

    public CricketTeam getTeam() {
        return team;
    }
    public void setTeam(CricketTeam team) {
        this.team = team;
    }

    public Set<Match> getMatches() {
        return matches;
    }

    public void setMatches(Set<Match> matches) {
        this.matches = matches;
    }

    @Override
    public String toString() {
        return "Player{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", jerseyNo=" + jerseyNo +
                ", role='" + role + '\'' +
                ", captain=" + captain +
                ", debutDate=" + debutDate +
                ", team=" + team +
                ", matches=" + matches +
                '}';
    }
}
