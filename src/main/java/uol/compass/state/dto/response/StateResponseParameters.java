package uol.compass.state.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StateResponseParameters {

    private Integer numberOfElements;

    private Long totalElements;

    private Integer totalPages;

    private List<StateResponseDTO> states;
}
