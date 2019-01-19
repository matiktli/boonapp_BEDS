package com.boon.boonapp.security;

import com.boon.boonapp.exception.AuthorizationException;
import com.boon.boonapp.exception.NotFoundBaseException;
import com.boon.boonapp.model.Token;
import com.boon.boonapp.model.User;
import com.boon.boonapp.service.TokenService;
import com.boon.boonapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

import static com.boon.boonapp.controller.BoonServiceConstants.TOKEN_DEFAULT_DURATION_HOURS;

@Service
public class SecurityService {

    private final TokenService tokenService;
    private final UserService userService;

    @Autowired
    public SecurityService(TokenService tokenService, UserService userService) {
        this.tokenService = tokenService;
        this.userService = userService;
    }

    public Token login(User userEmailPwd) {
        User user = userService.findUserByEmail(userEmailPwd.getEmail());
        if (user.getPassword().equals(userEmailPwd.getPassword())) {
            return tokenService.createNewToken(user, TokenUtil.generateToken());
        } else {
            throw new AuthorizationException("Not valid password");
        }
    }

    public Token register(User userData) {
        User user = userService.createNewUser(userData);
        return login(user); //it is the same at this stage
    }

    public Token getTokenFromDb(String tokenHeader) {
        if (!TokenUtil.isValidTokenFormat(tokenHeader)) {
            throw new AuthorizationException("Token format not valid");
        }
        try {
            return tokenService.getTokenByValue(tokenHeader);
        } catch (NotFoundBaseException ex) {
            throw new AuthorizationException(String.format("Token with value [%s] does not exists", tokenHeader));
        }
    }

    public User getUserFromToken(String tokenHeader) {
        return getTokenFromDb(tokenHeader).getUser();
    }

    public void validateTokenForUser(String tokenHeader, User user) {
        Token token = getTokenFromDb(tokenHeader);
        StringBuilder tokenAlaHashed = new StringBuilder();
        tokenHeader.chars().mapToObj(c -> (c%2 == 0) ? "*" : ((char) c)).forEach(tokenAlaHashed::append);
        if (!tokenService.isTokenActive(token, TOKEN_DEFAULT_DURATION_HOURS)) {
            throw new AuthorizationException(String.format("Token [%s] expired.", tokenAlaHashed.toString()));
        }
        if (!token.getUser().getEmail().equals(user.getEmail())) {
            throw new AuthorizationException(String.format("Token [%s] does not belongs to User %s", tokenAlaHashed.toString(), user));
        }
        Token tokenRecent = tokenService.findMostRecentTokenByUserId(user.getId());
        if (!token.equals(tokenRecent)) {
            throw new AuthorizationException(String.format("Token [%s] is expired.", tokenAlaHashed));
        }
    }
}
