package com.ed.springboot.cricketteam.service;

import com.ed.springboot.cricketteam.dto.PlayerRequestDTO;
import com.ed.springboot.cricketteam.dto.PlayerResponseDTO;
import com.ed.springboot.cricketteam.entity.Player;

import java.util.List;

public interface PlayerService {

    PlayerResponseDTO addNewPlayer(PlayerRequestDTO playerRequestDTO);
    PlayerResponseDTO updatePlayer(Integer id, PlayerRequestDTO updatedPlayerDTO);
    void removePlayerById(Integer id);
    PlayerResponseDTO getPlayerById(Integer id);
    PlayerResponseDTO getPlayerByJerseyNo(int jerseyNo);
    List<PlayerResponseDTO> getAllPlayers();
}
