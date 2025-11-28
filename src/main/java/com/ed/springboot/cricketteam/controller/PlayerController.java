package com.ed.springboot.cricketteam.controller;

import com.ed.springboot.cricketteam.dto.PlayerRequestDTO;
import com.ed.springboot.cricketteam.dto.PlayerResponseDTO;
import com.ed.springboot.cricketteam.service.PlayerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/cricketteam/players")
@Tag(name = "Player Management", description = "APIs for managing cricket team players")
public class PlayerController {

    private static final Logger logger = LoggerFactory.getLogger(PlayerController.class);

    private final PlayerService playerService;
    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @PostMapping
    @Operation(summary = "Add a new player", description = "Adds a new player to the cricket team")
    public PlayerResponseDTO addNewPlayer(@Valid @RequestBody PlayerRequestDTO playerRequestDTO) {
        log.info("Adding new player: {}",playerRequestDTO.getName());
        logger.info("INFO:slf4j Adding new player: {}", playerRequestDTO.getName());
        return playerService.addNewPlayer(playerRequestDTO);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing player", description = "Updates the details of an existing player by ID")
    public PlayerResponseDTO updatePlayer(@PathVariable Integer id,@Valid @RequestBody PlayerRequestDTO updatedPlayerDTO) {
        log.info("Updating player with id: {}",id);
        return playerService.updatePlayer(id, updatedPlayerDTO);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    @Operation(summary = "Remove a player", description = "Removes a player from the cricket team by ID")
    public String removePlayerById(@PathVariable Integer id) {
        log.warn("Removing player with id: {}",id);
        playerService.removePlayerById(id);
        return "Player with id " + id + " has been removed.";
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get player by ID", description = "Fetches the details of a player by their ID")
    public PlayerResponseDTO getPlayerById(@PathVariable Integer id) {
        log.info("Fetching player with id: {}",id);
        return playerService.getPlayerById(id);
    }

    @GetMapping("/jersey/{jerseyNo}")
    @Operation(summary = "Get player by Jersey Number", description = "Fetches the details of a player by their Jersey Number")
    public PlayerResponseDTO getPlayerByJerseyNo(@PathVariable int jerseyNo) {
        log.info("Fetching player with jersey no: {}",jerseyNo);
        return playerService.getPlayerByJerseyNo(jerseyNo);
    }

    @GetMapping
    @Operation(summary = "Get all players", description = "Fetches the list of all players in the cricket team")
    public List<PlayerResponseDTO> getAllPlayers() {
        log.info("Fetching all players");
        return playerService.getAllPlayers();
    }
}
