package br.com.spacebox.common.service.config;

import br.com.spacebox.common.service.exception.AuthClientErrorDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AuthClientConfiguration {

    @Bean
    public AuthClientErrorDecoder spaceBoxErrorDecoder() {
        return new AuthClientErrorDecoder();
    }
}