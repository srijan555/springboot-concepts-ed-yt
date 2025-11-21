package com.ed.springboot.cricketteam.security;

import com.ed.springboot.cricketteam.entity.AppUser;
import com.ed.springboot.cricketteam.repository.UserRepository;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.text.html.Option;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {
    
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;
    public JwtAuthFilter(JwtUtil jwtUtil, UserRepository userRepository) {
        this.jwtUtil = jwtUtil;
        this.userRepository = userRepository;
    }
    
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        System.out.println("[JwtAuthFilter] doFilterInternal() called for request: " + request.toString());
        System.out.println("[JwtAuthFilter] response: " + response);
        System.out.println("[JwtAuthFilter] filterChain: " + filterChain);
        final String authHeader = request.getHeader("Authorization");
        System.out.println("[JwtAuthFilter] Authorization header: " + authHeader);
        if(authHeader == null || !authHeader.startsWith("Bearer ")){
            filterChain.doFilter(request,response);
            return;
        }
        final String token = authHeader.substring(7);
        System.out.println("[JwtAuthFilter] Extracted token: " + token);
        if(!jwtUtil.validateToken(token)){
            filterChain.doFilter(request,response);
            System.out.println("Need to see what doFilter() is doing here");
            return;
        }
        String username = jwtUtil.extractUsername(token);
        List<String> roles = jwtUtil.extractRoles(token);
        List<SimpleGrantedAuthority> authorities = roles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
        System.out.println("[JwtAuthFilter] Extracted username from token: " + username);
        System.out.println("[JwtAuthFilter] Extracted roles from token: " + roles);
        System.out.println("[JwtAuthFilter] Converted roles to authorities: " + authorities);
        Optional<AppUser> userOpt = userRepository.findByUsername(username);
        System.out.println("[JwtAuthFilter] Retrieved user from database: " + userOpt);
        if(userOpt.isPresent()){
            AppUser user = userOpt.get();
            System.out.println("[JwtAuthFilter] User found: " + user);
            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                    user.getUsername(),null, authorities);
            System.out.println("[JwtAuthFilter] Created authToken: " + authToken);
            SecurityContextHolder.getContext().setAuthentication(authToken);
            System.out.println("Need to see what setAuthentication() is doing here");
        }
        filterChain.doFilter(request,response);
        System.out.println("Need to see what doFilter() is doing here");
    }
}
