package com.edu.medproject.api.controller;

import com.edu.medproject.api.domain.user.User;
import com.edu.medproject.api.domain.user.UserAuthenticatorDTO;
import com.edu.medproject.api.infra.security.TokenDataDTO;
import com.edu.medproject.api.infra.security.TokenService;
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
    @Autowired
    private TokenService tokenService;

    @PostMapping
    @Transactional
    public ResponseEntity makeLogin(@RequestBody @Valid UserAuthenticatorDTO data) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var authenticator = authenticationManager.authenticate(authenticationToken);
        var tokenJWT = tokenService.tokenGenerator((User) authenticator.getPrincipal());
        return ResponseEntity.ok(new TokenDataDTO(tokenJWT));
    }
}
