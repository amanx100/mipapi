package com.sincos.app;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GlobalInputValidator {
    public static boolean isValidIpAddress (String ipAddress) {
        String IPADDRESS_PATTERN = "(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)";

        Pattern pattern = Pattern.compile(IPADDRESS_PATTERN);
        Matcher matcher = pattern.matcher(ipAddress);
        return matcher.matches();
    }


    public static boolean isValidHostPort (String hostPort) {
        boolean status = false;
        if (isValidInteger(hostPort)) {
            int hp = Integer.parseInt(hostPort);
            if (hp >= 0 && hp <= 65535) status = true;
        }
        return status;
    }


    public static boolean isValidInteger (String value) {
        String integerPattern = "\\d+";

        Pattern pattern = Pattern.compile(integerPattern);
        Matcher matcher = pattern.matcher(value);

        return matcher.matches();
    }
}
