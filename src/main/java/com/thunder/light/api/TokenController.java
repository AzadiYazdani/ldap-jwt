package com.thunder.light.api;

import com.thunder.light.model.dto.AuthRequest;
import com.thunder.light.service.token.TokenService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authentication")
@Slf4j
public class TokenController {

    private final TokenService tokenService;

    public TokenController(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @PostMapping(value = "/login")
    public ResponseEntity<String> login(@RequestBody @NonNull AuthRequest authRequest){
        log.info("get token");
        String token = tokenService.newToken(authRequest.getUsername(), authRequest.getPassword());
        return new ResponseEntity(token, HttpStatus.OK);
    }
}
