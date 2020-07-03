package com.groupepsih.psihback.Utils;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {
    /**
     * this method permit to valid a Email
     * @param email
     * @return true|false
     */
    public static boolean isEmailValid(String email) {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
