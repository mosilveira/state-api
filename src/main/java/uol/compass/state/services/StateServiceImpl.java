package uol.compass.state.services;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uol.compass.state.dto.request.StateRequestDTO;
import uol.compass.state.dto.response.StateResponseDTO;
import uol.compass.state.dto.response.StateResponseParameters;
import uol.compass.state.entities.StateEntity;
import uol.compass.state.enums.Region;
import uol.compass.state.exceptions.StateNotFoundException;
import uol.compass.state.repositories.StateRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StateServiceImpl implements StateService {

    private final StateRepository stateRepository;

    private final ModelMapper modelMapper;

    @Override
    public StateResponseDTO create(StateRequestDTO request) {
        StateEntity stateToCreate = modelMapper.map(request, StateEntity.class);
        StateEntity createdState = stateRepository.save(stateToCreate);

        return modelMapper.map(createdState, StateResponseDTO.class);
    }

    @Override
    public StateResponseParameters findAll(Region region, Pageable pageable) {
        Page<StateEntity> page = region == null ?
                stateRepository.findAll(pageable) :
                stateRepository.findAllByRegion(region, pageable);

        return createStateResponseParameters(page);
    }

    @Override
    public StateResponseDTO findById(Long id) {
        StateEntity state = getStateEntity(id);

        return modelMapper.map(state, StateResponseDTO.class);
    }

    @Override
    public StateResponseDTO update(Long id, StateRequestDTO request) {
        getStateEntity(id);

        StateEntity stateToUpdate = modelMapper.map(request, StateEntity.class);
        stateToUpdate.setId(id);
        StateEntity updatedState = stateRepository.save(stateToUpdate);

        return modelMapper.map(updatedState, StateResponseDTO.class);
    }

    @Override
    public void delete(Long id) {
        getStateEntity(id);
        stateRepository.deleteById(id);
    }

    private StateResponseParameters createStateResponseParameters(Page<StateEntity> page) {
        List<StateResponseDTO> states = page.stream()
                .map(this::createStateResponseDTO)
                .collect(Collectors.toList());

        return StateResponseParameters.builder()
                .numberOfElements(page.getNumberOfElements())
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .states(states)
                .build();
    }

    private StateResponseDTO createStateResponseDTO(StateEntity state) {
        return modelMapper.map(state, StateResponseDTO.class);
    }

    private StateEntity getStateEntity(Long id) {
        return stateRepository.findById(id)
                .orElseThrow(StateNotFoundException::new);
    }

}
