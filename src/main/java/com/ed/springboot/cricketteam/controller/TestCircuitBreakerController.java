package com.ed.springboot.cricketteam.controller;

import com.ed.springboot.cricketteam.service.PlayerInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cricketteam/test-circuit-breaker")
public class TestCircuitBreakerController {

    @Autowired
    private PlayerInfoService playerInfoService;

    @GetMapping("/players/fallback")
    public String testCircuitBreakerFallback() {
        return playerInfoService.getPlayerFromPlayerService();
    }
}
