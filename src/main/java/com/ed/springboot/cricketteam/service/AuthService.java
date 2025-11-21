package com.ed.springboot.cricketteam.service;

import com.ed.springboot.cricketteam.dto.AuthResponseDTO;
import com.ed.springboot.cricketteam.dto.LoginRequestDTO;
import com.ed.springboot.cricketteam.dto.RegisterRequestDTO;
import com.ed.springboot.cricketteam.entity.AppUser;
import com.ed.springboot.cricketteam.entity.RefreshToken;
import com.ed.springboot.cricketteam.entity.Role;
import com.ed.springboot.cricketteam.entity.RoleName;
import com.ed.springboot.cricketteam.repository.RoleRepository;
import com.ed.springboot.cricketteam.repository.UserRepository;
import com.ed.springboot.cricketteam.security.JwtUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;
    private final RefreshTokenService refershTokenService;
    private final RoleRepository roleRepository;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil, AuthenticationManager authenticationManager, RefreshTokenService refershTokenService,RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
        this.refershTokenService = refershTokenService;
        this.roleRepository = roleRepository;
    }

    public String register(RegisterRequestDTO request){
        System.out.println("[AuthService] register() called with username: " + request.getUsername());
        if(userRepository.findByUsername(request.getUsername()).isPresent()){
            throw new RuntimeException("Username already exists");
        }
        AppUser user = new AppUser();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        Set<Role> assignedRoles=new HashSet<>();
        //if no roles are provided then assign ROLE_USER by default
        if(request.getRoles()==null || request.getRoles().isEmpty()){
            Role defaultRole = roleRepository.findByName(RoleName.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("ROLE_USER not configured in the database"));
            assignedRoles.add(defaultRole);
        }else{
            for(String roleString : request.getRoles()){
                RoleName roleName=RoleName.valueOf(roleString);
                Role role=roleRepository.findByName(roleName)
                        .orElseThrow(()-> new RuntimeException(roleString + " not configured in the database"));
                assignedRoles.add(role);
            }
        }
        user.setRoles(assignedRoles);
        System.out.println("[AuthService] Saving user: " + user.toString());
        userRepository.save(user);
        return "User registered successfully";
    }

    public AuthResponseDTO login(LoginRequestDTO request){
        try{
            System.out.println("[AuthService] login() called with username: " + request.getUsername());
            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                    request.getUsername(),request.getPassword());
            System.out.println("[AuthService] authToken = " + authToken);
            authenticationManager.authenticate(authToken);
        } catch (BadCredentialsException e) {
            throw new RuntimeException("Invalid username or password");
        }
        AppUser user=userRepository.findByUsername(request.getUsername()).orElseThrow(()->new RuntimeException("User not found"));
        List<String> roles = user.getRoles().stream().map(r -> r.getName().name()).collect(Collectors.toList());
        String accessToken= jwtUtil.generateToken(request.getUsername(),roles);
        System.out.println("[AuthService] Generated JWT access token: " + accessToken);
        RefreshToken refershToken = refershTokenService.createRefreshToken(request.getUsername());
        System.out.println("[AuthService] Generated refresh token: " + refershToken.getToken());
        AuthResponseDTO authResponseDTO = new AuthResponseDTO(accessToken, refershToken.getToken());
        System.out.println("[AuthService] Returning AuthResponseDTO: " + authResponseDTO);
        return authResponseDTO;
    }
}
