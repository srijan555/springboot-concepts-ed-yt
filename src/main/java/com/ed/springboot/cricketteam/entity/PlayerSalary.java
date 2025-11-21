package com.ed.springboot.cricketteam.entity;

import javax.persistence.*;

@Entity
public class PlayerSalary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String month;
    private Double amount;
    @ManyToOne
    @JoinColumn(name = "player_id")
    private Player player;

    public PlayerSalary() {
    }
    public PlayerSalary(Integer id, String month, Double amount, Player player) {
        this.id = id;
        this.month = month;
        this.amount = amount;
        this.player = player;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
