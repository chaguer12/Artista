package project.Artista.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import project.Artista.mapper.mappers.UserMapper;
import project.Artista.dto.records.user.LogInDTO;
import project.Artista.dto.records.user.SignUpDTO;
import project.Artista.dto.records.user.UserResDTO;
import project.Artista.exception.NoUserFound;
import project.Artista.exception.PasswordDoNotMatch;
import project.Artista.exception.UserAlreadyExists;
import project.Artista.model.enums.Role;
import project.Artista.model.user.Client;
import project.Artista.model.user.User;
import project.Artista.repository.UserRepo;
import project.Artista.service.AuthServiceInterface;

import java.util.Objects;
@Service
@RequiredArgsConstructor
public class AuthService implements AuthServiceInterface {
    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    @Override
    public UserResDTO signUp(SignUpDTO signUpDTO) {
        validateUser(signUpDTO);
        String encoded_password = passwordEncoder.encode(signUpDTO.password());
        User user = buildUser(signUpDTO,encoded_password);
        userRepo.save(user);
        return userMapper.toDTO(user);
    }

    @Override
    public UserResDTO logIn(LogInDTO logInDTO) {
        validateCreds(logInDTO);
        User user = userRepo.findByEmail(logInDTO.email());
        return userMapper.toDTO(user);

    }
    private void validateCreds(LogInDTO logInDTO){
        if(!userRepo.existsByEmail(logInDTO.email())){
                throw new NoUserFound("No user was found with email: " + logInDTO.email());
        }

    }

    private void validateUser(SignUpDTO userDTO){
        if (!Objects.equals(userDTO.confirmPassword(), userDTO.password())) {
            throw new PasswordDoNotMatch("Password and confirmation password do not match.");
        }
        if(userRepo.existsByUserName(userDTO.userName())){
            throw new UserAlreadyExists("User already exists with username: " + userDTO.userName());
        }
        if(userRepo.existsByEmail(userDTO.email())){
            throw new UserAlreadyExists("User already exists with email: " + userDTO.email());
        }
    }


    private User buildUser(SignUpDTO userDTO, String encodedPassword){
        return  Client.builder()
                .fullName(userDTO.fullName())
                .userName(userDTO.userName())
                .email(userDTO.email())
                .password(encodedPassword)
                .role(Role.ROLE_CLIENT)
                .build();
    }

    @Override
    public String generateToken(UserDetails userDetails) {
        return "";
    }
}
