package br.com.spacebox.common.service.security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class PrincipalToken extends UsernamePasswordAuthenticationToken {

    private static final long serialVersionUID = 1L;

    public PrincipalToken(Object principal, Object credentials) {
        super(principal, credentials);
    }

    public PrincipalToken(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities) {
        super(principal, credentials, authorities);
    }

    public UserDetailsAuth getUserDetailsAuth() {
        return (UserDetailsAuth) getPrincipal();
    }
}