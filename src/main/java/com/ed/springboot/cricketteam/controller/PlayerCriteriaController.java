package com.ed.springboot.cricketteam.controller;

import com.ed.springboot.cricketteam.entity.Player;
import com.ed.springboot.cricketteam.service.PlayerCriteriaService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/criteria")
public class PlayerCriteriaController {

    private final PlayerCriteriaService playerCriteriaService;
    public PlayerCriteriaController(PlayerCriteriaService playerCriteriaService) {
        this.playerCriteriaService = playerCriteriaService;
    }

    @GetMapping("/players/byRole")
    public List<Player> getPlayersByRole(@RequestParam String role){
        return playerCriteriaService.getPlayersByRole(role);
    }

}
