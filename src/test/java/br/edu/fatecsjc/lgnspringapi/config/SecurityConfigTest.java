package br.edu.fatecsjc.lgnspringapi.config;

import org.junit.jupiter.api.Test;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;

class SecurityConfigTest {

    @Test
    void shouldInstantiateSecurityConfig() {
        var jwtAuthFilter = mock(JwtAuthenticationFilter.class);
        var authenticationProvider = mock(AuthenticationProvider.class);
        var logoutHandler = mock(LogoutHandler.class);

        SecurityConfig config = new SecurityConfig(jwtAuthFilter, authenticationProvider, logoutHandler);
        assertNotNull(config);
    }

    @Test
    void shouldCreateSecurityFilterChain() throws Exception {
        var jwtAuthFilter = mock(JwtAuthenticationFilter.class);
        var authenticationProvider = mock(AuthenticationProvider.class);
        var logoutHandler = mock(LogoutHandler.class);

        SecurityConfig config = new SecurityConfig(jwtAuthFilter, authenticationProvider, logoutHandler);

        HttpSecurity http = mock(HttpSecurity.class, RETURNS_DEEP_STUBS);
        assertDoesNotThrow(() -> config.securityFilterChain(http));
    }

    @Test
    void securityFilterChainShouldReturnNonNullBean() throws Exception {
        var jwtAuthFilter = mock(JwtAuthenticationFilter.class);
        var authenticationProvider = mock(AuthenticationProvider.class);
        var logoutHandler = mock(LogoutHandler.class);

        SecurityConfig config = new SecurityConfig(jwtAuthFilter, authenticationProvider, logoutHandler);

        HttpSecurity http = mock(HttpSecurity.class, RETURNS_DEEP_STUBS);
        SecurityFilterChain chain = config.securityFilterChain(http);
        assertNotNull(chain);
    }
}
