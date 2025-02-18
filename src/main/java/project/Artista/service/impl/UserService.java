package project.Artista.service.impl;


import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import project.Artista.dto.mapper.mappers.UserMapper;
import project.Artista.dto.records.user.UserReqDTO;
import project.Artista.dto.records.user.UserResDTO;
import project.Artista.dto.records.user.UserUpdateDTO;
import project.Artista.exception.UserAlreadyExists;
import project.Artista.model.enums.Role;
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




    @Override
    public UserResDTO saveUser(UserReqDTO userDTO) {
        //conditon dyal email
        if(userRepo.findByEmail(userDTO.email()) == null && Objects.equals(userDTO.confirmPassword(), userDTO.password())){
            String encodePass = passwordEncoder.encode(userDTO.password());
        User user = User.builder()
                .fullName(userDTO.fullName())
                .userName(userDTO.userName())
                .email(userDTO.email())
                .password(encodePass)
                .role(Role.ROLE_USER)
                .build();
        userRepo.save(user);
        return userMapper.toDTO(user);
        }else {
            throw new UserAlreadyExists("User already exists with email: " + userDTO.email());
        }
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
