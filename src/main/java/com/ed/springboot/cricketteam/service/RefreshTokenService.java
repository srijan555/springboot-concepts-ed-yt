package com.ed.springboot.cricketteam.service;

import com.ed.springboot.cricketteam.entity.AppUser;
import com.ed.springboot.cricketteam.entity.RefreshToken;
import com.ed.springboot.cricketteam.repository.RefreshTokenRepository;
import com.ed.springboot.cricketteam.repository.UserRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Instant;

@Service
@Transactional
public class RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;
    private final UserRepository userRepository;
    public RefreshTokenService(RefreshTokenRepository refreshTokenRepository, UserRepository userRepository) {
        this.refreshTokenRepository = refreshTokenRepository;
        this.userRepository = userRepository;
    }

    public RefreshToken createRefreshToken(String username){
        System.out.println("[RefreshTokenService] createRefreshToken() called for username: " + username);
        AppUser user= userRepository.findByUsername(username).get();
        System.out.println("user: " + user);
        refreshTokenRepository.deleteByUser(user);
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setUser(user);
        refreshToken.setToken(java.util.UUID.randomUUID().toString());
        refreshToken.setExpiryDate(Instant.now().plusSeconds(7*24*60*60)); // 7 days
        System.out.println("[RefreshTokenService] Created refresh token: " + refreshToken.getToken() + " with expiry date: " + refreshToken.getExpiryDate());
        return refreshTokenRepository.save(refreshToken);
    }

    public RefreshToken validateRefreshToken(String token){
        System.out.println("[RefreshTokenService] validateRefreshToken() called with token: " + token);
        RefreshToken refreshToken = refreshTokenRepository.findByToken(token)
                .orElseThrow(() -> new RuntimeException("Invalid refresh token"));
        System.out.println("[RefreshTokenService] Found refresh token: " + refreshToken.getToken() + " with expiry date: " + refreshToken.getExpiryDate());
        if(refreshToken.getExpiryDate().isBefore(Instant.now())){
            refreshTokenRepository.delete(refreshToken);
            throw new RuntimeException("Refresh token expired");
        }
        return refreshToken;
    }
}
