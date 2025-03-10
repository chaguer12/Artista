package project.Artista.service;

import project.Artista.dto.records.user.UserReqDTO;
import project.Artista.dto.records.user.UserResDTO;
import project.Artista.dto.records.user.UserUpdateDTO;

import java.util.List;

public interface ProviderServiceInterface {
    UserResDTO saveUser(UserReqDTO user);
    UserResDTO updateUser(int id, UserUpdateDTO user);
    boolean deleteUser(int id);
    UserResDTO getUser(int id);
    List<UserResDTO> getAllUsers();
}
