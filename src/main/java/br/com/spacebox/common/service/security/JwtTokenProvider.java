package br.com.spacebox.common.service.security;

import br.com.spacebox.common.model.response.UserResponse;
import br.com.spacebox.common.service.client.AuthClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class JwtTokenProvider {
    @Autowired
    private AuthClient authClient;

    public Authentication getAuthentication(HttpServletRequest req) {
        String bearerToken = req.getHeader("Authorization");
        UserResponse userData = authClient.detail(bearerToken);

        UserDetailsAuth userDetailsAuth = UserDetailsAuth
                .getBuilder()
                .withUsername(userData.getUsername())
                .withPassword("")
                .withAccountExpired(false)
                .withAccountLocked(false)
                .withCredentialsExpired(false)
                .withDisabled(false)
                .withId(userData.getId())
                .withName(userData.getName())
                .withEmail(userData.getEmail())
                .build();
        return new PrincipalToken(userDetailsAuth, "", userDetailsAuth.getAuthorities());
    }
}
