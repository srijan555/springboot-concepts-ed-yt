package com.ed.springboot.cricketteam.service;

import com.ed.springboot.cricketteam.dto.PlayerRequestDTO;
import com.ed.springboot.cricketteam.dto.PlayerResponseDTO;
import com.ed.springboot.cricketteam.entity.Player;
import com.ed.springboot.cricketteam.exception.PlayerNotFoundException;
import com.ed.springboot.cricketteam.repository.PlayerRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class PlayerServiceImpl implements PlayerService {

    //Dependency Injection(Constructor based)
    private final PlayerRepository repository;
    private final ModelMapper modelMapper;

    public PlayerServiceImpl(PlayerRepository repository, ModelMapper modelMapper) {
        this.repository = repository;
        this.modelMapper = modelMapper;
        log.debug("PlayerServiceImpl initialized with repository={} modelMapper={}",
                repository != null ? repository.getClass().getSimpleName() : "null",
                modelMapper != null ? modelMapper.getClass().getSimpleName() : "null");
    }

    @Override
    public PlayerResponseDTO addNewPlayer(PlayerRequestDTO playerRequestDTO) {
        log.debug("addNewPlayer called with playerRequestDTO={}", playerRequestDTO);
        try {
            Player player = modelMapper.map(playerRequestDTO, Player.class);
            log.trace("Mapped PlayerRequestDTO to Player: {}", player);
            Player savedPlayer = repository.save(player);
            log.info("Player saved with id: {}", savedPlayer.getId());
            return modelMapper.map(savedPlayer, PlayerResponseDTO.class);
        } catch (Exception ex) {
            log.error("Failed to add new player: {}", playerRequestDTO, ex);
            throw ex;
        }
    }

    @Override
    public PlayerResponseDTO updatePlayer(Integer id, PlayerRequestDTO updatedPlayerDTO) {
        log.debug("updatePlayer called with id={} updatedPlayerDTO={}", id, updatedPlayerDTO);
        try {
            Player existingPlayer = repository.findById(id)
                    .orElseThrow(() -> {
                        log.warn("updatePlayer - player not found with id={}", id);
                        return new PlayerNotFoundException("Player not found with id: " + id);
                    });
            modelMapper.map(updatedPlayerDTO, existingPlayer);
            log.trace("Mapped updated DTO into existing Player: {}", existingPlayer);
            Player updated = repository.save(existingPlayer);
            log.info("Player updated with id={}", updated.getId());
            return modelMapper.map(updated, PlayerResponseDTO.class);
        } catch (PlayerNotFoundException pnfe) {
            throw pnfe;
        } catch (Exception ex) {
            log.error("Failed to update player with id={}", id, ex);
            throw ex;
        }
    }

    @Override
    public void removePlayerById(Integer id) {
        log.debug("removePlayerById called with id={}", id);
        try {
            if (!repository.existsById(id)) {
                log.warn("removePlayerById - player not found with id={}", id);
                throw new PlayerNotFoundException("Player not found with id: " + id);
            } else {
                repository.deleteById(id);
                log.info("Player deleted with id={}", id);
            }
        } catch (PlayerNotFoundException pnfe) {
            throw pnfe;
        } catch (Exception ex) {
            log.error("Failed to remove player with id={}", id, ex);
            throw ex;
        }
    }

    @Override
    public PlayerResponseDTO getPlayerById(Integer id) {
        log.debug("getPlayerById called with id={}", id);
        try {
            Player player = repository.findById(id)
                    .orElseThrow(() -> {
                        log.warn("getPlayerById - player not found with id={}", id);
                        return new PlayerNotFoundException("Player not found with id: " + id);
                    });
            log.info("Found player with id={}", id);
            return modelMapper.map(player, PlayerResponseDTO.class);
        } catch (PlayerNotFoundException pnfe) {
            throw pnfe;
        } catch (Exception ex) {
            log.error("Failed to get player by id={}", id, ex);
            throw ex;
        }
    }

    @Override
    public PlayerResponseDTO getPlayerByJerseyNo(int jerseyNo) {
        log.debug("getPlayerByJerseyNo called with jerseyNo={}", jerseyNo);
        try {
            Player byJerseyNo = repository.findByJerseyNo(jerseyNo);
            if (byJerseyNo == null) {
                log.warn("getPlayerByJerseyNo - player not found with jerseyNo={}", jerseyNo);
                throw new PlayerNotFoundException("Player not found with jersey no: " + jerseyNo);
            }
            log.info("Found player with jerseyNo={}", jerseyNo);
            return modelMapper.map(byJerseyNo, PlayerResponseDTO.class);
        } catch (PlayerNotFoundException pnfe) {
            throw pnfe;
        } catch (Exception ex) {
            log.error("Failed to get player by jerseyNo={}", jerseyNo, ex);
            throw ex;
        }
    }

    @Override
    public List<PlayerResponseDTO> getAllPlayers() {
        log.debug("getAllPlayers called");
        try {
            List<PlayerResponseDTO> allPlayers = repository.findAll().stream()
                    .map(player -> modelMapper.map(player, PlayerResponseDTO.class))
                    .collect(Collectors.toList());
            log.info("Retrieved all players, count={}", allPlayers.size());
            return allPlayers;
        } catch (Exception ex) {
            log.error("Failed to retrieve all players", ex);
            throw ex;
        }
    }
}
