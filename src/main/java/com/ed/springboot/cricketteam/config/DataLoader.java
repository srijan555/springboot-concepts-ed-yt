package com.ed.springboot.cricketteam.config;

import com.ed.springboot.cricketteam.entity.AppUser;
import com.ed.springboot.cricketteam.entity.Role;
import com.ed.springboot.cricketteam.entity.RoleName;
import com.ed.springboot.cricketteam.repository.RoleRepository;
import com.ed.springboot.cricketteam.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Set;

@Component
public class DataLoader {
    //runner to create roles in DB and a default admin user if not present

    @Bean
    CommandLineRunner init(RoleRepository roleRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            //create roles if not present
            if (!roleRepository.findByName(RoleName.ROLE_ADMIN).isPresent()) {
                roleRepository.save(new Role(RoleName.ROLE_ADMIN));
            }
            if (!roleRepository.findByName(RoleName.ROLE_USER).isPresent()) {
                roleRepository.save(new Role(RoleName.ROLE_USER));
            }
            if (!roleRepository.findByName(RoleName.ROLE_SCORER).isPresent()) {
                roleRepository.save(new Role(RoleName.ROLE_SCORER));
            }
            //create default admin user if not present
            if (!userRepository.findByUsername("admin").isPresent()) {
                AppUser admin = new AppUser();
                admin.setUsername("admin");
                admin.setPassword(passwordEncoder.encode("admin123"));
                Role adminRole = roleRepository.findByName(RoleName.ROLE_ADMIN).get();
                admin.setRoles(Collections.singleton(adminRole));
                userRepository.save(admin);
            }
        };
    }
}
