package project.Artista.service;

import project.Artista.dto.records.user.LogInDTO;
import project.Artista.dto.records.user.SignUpDTO;
import project.Artista.dto.records.user.UserResDTO;

public interface AuthServiceInterface {
        UserResDTO signUp(SignUpDTO signUpDTO);
        UserResDTO logIn(LogInDTO logInDTO);
}
