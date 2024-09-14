package com.edu.medproject.api.controller;

import com.edu.medproject.api.domain.user.UserAuthenticatorDTO;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AuthenticatorController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping
    @Transactional
    public ResponseEntity makeLogin(@RequestBody @Valid UserAuthenticatorDTO data) {
        var token = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var authenticator = authenticationManager.authenticate(token);

        return ResponseEntity.ok().build();
    }
}
