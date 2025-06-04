package com.github.tennyros.transfers.controller;

import com.github.tennyros.transfers.dto.jwt.AuthRequest;
import com.github.tennyros.transfers.dto.jwt.AuthResponse;
import com.github.tennyros.transfers.service.impl.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
        String token = authService.authenticate(request.emailOrPhone(), request.password());
        return ResponseEntity.ok(new AuthResponse(token));
    }

}
