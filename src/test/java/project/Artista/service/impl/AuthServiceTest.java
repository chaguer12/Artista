package project.Artista.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import project.Artista.dto.records.user.LogInDTO;
import project.Artista.dto.records.user.SignUpDTO;
import project.Artista.dto.records.user.UserResDTO;
import project.Artista.exception.NoUserFound;
import project.Artista.mapper.mappers.UserMapper;
import project.Artista.model.enums.Role;
import project.Artista.model.user.User;
import project.Artista.repository.UserRepo;
import project.Artista.service.AuthServiceInterface;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
class AuthServiceTest {
    @Mock
    private UserRepo userRepo;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private UserMapper userMapper;
    @Mock
    private MyUserDetails userDetailsService;
    @Mock
    private AuthenticationManager authenticationManager;
    @InjectMocks
    private AuthService authService;

    private User user;
    private SignUpDTO signUpDTO;
    private LogInDTO loginDTO;


    @BeforeEach
    void setUp() {
        signUpDTO = new SignUpDTO("John Doe", "john_doe", "password123", "password123", "john@gmail.com");
        user = new User("j0hnD0e", "john_doe", "john@example.com", "hashed_password");
        loginDTO = new LogInDTO("john@example.com", "password123");
    }


    @Test
    void signUp() {
        when(userRepo.existsByEmail(signUpDTO.email())).thenReturn(false);
        when(userRepo.existsByUserName(signUpDTO.userName())).thenReturn(false);
        when(passwordEncoder.encode(signUpDTO.confirmPassword())).thenReturn("password is Hashed");
        when(userMapper.toDTO(any(User.class))).thenReturn(new UserResDTO(1, "john_doe", "John Doe", "john@example.com", null, null, null, Role.ROLE_ADMIN, true));

        UserResDTO result = authService.signUp(signUpDTO);

        assertNotNull(result);
        assertEquals("john_doe", result.userName());
        verify(userRepo,times(1)).save(any(User.class));
    }

    @Test
    void logIn() {
        UserDetails mockUserDetails = mock(UserDetails.class);
        Authentication mockAuth = mock(Authentication.class);
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class))).thenReturn(mockAuth);
        when(mockAuth.getPrincipal()).thenReturn(mockUserDetails);
        UserDetails result = authService.logIn(loginDTO);
        assertNotNull(result);
        assertEquals(mockUserDetails, result);
        verify(authenticationManager,times(1)).authenticate(any(UsernamePasswordAuthenticationToken.class));

    }

    @Test
    void logIn_ShouldThrowExceptionWhenUserNotFound() {
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenThrow(new NoUserFound("User not found"));
        assertThrows(NoUserFound.class, () -> authService.logIn(loginDTO));
        verify(authenticationManager, times(1)).authenticate(any(UsernamePasswordAuthenticationToken.class));
    }


}