package uol.compass.state.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import uol.compass.state.entities.StateEntity;
import uol.compass.state.enums.Region;

public interface StateRepository extends JpaRepository<StateEntity, Long> {

    Page<StateEntity> findAllByRegion(Region region, Pageable page);

}
