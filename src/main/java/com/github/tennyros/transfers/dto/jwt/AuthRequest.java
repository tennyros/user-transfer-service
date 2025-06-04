package com.github.tennyros.transfers.dto.jwt;

public record AuthRequest(

        String emailOrPhone,
        String password

) {
}
