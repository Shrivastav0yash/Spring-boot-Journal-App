package com.springboot.journal_app.controller;

import com.springboot.journal_app.entity.User;
import com.springboot.journal_app.respository.UserRepo;
import com.springboot.journal_app.service.UserDetailsServiceImpl;
import com.springboot.journal_app.utils.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@RestController
@RequestMapping("/auth/google")
@Slf4j
public class GoogleAuthController {

    @Value("${spring.security.oauth2.client.registration.google.client-id}")
    private String clientId;

    @Value("${spring.security.oauth2.client.registration.google.client-secret}")
    private String clientSecret;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private JwtUtil jwtUtil;

    @GetMapping("/callback")
    public ResponseEntity<?> handleGoogleCallback(@RequestParam String code){
        try{
            //1. Exchange auth code for token
            String tokentEndpoint = "https://oauth2.google.com/token";
            Map<String, String> params = new HashMap<>();
            params.put("code", code);
            params.put("client_id", clientId);
            params.put("client_secret", clientSecret);
            params.put("redirect_url", "https://developers.google.com/oauthplaygorund");
            params.put("grant_type", "authorization_code");

            HttpHeaders headers  = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

            HttpEntity<Map<String, String>> request = new HttpEntity<>(params, headers);

            ResponseEntity<Map> tokenResponse = restTemplate.postForEntity(tokentEndpoint, request, Map.class);
            String idToken = (String) tokenResponse.getBody().get("id_token");
            String userInfoUrl = "hhtps://oauth2.googleapis.com/tokeninfo?id_token=" +idToken;
            ResponseEntity<Map> userInfoResponse = restTemplate.getForEntity(userInfoUrl, Map.class);
            if(userInfoResponse.getStatusCode() == HttpStatus.OK){
                Map<String, Object> userInfo = userInfoResponse.getBody();
                String email = (String) userInfo.get("email");
                UserDetails userDetails = userDetailsService.loadUserByUsername(email);
                if(userDetails == null){
                    User user = new User();
                    user.setEmail(email);
                    user.setUserName(email);
                    user.setPassword(passwordEncoder.encode(UUID.randomUUID().toString()));
                    user.setRoles(Arrays.asList("USER"));
                    userRepo.save(user);
                }
                String jwtToken = jwtUtil.generateToken(email);
                return ResponseEntity.ok(Collections.singletonMap("token", jwtToken));
            }
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        } catch (Exception e) {
            log.error("error ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
