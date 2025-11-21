package com.ed.springboot.cricketteam.dto;

public class PlayerResponseDTO {
    private Integer id;
    private String name;
    private int jerseyNo;
    private String role;
    private boolean captain;
    private String debutDate;

    public PlayerResponseDTO() {}

    public PlayerResponseDTO(Integer id, String name, int jerseyNo, String role, boolean captain, String debutDate) {
        this.id = id;
        this.name = name;
        this.jerseyNo = jerseyNo;
        this.role = role;
        this.captain = captain;
        this.debutDate = debutDate;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public int getJerseyNo() { return jerseyNo; }
    public void setJerseyNo(int jerseyNo) { this.jerseyNo = jerseyNo; }
    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
    public boolean isCaptain() { return captain; }
    public void setCaptain(boolean captain) { this.captain = captain; }
    public String getDebutDate() { return debutDate; }
    public void setDebutDate(String debutDate) { this.debutDate = debutDate; }
}

