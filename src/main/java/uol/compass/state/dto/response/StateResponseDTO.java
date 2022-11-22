package uol.compass.state.dto.response;

import lombok.Data;
import uol.compass.state.enums.Region;

@Data
public class StateResponseDTO {

    private Long id;

    private String name;

    private Region region;

    private Integer population;

    private String capital;

    private Double area;

}
