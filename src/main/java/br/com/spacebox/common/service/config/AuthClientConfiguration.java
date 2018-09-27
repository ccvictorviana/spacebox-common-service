package br.com.spacebox.common.service.config;

import br.com.spacebox.common.service.exception.SpaceBoxErrorDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AuthClientConfiguration {

    @Bean
    public SpaceBoxErrorDecoder spaceBoxErrorDecoder() {
        return new SpaceBoxErrorDecoder();
    }
}