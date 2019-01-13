package com.boon.boonapp.controller;

import com.boon.boonapp.domain.BaseDTO;
import lombok.experimental.UtilityClass;

@UtilityClass
public class BoonServiceConstants {

    public static final Integer DEFAULT_PAGE_SIZE = 50;

    public static final String TOKEN_HEADER_NAME = "X-TOKEN";

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
    public static final String HELP_WITH_ID_URL = NEEDY_URL + "/{helpId}";

}
