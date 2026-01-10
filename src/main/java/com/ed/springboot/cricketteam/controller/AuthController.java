package com.ed.springboot.cricketteam.controller;

import com.ed.springboot.cricketteam.dto.*;
import com.ed.springboot.cricketteam.entity.RefreshToken;
import com.ed.springboot.cricketteam.security.JwtUtil;
import com.ed.springboot.cricketteam.service.AuthService;
import com.ed.springboot.cricketteam.service.RefreshTokenService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/cricketteam/auth")
public class AuthController {

    private final AuthService authService;
    private final RefreshTokenService refreshTokenService;
    private final JwtUtil jwtUtil;
    public AuthController(AuthService authService, RefreshTokenService refreshTokenService, JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
        this.refreshTokenService = refreshTokenService;
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequestDTO request){
        System.out.println("[AuthController] register() called with username: " + request.getUsername());
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDTO request){
        System.out.println("[AuthController] login() called with username: " + request.getUsername());
        AuthResponseDTO response=authService.login(request);
        System.out.println("[AuthController] login() returning response: " + response);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/refresh")
    public ResponseEntity<?> refresh(@RequestBody RefreshTokenRequestDTO request){
        System.out.println("[AuthController] refresh() called with refresh token: " + request.getRefreshToken());
        RefreshToken refreshToken=refreshTokenService.validateRefreshToken(request.getRefreshToken());
        System.out.println("[AuthController] refresh() validated refresh token for user: " + refreshToken.getUser().getUsername());
        String newAccessToken= jwtUtil.generateToken(refreshToken.getUser().getUsername(),
                refreshToken.getUser().getRoles().stream().map(role -> role.getName().name()).collect(Collectors.toList()));
        System.out.println("[AuthController] refresh() generated new access token: " + newAccessToken);
        return ResponseEntity.ok(
                new RefreshTokenResponseDTO(newAccessToken,refreshToken.getToken())
        );
    }
}
