package com.boon.boonapp.interceptor;

import com.boon.boonapp.exception.AuthorizationException;
import com.boon.boonapp.model.User;
import com.boon.boonapp.security.AuthorizationUtil;
import com.boon.boonapp.security.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

import static com.boon.boonapp.controller.BoonServiceConstants.TOKEN_HEADER_NAME;

@Component
public class AuthHeaderInterceptor extends HandlerInterceptorAdapter {

    private final SecurityService securityService;

    @Autowired
    public AuthHeaderInterceptor(SecurityService securityService) {
        this.securityService = securityService;
    }


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Optional<String> authHeader = Optional.ofNullable(request.getHeader(TOKEN_HEADER_NAME));
        if (!authHeader.isPresent()) {
            throw new AuthorizationException("Token header not present");
        }
        User user = securityService.getUserFromToken(authHeader.get());
        securityService.validateTokenForUser(authHeader.get(), user);
        AuthorizationUtil.setUserToContext(user);
        return super.preHandle(request, response, handler);
    }


    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        AuthorizationUtil.clearContext();
        super.postHandle(request, response, handler, modelAndView);
    }
}
