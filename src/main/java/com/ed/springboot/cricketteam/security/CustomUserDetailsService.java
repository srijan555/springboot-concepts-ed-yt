package com.ed.springboot.cricketteam.security;

import com.ed.springboot.cricketteam.entity.AppUser;
import com.ed.springboot.cricketteam.repository.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("[CustomUserDetailsService] loadUserByUsername() called with username: " + username);
        AppUser user = userRepository.findByUsername(username).get();
        System.out.println("[CustomUserDetailsService] Found user: " + user);
        if(user==null){
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        List<SimpleGrantedAuthority> authorities = user.getRoles().stream().map(r -> new SimpleGrantedAuthority(r.getName().name())).collect(Collectors.toList());
        System.out.println("[CustomUserDetailsService] User authorities: " + authorities);
        User user1 = new User(user.getUsername(), user.getPassword(), authorities);
        System.out.println("[CustomUserDetailsService] Returning UserDetails: " + user1);
        return user1;
    }
}
