package project.Artista.service.impl;


import lombok.*;
import org.springframework.stereotype.Service;
import project.Artista.dto.mapper.mappers.UserMapper;
import project.Artista.dto.records.user.UserReqDTO;
import project.Artista.dto.records.user.UserResDTO;
import project.Artista.dto.records.user.UserUpdateDTO;
import project.Artista.exception.UserAlreadyExists;
import project.Artista.model.Role;
import project.Artista.model.User;
import project.Artista.repository.UserRepo;
import project.Artista.service.UserServiceInterface;
@Service
@RequiredArgsConstructor
public class UserService implements UserServiceInterface {

    private final  UserRepo userRepo;
    private final UserMapper userMapper;




    @Override
    public UserResDTO saveUser(UserReqDTO userDTO) {
        //conditon dyal email
        if(userRepo.findByEmail(userDTO.email()) == null){
        User user = User.builder()
                .fullName(userDTO.fullName())
                .userName(userDTO.userName())
                .email(userDTO.email())
                .password(userDTO.password())
                .role(Role.ROLE_USER)
                .build();
        userRepo.save(user);
        return userMapper.toDTO(user);
        }else {
            throw new UserAlreadyExists("User already exists with email: " + userDTO.email());
        }
    }

    @Override
    public UserResDTO updateUser(UserUpdateDTO user) {
        return null;
    }

    @Override
    public boolean deleteUser(int id) {
        return false;
    }

    @Override
    public UserResDTO getUser(int id) {
        return null;
    }
}
