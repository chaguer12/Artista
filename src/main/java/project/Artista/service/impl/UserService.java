package project.Artista.service.impl;


import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import project.Artista.mapper.mappers.UserMapper;
import project.Artista.dto.records.user.UserReqDTO;
import project.Artista.dto.records.user.UserResDTO;
import project.Artista.dto.records.user.UserUpdateDTO;
import project.Artista.exception.PasswordDoNotMatch;
import project.Artista.exception.UserAlreadyExists;
import project.Artista.model.enums.Role;
import project.Artista.model.user.Client;
import project.Artista.model.user.User;
import project.Artista.repository.UserRepo;
import project.Artista.service.UserServiceInterface;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserService implements UserServiceInterface {

    private final  UserRepo userRepo;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;


    //chuf AuthService


    @Override
    public UserResDTO saveUser(UserReqDTO userDTO) {
        validateUser(userDTO);
        String encoded_password = passwordEncoder.encode(userDTO.password());
        Client user = (Client) buildUser(userDTO,encoded_password);
        userRepo.save(user);
        return userMapper.toDTO(user);
    }
    private void validateUser(UserReqDTO userDTO) {
        //for validation mzyana
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
    private User buildUser(UserReqDTO userDTO, String encodedPassword) {
        return  Client.builder()
                .fullName(userDTO.fullName())
                .userName(userDTO.userName())
                .email(userDTO.email())
                .password(encodedPassword)

                .build();
    }

    @Override
    public UserResDTO updateUser(int id,UserUpdateDTO updateDTO) {
        User user = userRepo.findById(id).orElseThrow(() -> new RuntimeException("User not found!"));
        updateDTO.fullName().ifPresent(user::setFullName);
        updateDTO.userName().ifPresent(user::setUserName);
        updateDTO.email().ifPresent(user::setEmail);
        updateDTO.password().ifPresent(user::setPassword);
        updateDTO.profilePic().ifPresent(user::setProfilePic);
        updateDTO.password().ifPresent(user::setPassword);
        userRepo.save(user);
        return userMapper.toDTO(user);
    }

    @Override
    public boolean deleteUser(int id) {
       if(userRepo.existsById(id)){
           userRepo.deleteById(id);
           return true;
       }else{
           return false;
       }
    }

    @Override
    public UserResDTO getUser(int id) {
        User user = userRepo.getById(id);
        return userMapper.toDTO(user);
    }

    @Override
    public List<UserResDTO> getAllUsers() {
        List<User> users = userRepo.findAll();
        return users.stream().map(userMapper::toDTO).toList();
    }
}
