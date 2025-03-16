package project.Artista.service;

import org.springframework.security.core.userdetails.UserDetails;
import project.Artista.dto.records.user.LogInDTO;
import project.Artista.dto.records.user.SignUpDTO;
import project.Artista.dto.records.user.UserResDTO;

public interface AuthServiceInterface {
        UserResDTO signUp(SignUpDTO signUpDTO);
        UserDetails logIn(LogInDTO logInDTO);
        String generateToken(UserDetails userDetails);
        UserDetails validateToken(String token);
        String extractUsername(String token);
}
