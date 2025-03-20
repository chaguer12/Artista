package project.Artista.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import project.Artista.dto.records.user.UserReqDTO;
import project.Artista.dto.records.user.UserResDTO;
import project.Artista.dto.records.user.UserUpdateDTO;
import project.Artista.exception.EntityNotFound;
import project.Artista.exception.PasswordDoNotMatch;
import project.Artista.exception.UserAlreadyExists;
import project.Artista.mapper.mappers.ClientMapper;
import project.Artista.model.enums.PhotoType;
import project.Artista.model.user.Client;
import project.Artista.model.user.Provider;
import project.Artista.repository.ClientRepo;
import project.Artista.repository.UserRepo;
import project.Artista.service.ClientServiceInterface;
import project.Artista.service.CloudinaryServiceInterface;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
@Service
@RequiredArgsConstructor
public class ClientService implements ClientServiceInterface {
    private final PasswordEncoder passwordEncoder;
    private final ClientMapper clientMapper;
    private final UserRepo userRepo;
    private final ClientRepo clientRepo;
    private final CloudinaryServiceInterface cloudinaryService;
    @Override
    public UserResDTO saveUser(UserReqDTO userDTO) {
        validateUser(userDTO);
        String encodedPassword = passwordEncoder.encode(userDTO.confirmPassword());
        Client client =  buildUser(userDTO, encodedPassword);
        clientRepo.save(client);
        return clientMapper.toDTO(client);
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
    private Client buildUser(UserReqDTO userDTO, String encodedPassword) {
        return  Client.builder()
                .fullName(userDTO.fullName())
                .userName(userDTO.userName())
                .email(userDTO.email())
                .password(encodedPassword)
                .build();
    }
    @Override
    public UserResDTO updateUser(int id, UserUpdateDTO updateDTO) {
        Client user = clientRepo.findById(id).orElseThrow(() -> new EntityNotFound("client was not found using id: " + id));
        updateDTO.fullName().ifPresent(user::setFullName);
        updateDTO.userName().ifPresent(user::setUserName);
        updateDTO.email().ifPresent(user::setEmail);
        updateDTO.password().ifPresent(user::setPassword);
        updateDTO.profilePic().ifPresent(user::setProfilePic);
        updateDTO.password().ifPresent(user::setPassword);
        clientRepo.save(user);
        return clientMapper.toDTO(user);
    }


    @Override
    public boolean deleteUser(int id) {
        if(clientRepo.existsById(id)){
            clientRepo.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public UserResDTO getUser(int id) {
        Client client = clientRepo.findById(id).orElseThrow(() -> new EntityNotFound("No entity was found wiht id: " +id ));
        return clientMapper.toDTO(client);
    }

    @Override
    public List<UserResDTO> getAllUsers() {
        List<Client> clients = clientRepo.findAll();
        return clients.stream().map(clientMapper::toDTO).toList();
    }
    @Override
    public String uploadProfilePic(String email, MultipartFile file) throws IOException {
        Client client = clientRepo.findByEmail(email).orElseThrow(() -> new EntityNotFound("Admin not found with email: " + email));
        String photo = cloudinaryService.uploadImage(file, PhotoType.USER_PROFILE,client.getId());
        client.setProfilePic(photo);
        clientRepo.save(client);
        return photo;
    }
}
