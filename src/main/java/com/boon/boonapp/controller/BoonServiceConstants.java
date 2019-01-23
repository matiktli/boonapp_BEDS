package com.boon.boonapp.controller;

import com.google.common.collect.Lists;
import lombok.experimental.UtilityClass;

import java.util.List;

@UtilityClass
public class BoonServiceConstants {

    public static final Integer DEFAULT_PAGE_SIZE = 50;

    public static final Integer TOKEN_LENGTH = 30;
    public static final String TOKEN_HEADER_NAME = "TOKEN";
    public static final Integer TOKEN_DEFAULT_DURATION_HOURS = 48;

    // * URLs *
    public static final String BASE_URL = "/boon";

    public static final String USER_URL = BASE_URL + "/user";
    public static final String USER_WITH_ID_URL = USER_URL + "/{userId}";
    public static final String LOGIN_URL = USER_URL + "/login";
    public static final String LOGOUT_URL = USER_URL + "/logout";
    public static final String REGISTER_URL = USER_URL + "/register";

    public static final String NEEDY_URL = BASE_URL + "/needy";
    public static final String NEEDY_WITH_ID_URL = NEEDY_URL + "/{needyId}";

    public static final String HELP_URL = BASE_URL + "/help";
    public static final String HELP_WITH_ID_URL = HELP_URL + "/{helpId}";

    public static final List<String> PATHS_OUT_OF_SEC = Lists.newArrayList(LOGIN_URL, LOGOUT_URL, REGISTER_URL, "/swagger-ui.html", "/v2/**", "/api/**", "/favicon.ico", "/swagger**", "/webjars/**", "/swagger-resources/**", "/error");


}
