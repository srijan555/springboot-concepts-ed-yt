package com.ed.springboot.cricketteam;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class CricketteamApplication {

	public static void main(String[] args) {
		SpringApplication.run(CricketteamApplication.class, args);
	}

}
