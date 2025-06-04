package com.github.tennyros.transfers.service.impl;

import com.github.tennyros.transfers.entity.User;
import com.github.tennyros.transfers.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserSecurityService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        boolean emailExists = user.getUserEmails().stream()
                .anyMatch(e -> e.getEmail().equals(email));

        if (!emailExists) {
            throw new BadCredentialsException("Email not registered for user");
        }

        return new AppUserDetails(user, email);
    }
}