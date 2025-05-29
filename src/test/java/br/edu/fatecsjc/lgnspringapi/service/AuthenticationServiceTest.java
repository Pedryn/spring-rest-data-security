package br.edu.fatecsjc.lgnspringapi.service;

import br.edu.fatecsjc.lgnspringapi.dto.AuthenticationRequestDTO;
import br.edu.fatecsjc.lgnspringapi.dto.AuthenticationResponseDTO;
import br.edu.fatecsjc.lgnspringapi.dto.RegisterRequestDTO;
import br.edu.fatecsjc.lgnspringapi.entity.User;
import br.edu.fatecsjc.lgnspringapi.enums.Role;
import br.edu.fatecsjc.lgnspringapi.repository.TokenRepository;
import br.edu.fatecsjc.lgnspringapi.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AuthenticationServiceTest {

  @Mock
  private UserRepository userRepository;

  @Mock
  private TokenRepository tokenRepository;

  @Mock
  private PasswordEncoder passwordEncoder;

  @Mock
  private JwtService jwtService;

  @Mock
  private AuthenticationManager authenticationManager;

  @InjectMocks
  private AuthenticationService authenticationService;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void testRegister_ShouldReturnAuthenticationResponse() {
    RegisterRequestDTO request = RegisterRequestDTO.builder()
        .firstname("John")
        .lastname("Doe")
        .email("john@example.com")
        .password("password")
        .role(Role.USER)
        .build();

    User user = User.builder()
        .firstName("John")
        .lastName("Doe")
        .email("john@example.com")
        .password("encodedPassword")
        .role(Role.USER)
        .build();

    when(passwordEncoder.encode("password")).thenReturn("encodedPassword");
    when(userRepository.save(any(User.class))).thenReturn(user);
    when(jwtService.generateToken(user)).thenReturn("access-token");
    when(jwtService.generateRefreshToken(user)).thenReturn("refresh-token");

    AuthenticationResponseDTO response = authenticationService.register(request);

    assertEquals("access-token", response.getAccessToken());
    assertEquals("refresh-token", response.getRefreshToken());

    verify(tokenRepository).save(any());
  }

  @Test
  void testAuthenticate_ShouldReturnAuthenticationResponse() {
    AuthenticationRequestDTO request = AuthenticationRequestDTO.builder()
        .email("john@example.com")
        .password("password")
        .build();

    User user = User.builder()
        .email("john@example.com")
        .password("encodedPassword")
        .role(Role.USER)
        .build();

    when(userRepository.findByEmail("john@example.com")).thenReturn(Optional.of(user));
    when(jwtService.generateToken(user)).thenReturn("access-token");
    when(jwtService.generateRefreshToken(user)).thenReturn("refresh-token");

    AuthenticationResponseDTO response = authenticationService.authenticate(request);

    assertEquals("access-token", response.getAccessToken());
    assertEquals("refresh-token", response.getRefreshToken());

    verify(authenticationManager).authenticate(any(UsernamePasswordAuthenticationToken.class));
    verify(tokenRepository).save(any());
  }
}
