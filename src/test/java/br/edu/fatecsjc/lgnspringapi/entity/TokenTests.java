package br.edu.fatecsjc.lgnspringapi.entity;

import br.edu.fatecsjc.lgnspringapi.enums.TokenType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class TokenTest {

  private Token token;
  private User user;

  @BeforeEach
  void setUp() {
    user = User.builder()
        .id(1L)
        .email("mike_tayson")
        .password("champofufc")
        .build();

    token = Token.builder()
        .id(1L)
        .token("mike345tay10")
        .tokenType(TokenType.BEARER)
        .revoked(false)
        .expired(false)
        .user(user)
        .build();
  }

  @Test
  void shouldInitializeEntityWithBuilder() {
    // Then
    assertThat(token).isNotNull();
    assertThat(token.getId()).isEqualTo(1L);
    assertThat(token.getToken()).isEqualTo("mike345tay10");
    assertThat(token.getTokenType()).isEqualTo(TokenType.BEARER);
    assertThat(token.isRevoked()).isFalse();
    assertThat(token.isExpired()).isFalse();
    assertThat(token.getUser().getUsername()).isEqualTo("mike_tayson");
  }

  @Test
  void shouldSetAndGetId() {
    token.setId(99L);
    assertThat(token.getId()).isEqualTo(99L);
  }

  @Test
  void shouldSetAndGetToken() {
    token.setToken("new_token_456");
    assertThat(token.getToken()).isEqualTo("new_token_456");
  }

  @Test
  void shouldSetAndGetType() {
    token.setTokenType(TokenType.BEARER);
    assertThat(token.getTokenType()).isEqualTo(TokenType.BEARER);
  }

  @Test
  void shouldSetAndGetRevokedStatus() {
    token.setRevoked(true);
    assertThat(token.isRevoked()).isTrue();
  }

  @Test
  void shouldSetAndGetExpiredStatus() {
    token.setExpired(true);
    assertThat(token.isExpired()).isTrue();
  }

  @Test
  void shouldSetAndGetUser() {
    User newUser = User.builder()
        .id(2L)
        .email("jane_doe")
        .password("pass123")
        .build();
    token.setUser(newUser);
    assertThat(token.getUser()).isEqualTo(newUser);
    assertThat(token.getUser().getUsername()).isEqualTo("jane_doe");
  }
}