package project.Artista.service;

import org.springframework.security.core.userdetails.UserDetails;
import project.Artista.dto.records.user.AuthResponse;
import project.Artista.dto.records.user.LogInDTO;
import project.Artista.dto.records.user.SignUpDTO;
import project.Artista.dto.records.user.UserResDTO;
import project.Artista.exception.TokenExpired;

import java.util.Map;

public interface AuthServiceInterface {
        UserResDTO signUp(SignUpDTO signUpDTO);
        UserDetails logIn(LogInDTO logInDTO);
        AuthResponse generateToken(UserDetails userDetails);
        UserDetails validateToken(String token);
        String extractUsername(String token);
        String refreshToken(String refreshToken) throws TokenExpired;
        UserResDTO getUserProfileByUserName(String username);
}
