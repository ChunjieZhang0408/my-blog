package com.example.config.security;

import com.example.mvc.model.MemberInfo;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * @Desc CustomUserDetails
 * @Author ZhangChunjie
 * @Date 2020/1/12 12:41
 * @Version 1.0
 */
public class CustomUserDetails extends MemberInfo implements UserDetails {

    private List<? extends GrantedAuthority> authorities;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return this.getAccount();
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    public void setAuthorities(List<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    }
}
