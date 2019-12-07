package com.table.order.common.service.helper;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class UserHelper {


    public boolean isLoggedRestaurateur() {
        Collection<? extends GrantedAuthority> authorities = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getAuthorities();

        if ( !authorities.isEmpty() ) {
            String role = authorities.stream()
                    .findFirst()
                    .get()
                    .toString();

            if ("ROLE_RESTAURATEUR".equals(role))
                return true;
        }

        return false;
    }

    public String getLoggedUserUsername() {
        return SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal()
                .toString();
    }

}
