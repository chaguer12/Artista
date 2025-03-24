package project.Artista.service;

import project.Artista.dto.records.studio.StudioReqDTO;
import project.Artista.dto.records.studio.StudioResDTO;
import project.Artista.dto.records.studio.StudioUpdateDTO;

import java.util.List;

public interface StudioServiceInterface {
    StudioResDTO saveStudio(StudioReqDTO studioReqDTO);
    StudioResDTO updateStudio(int id,StudioUpdateDTO studioReqDTO);
    void deleteStudio(int id);
    StudioResDTO getStudio(int id);
    List<StudioResDTO> getStudios();
    List<StudioResDTO> getStudiosByProvider(int providerId);

}
