package com.ed.springboot.cricketteam.service;

import com.ed.springboot.cricketteam.entity.Player;
import com.ed.springboot.cricketteam.entity.PlayerSalary;
import com.ed.springboot.cricketteam.repository.PlayerRepository;
import com.ed.springboot.cricketteam.repository.PlayerSalaryRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class PlayerSalaryService {
    private final PlayerRepository playerRepository;
    private final PlayerSalaryRepository playerSalaryRepository;
    public PlayerSalaryService(PlayerRepository playerRepository, PlayerSalaryRepository playerSalaryRepository) {
        this.playerRepository = playerRepository;
        this.playerSalaryRepository = playerSalaryRepository;
    }

    @Transactional
    public void creditFullMonthlyPayment(Integer playerId, Double salary, Double bonus, Double matchReward, String month){
        Player player = playerRepository.findById(playerId).orElseThrow(()->new RuntimeException("Player not found with id: "+playerId));
        //Step1: Salary credit
        PlayerSalary baseSalary= new PlayerSalary();
        baseSalary.setPlayer(player);
        baseSalary.setAmount(salary);
        baseSalary.setMonth(month);
        playerSalaryRepository.save(baseSalary);
        //Step2: Bonus credit
        PlayerSalary bonusSalary= new PlayerSalary();
        bonusSalary.setPlayer(player);
        bonusSalary.setAmount(bonus);
        bonusSalary.setMonth(month);
        playerSalaryRepository.save(bonusSalary);
        //Step3: Match reward credit - simulate error
        if(matchReward>10000){
            throw new RuntimeException("Match reward too high, transaction rolled back");
        }
        PlayerSalary reward= new PlayerSalary();
        reward.setPlayer(player);
        reward.setAmount(matchReward);
        reward.setMonth(month);
        playerSalaryRepository.save(reward);
    }
}
