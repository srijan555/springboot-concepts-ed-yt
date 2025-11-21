package com.ed.springboot.cricketteam.service;

import com.ed.springboot.cricketteam.entity.Player;
import com.ed.springboot.cricketteam.repository.PlayerCriteriaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerCriteriaService {

    private final PlayerCriteriaRepository playerCriteriaRepository;
    public PlayerCriteriaService(PlayerCriteriaRepository playerCriteriaRepository) {
        this.playerCriteriaRepository = playerCriteriaRepository;
    }

    public List<Player> getPlayersByRole(String role){
        return playerCriteriaRepository.findPlayersByRole(role);
    }
}
