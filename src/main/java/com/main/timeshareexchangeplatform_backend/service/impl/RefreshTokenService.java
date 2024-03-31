package com.main.timeshareexchangeplatform_backend.service.impl;

import com.main.timeshareexchangeplatform_backend.entity.RefreshToken;
import com.main.timeshareexchangeplatform_backend.entity.User;
import com.main.timeshareexchangeplatform_backend.repository.RefreshTokenRepository;
import com.main.timeshareexchangeplatform_backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
public class RefreshTokenService {
    public static final long JWT_TOKEN_VALIDITY = 1000 * 60 * 60;// 60m

    @Autowired
    private RefreshTokenRepository refreshTokenRepository;
    @Autowired
    private UserRepository userInfoRepository;

    public RefreshToken createRefreshToken(String username) {
        User user = userInfoRepository.findUserByUsername(username);
        RefreshToken refreshToken = RefreshToken.builder().user(user).token(UUID.randomUUID().toString())
                .expiryDate(Instant.now().plusMillis(JWT_TOKEN_VALIDITY)).build();

        if (refreshTokenRepository.findByUser(user) != null) {
            refreshTokenRepository.delete(refreshTokenRepository.findByUser(user));
        }

        return refreshTokenRepository.save(refreshToken);
    }

    public RefreshToken findByToken(String token) {
        return refreshTokenRepository.findByToken(token);
    }

    public RefreshToken verifyExpiration(RefreshToken token) {
        if (token.getExpiryDate().compareTo(Instant.now()) < 0) {
            refreshTokenRepository.delete(token);
            throw new RuntimeException(
                    token.getToken() + " Refresh token was expired. Please make a new signin request");
        }
        return token;
    }

    public void removeFromInstance(RefreshToken token) {
        refreshTokenRepository.delete(token);

    }
}
