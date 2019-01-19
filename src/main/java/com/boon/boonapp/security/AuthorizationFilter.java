package com.boon.boonapp.security;

import com.boon.boonapp.exception.AuthorizationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.boon.boonapp.controller.BoonServiceConstants.LOGIN_URL;
import static com.boon.boonapp.controller.BoonServiceConstants.REGISTER_URL;
import static com.boon.boonapp.controller.BoonServiceConstants.TOKEN_HEADER_NAME;

public class AuthorizationFilter extends OncePerRequestFilter {

    private final SecurityService securityService;

    public AuthorizationFilter(SecurityService securityService) {
        super();
        this.securityService = securityService;
    }

    private final List<String> PATHS_TO_EXCLUDE = new ArrayList<>(Arrays.asList(LOGIN_URL, REGISTER_URL));
    private AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String authHeader = request.getHeader(TOKEN_HEADER_NAME);
        if (authHeader == null || !authHeader.isEmpty()) {
            throw new AuthorizationException("Missing authorization token in header!");
        }
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return PATHS_TO_EXCLUDE.stream().
                anyMatch(p -> antPathMatcher.match(p, request.getServletPath()));
    }
}
