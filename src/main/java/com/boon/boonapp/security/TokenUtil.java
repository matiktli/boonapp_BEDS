package com.boon.boonapp.security;

import lombok.experimental.UtilityClass;
import net.bytebuddy.utility.RandomString;

@UtilityClass
public class TokenUtil {

    private static final Integer TOKEN_LENGTH = 30;

    public static String generateToken() {
        String token = RandomString.make(TOKEN_LENGTH);
        return token;
    }

    public static Boolean isValidTokenFormat(String token) {
        return token.length() == 10;
    }
}
