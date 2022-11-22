package uol.compass.state.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import uol.compass.state.enums.Region;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StateRequestDTO {

    @NotBlank
    private String name;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Region region;

    @NotNull
    private Integer population;

    @NotBlank
    private String capital;

    @NotNull
    private Double area;

}
