package com.gateway.Gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class security {

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity httpSecurity) {
        // we disabled the csrf that caused multiple issues
        httpSecurity.csrf(ServerHttpSecurity.CsrfSpec::disable).authorizeExchange(authorizeExchangeSpec -> authorizeExchangeSpec.anyExchange().authenticated()).httpBasic();

        return httpSecurity.build();
    }
}
