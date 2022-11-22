package uol.compass.state.services;

import org.springframework.data.domain.Pageable;
import uol.compass.state.dto.request.StateRequestDTO;
import uol.compass.state.dto.response.StateResponseDTO;
import uol.compass.state.dto.response.StateResponseParameters;
import uol.compass.state.enums.Region;

public interface StateService {

    StateResponseDTO create(StateRequestDTO request);

    StateResponseParameters findAll(Region region, Pageable pageable);

    StateResponseDTO findById(Long id);

    StateResponseDTO update(Long id, StateRequestDTO request);

    void delete(Long id);

}
