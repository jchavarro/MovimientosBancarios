package pruebadevsu.movimientos.model.repositories;

import org.springframework.data.repository.CrudRepository;
import pruebadevsu.movimientos.model.entities.MovimientoEntity;

public interface MovimientoRepository extends CrudRepository<MovimientoEntity, Integer> {
}
