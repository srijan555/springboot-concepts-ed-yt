package com.ed.springboot.cricketteam.dto;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.*;

import java.time.LocalDate;

public class PlayerRequestDTO {
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

    public PlayerRequestDTO() {
    }

    public PlayerRequestDTO(String name, int jerseyNo, String role, boolean captain, LocalDate debutDate) {
        this.name = name;
        this.jerseyNo = jerseyNo;
        this.role = role;
        this.captain = captain;
        this.debutDate = debutDate;
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

    @Override
    public String toString() {
        return "PlayerRequestDTO{" +
                "name='" + name + '\'' +
                ", jerseyNo=" + jerseyNo +
                ", role='" + role + '\'' +
                ", captain=" + captain +
                ", debutDate=" + debutDate +
                '}';
    }
}
