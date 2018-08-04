package com.expertsoft.web.util;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public final class SecurityUtils {

    private SecurityUtils() {}

    public static String username() {
        final var authentication = SecurityContextHolder.getContext().getAuthentication();
        final var principal = authentication.getPrincipal();
        if (principal instanceof UserDetails) {
            final var user = (UserDetails) principal;
            return user.getUsername();
        }
        return principal.toString();
    }
}
