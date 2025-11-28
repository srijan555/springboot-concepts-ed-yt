package com.ed.springboot.cricketteam.controller;

import com.ed.springboot.cricketteam.service.PlayerSalaryService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cricketteam/salary")
public class PlayerSalaryController {
    private final PlayerSalaryService playerSalaryService;
    public PlayerSalaryController(PlayerSalaryService playerSalaryService) {
        this.playerSalaryService = playerSalaryService;
    }

    @PostMapping("/creditFull/{playerId}")
    public String creditFullMonthlyPayment(@PathVariable Integer playerId, @RequestParam Double salary, @RequestParam Double bonus,
                                           @RequestParam Double matchReward,@RequestParam String month){
        try{
            playerSalaryService.creditFullMonthlyPayment(playerId,salary,bonus,matchReward,month);
            return "Salary credited successfully for player id: "+playerId;
        }catch (Exception e){
            return "Failed to credit salary for player id: "+playerId+" due to: "+e.getMessage();
        }
    }
}
