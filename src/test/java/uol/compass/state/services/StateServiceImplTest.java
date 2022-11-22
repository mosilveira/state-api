package uol.compass.state.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import uol.compass.state.dto.request.StateRequestDTO;
import uol.compass.state.dto.response.StateResponseDTO;
import uol.compass.state.dto.response.StateResponseParameters;
import uol.compass.state.entities.StateEntity;
import uol.compass.state.exceptions.StateNotFoundException;
import uol.compass.state.repositories.StateRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class StateServiceImplTest {

    public static final Long ID = 1L;

    @InjectMocks
    private StateServiceImpl stateService;

    @Mock
    private StateRepository stateRepository;

    @Mock
    private ModelMapper modelMapper;

    @Test
    void shouldCreateState_success() {
        StateEntity state = new StateEntity();
        StateResponseDTO response = new StateResponseDTO();
        StateRequestDTO request = new StateRequestDTO();

        Mockito.when(modelMapper.map(any(), eq(StateEntity.class))).thenReturn(state);
        Mockito.when(stateRepository.save(any())).thenReturn(state);
        Mockito.when(modelMapper.map(any(), eq(StateResponseDTO.class))).thenReturn(response);

        StateResponseDTO stateResponseDTO = stateService.create(request);

        assertEquals(response, stateResponseDTO);
        verify(stateRepository).save(any());
    }

    @Test
    void shouldUpdateState_success() {
        StateEntity state = new StateEntity();
        StateResponseDTO response = new StateResponseDTO();
        StateRequestDTO request = new StateRequestDTO();

        Mockito.when(stateRepository.findById(any())).thenReturn(Optional.of(state));
        Mockito.when(modelMapper.map(any(), eq(StateEntity.class))).thenReturn(state);
        Mockito.when(stateRepository.save(any())).thenReturn(state);
        Mockito.when(modelMapper.map(any(), eq(StateResponseDTO.class))).thenReturn(response);

        StateResponseDTO stateResponseDTO = stateService.update(ID, request);

        assertEquals(response, stateResponseDTO);
        verify(stateRepository).save(any());
    }

    @Test
    void shouldUpdateState_error_notFound() {
        StateRequestDTO request = new StateRequestDTO();

        Mockito.when(stateRepository.findById(any())).thenReturn(Optional.empty());

        assertThrows(StateNotFoundException.class, () -> stateService.update(ID, request));
    }

    @Test
    void shouldDeleteState_success() {
        StateEntity state = new StateEntity();

        Mockito.when(stateRepository.findById(any())).thenReturn(Optional.of(state));

        stateService.delete(ID);

        verify(stateRepository).deleteById(any());
    }

    @Test
    void shouldFindStateById_success() {
        StateEntity state = new StateEntity();
        StateResponseDTO response = new StateResponseDTO();

        Mockito.when(stateRepository.findById(any())).thenReturn(Optional.of(state));
        Mockito.when(modelMapper.map(any(), eq(StateResponseDTO.class))).thenReturn(response);

        StateResponseDTO stateResponseDTO = stateService.findById(ID);

        assertEquals(response, stateResponseDTO);
    }

    @Test
    void shouldFindAllStates_success() {
        StateEntity state = new StateEntity();
        StateResponseDTO response = new StateResponseDTO();
        Page<StateEntity> page = new PageImpl<>(List.of(state));
        StateResponseParameters expectedStateResponseParameters = getStateResponseParameters();

        Mockito.when(stateRepository.findAll((Pageable) any())).thenReturn(page);
        Mockito.when(modelMapper.map(any(), eq(StateResponseDTO.class))).thenReturn(response);

        StateResponseParameters stateResponseParameters = stateService.findAll(null, any(Pageable.class));

        assertEquals(expectedStateResponseParameters, stateResponseParameters);
    }

    private StateResponseParameters getStateResponseParameters() {
        return StateResponseParameters.builder()
                .numberOfElements(1)
                .totalElements(1L)
                .totalPages(1)
                .states(List.of(new StateResponseDTO()))
                .build();
    }

}