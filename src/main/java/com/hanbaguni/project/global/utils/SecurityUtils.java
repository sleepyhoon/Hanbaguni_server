package com.hanbaguni.project.global.utils;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtils {

    public static String getCurrentUsername() {
        final Authentication authentication = SecurityContextHolder
                .getContext().getAuthentication();
        if(authentication == null || authentication.getName() == null) {
            throw new RuntimeException("no authentication information");
        }
        return authentication.getName();
    }
}
