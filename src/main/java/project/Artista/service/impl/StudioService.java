package project.Artista.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.Artista.mapper.mappers.StudioMapper;
import project.Artista.dto.records.studio.StudioReqDTO;
import project.Artista.dto.records.studio.StudioResDTO;
import project.Artista.dto.records.studio.StudioUpdateDTO;
import project.Artista.model.Studio;
import project.Artista.model.user.Provider;
import project.Artista.repository.ProviderRepo;
import project.Artista.repository.StudioRepo;
import project.Artista.service.StudioServiceInterface;

import java.util.List;


@Service
@RequiredArgsConstructor
public class StudioService implements StudioServiceInterface {
    private final StudioRepo studioRepo;
    private final StudioMapper studioMapper;
    private final ProviderRepo providreRepo;
    @Override
    public StudioResDTO saveStudio(StudioReqDTO studioReqDTO) {
        Provider provider =  providreRepo.getById(studioReqDTO.provider_id());
        Studio studio = Studio.builder()
                .name(studioReqDTO.name())
                .description(studioReqDTO.description())
                .city(studioReqDTO.city())
                .address(studioReqDTO.address())
                .phone(studioReqDTO.phone())
                .email(studioReqDTO.email())
                .hourRate(studioReqDTO.hourRate())
                .provider(provider)
                .build();
        studioRepo.save(studio);
        return studioMapper.toDTO(studio);
    }

    @Override
    public StudioResDTO updateStudio(int id,StudioUpdateDTO studioReqDTO) {
        Studio studio = studioRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("Studio not found with id: "+ id));
        studioReqDTO.name().ifPresent(studio::setName);
        studioReqDTO.description().ifPresent(studio::setDescription);
        studioReqDTO.city().ifPresent(studio::setCity);
        studioReqDTO.address().ifPresent(studio::setAddress);
        studioReqDTO.phone().ifPresent(studio::setPhone);
        studioRepo.save(studio);
        return studioMapper.toDTO(studio);
    }

    @Override
    public void deleteStudio(int id) {
        studioRepo.deleteById(id);
    }

    @Override
    public StudioResDTO getStudio(int id) {
        Studio studio = studioRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("Studio not found with id: "+ id));
        return studioMapper.toDTO(studio);
    }

    @Override
    public List<StudioResDTO> getStudios() {
        List<Studio> studios = studioRepo.findAll();
        return studios.stream().map(studioMapper::toDTO).toList();
    }

    @Override
    public List<StudioResDTO> getStudiosByProvider(int providerId) {
        List<Studio> studios = studioRepo.getStudioByProviderId(providerId);
        return studios.stream().map(studioMapper::toDTO).toList();
    }
}
