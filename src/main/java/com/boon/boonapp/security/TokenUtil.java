package com.boon.boonapp.security;

import lombok.experimental.UtilityClass;
import net.bytebuddy.utility.RandomString;

@UtilityClass
public class TokenUtil {

    private static final Integer TOKEN_LENGTH = 10;

    public static String generateToken() {
        //we need smh better dude
        return RandomString.make(RandomString.DEFAULT_LENGTH);
    }

    public static Boolean isValidTokenFormat(String token) {
        return token.length() == 10;
    }
}
