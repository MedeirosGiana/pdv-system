package com.gm.pdv.controller;

import com.gm.pdv.dto.LoginDTO;
import com.gm.pdv.dto.TokenDTO;
import com.gm.pdv.security.CustomUserDetailsService;
import com.gm.pdv.security.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private CustomUserDetailsService userDetailsService;
    @Autowired
    private JwtService jwtService;
    @Value("${security.jwt.expiration}")
    private String expiration;

    @PostMapping()
    public ResponseEntity post(@Valid @RequestBody LoginDTO loginData){
        try {
            userDetailsService.verifyUserCredentials(loginData);
            String token = jwtService.generateToken(loginData.getUsername());
            //Gerar o token
            return  new ResponseEntity<>(new TokenDTO(token, expiration), HttpStatus.OK);
        }catch (Exception error){
            return new ResponseEntity<>(error.getMessage(), HttpStatus.UNAUTHORIZED);
        }
    }
}
