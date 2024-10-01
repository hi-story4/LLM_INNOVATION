package com.llm.receipt_review.server.security.user;

import com.llm.receipt_review.server.domains.user.entity.User;
import com.llm.receipt_review.server.domains.user.entity.UserStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class UserPrincipal implements UserDetails {

    private final User user;

    public UserPrincipal(User user){
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        List<GrantedAuthority> authorities = new ArrayList<>();


        this.user.getRoles().forEach(p ->
        {
            GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + p);
            authorities.add(authority);
        });
        return authorities;
    }

    @Override
    public String getPassword() {
        return this.user.getClientApiKey();
    }

    @Override
    public String getUsername() {
        return this.user.getId();
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.user.getUserStatus().equals(UserStatus.ACTIVATE);
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.user.getUserStatus().equals(UserStatus.ACTIVATE);
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.user.getUserStatus().equals(UserStatus.ACTIVATE);

    }

    @Override
    public boolean isEnabled() {
        return this.user.getUserStatus().equals(UserStatus.ACTIVATE);
    }
}
