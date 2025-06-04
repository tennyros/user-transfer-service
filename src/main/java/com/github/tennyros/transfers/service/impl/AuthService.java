package com.github.tennyros.transfers.service.impl;

import com.github.tennyros.transfers.entity.User;
import com.github.tennyros.transfers.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public String authenticate(String emailOrPhone, String rawPassword) {
        Optional<User> optionalUser = userRepository.findByEmail(emailOrPhone);

        if (optionalUser.isEmpty()) {
            optionalUser = userRepository.findByPhone(emailOrPhone);
        }

        User user = optionalUser
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        if (!passwordEncoder.matches(rawPassword, user.getPassword())) {
            throw new BadCredentialsException("Invalid password");
        }

        return jwtService.generateToken(user.getId());
    }

}
