package com.llm.receipt_review.server.security.filter;

import com.llm.receipt_review.server.security.user.UserPrincipal;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.security.Principal;
import java.util.Collection;

public class ApiKeyAuth extends AbstractAuthenticationToken {

    private final UserPrincipal principal;

    public ApiKeyAuth(UserPrincipal principal, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.principal = principal;
        setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return principal;
    }

}