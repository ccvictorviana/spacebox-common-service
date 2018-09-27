package br.com.spacebox.common.service.security;

import br.com.spacebox.common.service.exception.AuthorizationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtTokenFilter extends GenericFilterBean {

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain filterChain) throws IOException, ServletException {

        try {
            SecurityContextHolder.getContext().setAuthentication(jwtTokenProvider.getAuthentication((HttpServletRequest) req));
            filterChain.doFilter(req, res);
        } catch (AuthorizationException e) {
            HttpServletResponse response = (HttpServletResponse) res;
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
        }
    }
}
