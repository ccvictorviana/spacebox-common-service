package br.com.spacebox.common.service.security;

import br.com.spacebox.common.messages.EMessage;
import br.com.spacebox.common.messages.MessageSourceCustom;
import br.com.spacebox.common.model.response.ErrorMessageResponse;
import br.com.spacebox.common.service.exception.AuthorizationException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.RetryableException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

    @Autowired
    protected MessageSourceCustom messageSource;

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain filterChain) throws IOException, ServletException {

        try {
            SecurityContextHolder.getContext().setAuthentication(jwtTokenProvider.getAuthentication((HttpServletRequest) req));
            filterChain.doFilter(req, res);
        } catch (AuthorizationException e) {
            HttpServletResponse response = (HttpServletResponse) res;
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
        } catch (RetryableException e) {
            HttpServletResponse response = (HttpServletResponse) res;
            ErrorMessageResponse errorResponse = new ErrorMessageResponse(messageSource.getMessage(EMessage.ERROR_AUTHSERVICEUNAVAILABLE));

            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.setStatus(HttpStatus.SERVICE_UNAVAILABLE.value());
            response.getWriter().write(convertObjectToJson(errorResponse));
        }
    }

    public String convertObjectToJson(Object object) throws JsonProcessingException {
        if (object == null)
            return null;

        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(object);
    }
}
