package com.boon.boonapp.security;

import com.boon.boonapp.exception.AuthorizationException;
import com.boon.boonapp.model.User;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;

@UtilityClass
@Slf4j
public class AuthorizationUtil {

    public static void setUserToContext(User user) {
        SecurityContextHolder.getContext().setAuthentication(new PreAuthenticatedAuthenticationToken(user, null));
        log.info("User with id [{}] added to context", user.getId());
    }

    public static void clearContext() {
        log.info("Clearing context");
        SecurityContextHolder.clearContext();
    }

    public static User getCurrentUser() {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (currentUser == null) {
            throw new AuthorizationException("User not present in context");
        }
        currentUser.setPassword(currentUser.getPassword().replaceAll(".", "#"));
        return currentUser;
    }
}
