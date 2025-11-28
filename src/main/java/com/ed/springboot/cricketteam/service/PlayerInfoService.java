package com.ed.springboot.cricketteam.service;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PlayerInfoService {

    @Autowired
    private RestTemplate restTemplate;

    @RateLimiter(name = "playerServiceRateLimiter", fallbackMethod = "getPlayerFallback")
    //@Retry(name = "playerServiceRetry", fallbackMethod = "getPlayerFallback")
    //@CircuitBreaker(name = "playerServiceCircuitBreaker", fallbackMethod = "getPlayerFallback")
    public String getPlayerFromPlayerService() {
        String playerServiceUrl = "http://localhost:8080/api/player-service/players";
        return restTemplate.getForObject(playerServiceUrl, String.class);
    }

    public String getPlayerFallback(Throwable t) {
        return "Player Service request blocked or currently unavailable. Please try again later.";
    }
}
