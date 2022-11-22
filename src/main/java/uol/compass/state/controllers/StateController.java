package uol.compass.state.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uol.compass.state.dto.request.StateRequestDTO;
import uol.compass.state.dto.response.StateResponseDTO;
import uol.compass.state.dto.response.StateResponseParameters;
import uol.compass.state.enums.Region;
import uol.compass.state.services.StateServiceImpl;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class StateController {

    private final StateServiceImpl stateService;

    @PostMapping(value = "/api/states")
    public ResponseEntity<StateResponseDTO> create(@RequestBody @Valid StateRequestDTO request) {
        StateResponseDTO response = stateService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping(value = "/api/states")
    public ResponseEntity<StateResponseParameters> findAll(@RequestParam(required = false) Region region,
                                                           Pageable pageable) {
        StateResponseParameters response = stateService.findAll(region, pageable);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping(value = "/api/states/{id}")
    public ResponseEntity<StateResponseDTO> findById(@PathVariable("id") Long id) {
        StateResponseDTO response = stateService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping(value = "/api/states/{id}")
    public ResponseEntity<StateResponseDTO> update(@PathVariable("id") Long id, @RequestBody @Valid StateRequestDTO request) {
        StateResponseDTO response = stateService.update(id, request);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping(value = "/api/states/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        stateService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
