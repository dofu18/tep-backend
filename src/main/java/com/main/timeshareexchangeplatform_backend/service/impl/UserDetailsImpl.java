package com.main.timeshareexchangeplatform_backend.service.impl;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.main.timeshareexchangeplatform_backend.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class UserDetailsImpl implements UserDetails {
    private static final long serialVersionUID = 1L;
    private UUID id;
    private String username;
    private Boolean enabled;

    @JsonIgnore
    private String password;
    GrantedAuthority authority = null;

    public UserDetailsImpl(UUID id, String username, String password, GrantedAuthority authority, Boolean enabled) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.authority = authority;
        this.enabled = enabled;
    }
    public static UserDetailsImpl build(User account) {
        GrantedAuthority authority = new SimpleGrantedAuthority(account.getRole());
        return new UserDetailsImpl(account.getUser_id(), account.getUsername(), account.getPassword(), authority,
                account.isStatus());
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(authority);
    }

    public UUID getId() {
        return id;
    }
    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        UserDetailsImpl account = (UserDetailsImpl) o;
        return Objects.equals(id, account.id);
    }
}
