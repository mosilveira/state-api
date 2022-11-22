package uol.compass.state.entities;

import lombok.*;
import uol.compass.state.enums.Region;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "STATES")
public class StateEntity {

    @Id
    @Column(name = "ID", updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "REGION")
    private Region region;

    @Column(name = "POPULATION")
    private Integer population;

    @Column(name = "CAPITAL")
    private String capital;

    @Column(name = "AREA")
    private Double area;

}
