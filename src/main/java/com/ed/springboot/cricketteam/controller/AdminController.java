package com.ed.springboot.cricketteam.controller;

import com.ed.springboot.cricketteam.dto.PlayerRequestDTO;
import com.ed.springboot.cricketteam.dto.PlayerResponseDTO;
import com.ed.springboot.cricketteam.entity.Player;
import com.ed.springboot.cricketteam.service.PlayerService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cricketteam/admin")
public class AdminController {

    private final PlayerService playerService;
    public AdminController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/players")
    public PlayerResponseDTO addPlayer(@RequestBody PlayerRequestDTO p){
        return playerService.addNewPlayer(p);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/players/{id}")
    public String deletePlayer(@PathVariable Integer id) {
        playerService.removePlayerById(id);
        return "Player with id " + id + " has been deleted.";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/admin-endpoint")
    public String adminEndpoint(){
        System.out.println("[AdminController] adminEndpoint() accessed");
        return "Admin endpoint accessed";
    }
}
