package com.boon.boonapp.service;

import com.boon.boonapp.dao.TokenRepository;
import com.boon.boonapp.exception.TokenNotFoundException;
import com.boon.boonapp.model.Token;
import com.boon.boonapp.model.User;
import com.boon.boonapp.security.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Comparator;
import java.util.Optional;
import java.util.Set;

@Service
public class TokenService {

    @Autowired
    private TokenRepository tokenRepository;

    public Boolean isMostRecentTokenByUserValid(Long userId, Integer durationHours) {
        return isTokenActive(findMostRecentTokenByUserId(userId), durationHours);
    }

    public Boolean isTokenActive(Token token, Integer duration) {
        return token.getCreateDate().after(Timestamp.from(Instant.now().minusSeconds(duration * 3600)));
    }

    public Token findMostRecentTokenByUserId(Long userId) {
        Set<Token> tokensByUser = findAllTokensByUserId(userId);
        return tokensByUser.stream().max(Comparator.comparing(Token::getCreateDate))
                .orElseThrow(() -> new TokenNotFoundException(userId, "UNKNOWN"));
    }

    public Optional<Token> findTokenById(Long tokenId) {
        return tokenRepository.findById(tokenId);
    }

    public Token getTokenByValue(String token) {
        return tokenRepository.getTokenByValue(token)
                .orElseThrow(() -> new TokenNotFoundException(token));
    }

    public Set<Token> findAllTokensByUserId(Long userId) {
        Set<Token> result = tokenRepository.findAllByUserId(userId);
        if (result == null || result.isEmpty()) {
            throw new TokenNotFoundException(userId);
        }
        return result;
    }

    public Token createNewToken(User forUser, String value) {
        return save(Token.builder().user(forUser).value(value).build());
    }

    public Token save(Token token) {
        return tokenRepository.save(token);
    }

    public void deleteTokenById(Long tokenId) {
        tokenRepository.deleteById(tokenId);
    }

}
