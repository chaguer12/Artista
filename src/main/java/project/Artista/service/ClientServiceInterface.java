package project.Artista.service;

import org.springframework.web.multipart.MultipartFile;
import project.Artista.dto.records.user.UserReqDTO;
import project.Artista.dto.records.user.UserResDTO;
import project.Artista.dto.records.user.UserUpdateDTO;

import java.io.IOException;
import java.util.List;

public interface ClientServiceInterface {
    UserResDTO saveUser(UserReqDTO user);
    UserResDTO updateUser(int id, UserUpdateDTO user);
    boolean deleteUser(int id);
    UserResDTO getUser(int id);
    List<UserResDTO> getAllUsers();
    String uploadProfilePic(String email, MultipartFile file) throws IOException;
}
