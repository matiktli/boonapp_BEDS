package com.boon.boonapp.security;

import lombok.experimental.UtilityClass;
import net.bytebuddy.utility.RandomString;

import static com.boon.boonapp.controller.BoonServiceConstants.TOKEN_LENGTH;

@UtilityClass
public class TokenUtil {

    public static String generateToken() {
        String token = RandomString.make(TOKEN_LENGTH);
        return token;
    }

    public static Boolean isValidTokenFormat(String token) {
        return token.length() == TOKEN_LENGTH;
    }
}
