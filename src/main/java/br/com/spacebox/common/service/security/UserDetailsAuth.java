package br.com.spacebox.common.service.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UserDetailsAuth extends User {
    private static final long serialVersionUID = -3531439484732724601L;

    private final Long id;
    private final String name;
    private final String email;

    public UserDetailsAuth(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired,
                           boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities, Long id, String name, String email) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        this.id = id;
        this.email = email;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public static UserDetailsAuth.UserDetailAuthBuilder getBuilder() {
        return new UserDetailsAuth.UserDetailAuthBuilder();
    }

    public static class UserDetailAuthBuilder {
        private Long id;
        private String name;
        private String email;
        private String username;
        private String password;
        private boolean disabled;
        private List<GrantedAuthority> authorities;
        private boolean accountExpired;
        private boolean accountLocked;
        private boolean credentialsExpired;

        public UserDetailsAuth.UserDetailAuthBuilder withId(Long id) {
            this.id = id;
            return this;
        }

        public UserDetailsAuth.UserDetailAuthBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public UserDetailsAuth.UserDetailAuthBuilder withEmail(String email) {
            this.email = email;
            return this;
        }

        public UserDetailsAuth.UserDetailAuthBuilder withUsername(String username) {
            this.username = username;
            return this;
        }

        public UserDetailsAuth.UserDetailAuthBuilder withPassword(String password) {
            this.password = password;
            return this;
        }

        public UserDetailsAuth.UserDetailAuthBuilder withAuthorities(List<GrantedAuthority> authorities) {
            this.authorities = authorities;
            return this;
        }

        public UserDetailsAuth.UserDetailAuthBuilder withAccountExpired(boolean accountExpired) {
            this.accountExpired = accountExpired;
            return this;
        }

        public UserDetailsAuth.UserDetailAuthBuilder withAccountLocked(boolean accountLocked) {
            this.accountLocked = accountLocked;
            return this;
        }

        public UserDetailsAuth.UserDetailAuthBuilder withCredentialsExpired(boolean credentialsExpired) {
            this.credentialsExpired = credentialsExpired;
            return this;
        }

        public UserDetailsAuth.UserDetailAuthBuilder withDisabled(boolean disabled) {
            this.disabled = disabled;
            return this;
        }

        public UserDetailsAuth build() {
            if (this.authorities == null)
                this.authorities = new ArrayList<>();

            return new UserDetailsAuth(this.username, this.password, !this.disabled, !this.accountExpired, !this.credentialsExpired,
                    !this.accountLocked, this.authorities, this.id, this.name, this.email);
        }
    }
}
