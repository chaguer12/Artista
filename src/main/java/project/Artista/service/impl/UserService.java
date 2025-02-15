package project.Artista.service.impl;


import lombok.*;
import org.springframework.stereotype.Service;
import project.Artista.dto.mapper.mappers.UserMapper;
import project.Artista.dto.records.user.UserReqDTO;
import project.Artista.dto.records.user.UserResDTO;
import project.Artista.dto.records.user.UserUpdateDTO;
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
//        User user = userMapper.toEntity(userDTO);
//        User savedUser = userRepo.save(user);
//        return userMapper.toDTO(savedUser);
        User user = User.builder()
                .fullName(userDTO.fullName())
                .userName(userDTO.userName())
                .email(userDTO.email())
                .password(userDTO.password())
                .build();
        userRepo.save(user);
        return userMapper.toDTO(user);
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
