package com.ed.springboot.cricketteam.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/scorer")
public class ScorerController {

    @PreAuthorize("hasRole('ROLE_SCORER','ROLE_ADMIN')")
    @PostMapping("/score")
    public String updateScore(@RequestBody String payload){
        //update score logic
        return "Score updated";
    }
}
